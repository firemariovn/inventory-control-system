#include "staff.h"
#include "batch.h"
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QDebug>
#include <database.h>
#include <QSqlDatabase>
Staff::Staff(){

}

int Staff::getSid(){
 return sid;
}

void Staff::setSid(int sid){
 this->sid = sid;
}

QString Staff::getName(){
  return name;
}

void Staff::setName(QString name){
  this->name = name;
}

QString Staff::getPassword(){
 return password;
}

void Staff::setPassword(QString password){
  this->password = password;
}

QString Staff::getTitle(){


  return title;
}

void Staff::setTitle(QString title){
  this->title = title;
}

Staff::Staff(int id){
    QSqlQuery query;
    query.prepare("SELECT sid,name,password,title,roleid FROM Staff WHERE sid = :id");
    query.bindValue(":id", id);
    query.exec();

    while (query.next()) {
            this->sid = query.value(0).toInt();
            this->name = query.value(1).toString();

            this->password= query.value(2).toString();
            this->title = query.value(3).toString();
            this->roleid = query.value(4).toInt();

        }

}

bool Staff::login(){

    //createConnection();
    QSqlQuery query;
    query.prepare("SELECT sid FROM Staff WHERE name = ? and password = ?");
    query.addBindValue( this->name);
    query.addBindValue( this->password);
    query.exec();
    //query.next();
    //qDebug() << query.value(0).toString();

    if(query.next())
        return true;

    else
        return false;


}

QSqlTableModel* Staff::getTableModel(){

    QSqlTableModel *model = new QSqlTableModel();
    model->setTable("Staff");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    return model;
}

 Warning* Staff::checkIfGoodsNearDepletion(Batch* b,Goods* g, int quantity){
     Warning *w = new Warning();

     QString goodsName = g->getName();
     int depletionLine = g->getDepletionLine();
     if(g->getTotalQuantity() <= g->getDepletionLine() ){
         w->setWmsg("Please purchase more"+goodsName+ "Because The current quantity of "+goodsName+" is smaller than its depletion line :"+depletionLine+".");
         w->setWtime(QDateTime::currentDateTime());
         w->setWtype(0);
         w->setBid(b->getBid());
         return w;
     }

     if(quantity<=g->getDepletionLine()){
         w->setWmsg("Recommend purchase more "+goodsName+ "Because after you sell this batch, the quantity of "+goodsName+" is smaller than its depletion line :"+depletionLine+".");
         w->setWtime(QDateTime::currentDateTime());
         w->setWtype(1);
         w->setBid(b->getBid());
         return w;

     }


     return NULL;
}
