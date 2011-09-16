#ifndef DATABASE_H
#define DATABASE_H
#include "goods.h"
#include "warning.h"
#include <QSqlDatabase>
#include <QSqlError>
#include <QSqlQuery>
#include <QDebug>
#include <QApplication>



static bool createConnection()
{
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");
    QString applicationPath = QApplication::applicationDirPath();
    db.setDatabaseName(applicationPath+"/inventory.tbl");
    if(!db.open()){
        qDebug()<<"database connection.error\n";
        qDebug() << db.lastError();

        return false;
       }
    else{
      qDebug()<<"establish a database connection.\n";
        return true;
    }

}

static void checkoutAllGoodsNearDepletion(){

    QSqlQuery query;
    query.prepare("SELECT gid,name,catid,depletionline,totalquantity FROM Goods");
    query.exec();

    while (query.next()) {

            QString goodsName = query.value(1).toString();

            int depletionLine= query.value(3).toInt();
            int totalQuantity = query.value(4).toInt();

            if(totalQuantity<=depletionLine){
                Warning *w = new Warning();
                QString msg = QString("Please purchase more %1.Because The current quantity of %2 is smaller than its depletion line :%3.");

                  w->setWmsg(msg.arg(goodsName).arg(goodsName).arg(depletionLine));
                  w->setWtime(QDateTime::currentDateTime());
                  w->setWtype(0);
                  w->setBid(rand());

                  w->addWarning();

            }

        }



}
#endif // DATABASE_H
