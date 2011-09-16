#ifndef DATABASE_H
#define DATABASE_H
#include "goods.h"
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



}
#endif // DATABASE_H
