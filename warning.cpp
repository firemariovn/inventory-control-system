#include "warning.h"
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QDebug>
#include <database.h>
#include <QDateTime>

Warning::Warning(){

}

int Warning::getWid(){
   return wid;
}

void Warning::setWid(int wid){
   this->wid = wid;
}

QDateTime Warning::getWtime(){
  return wtime;
}

void Warning::setWtime(QDateTime wtime){
   this->wtime = wtime;
}

QString Warning::getWmsg(){
   return wmsg;
}

void Warning::setWmsg(QString wmsg){
   this->wmsg = wmsg;
}

int Warning::getWtype(){
   return wtype;
}

void Warning::setWtype(int wtype){
  this->wtype = wtype;
}

int Warning::getBid(){
  return bid;
}

void Warning::setBid(int bid){
  this->bid = bid;
}

Warning::Warning(int id){
    QSqlQuery query;
    query.prepare("SELECT wid,wtime,wmsg,wtype,bid FROM Warning WHERE wid = :id");
    query.bindValue(":id", id);
    query.exec();


    while (query.next()) {
        this->wid = query.value(0).toInt();
        this->wtime = query.value(1).toDateTime();

        this->wmsg= query.value(2).toString();
        this->wtype = query.value(3).toInt();
        this->bid = query.value(4).toInt();

        }


}

bool Warning::addWarning(){

    QSqlQuery query;
      query.prepare("insert into Warning(bid,wtype,wmsg,wtime)""values(?,?,?,?)");
      query.addBindValue(this->bid);
      query.addBindValue(this->wtype);
      query.addBindValue(this->wmsg);
      query.addBindValue(this->wtime);
       return query.exec();

}

QSqlTableModel* Warning::getTableModel(){

    QSqlTableModel *model = new QSqlTableModel();
    model->setTable("Warning");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    return model;
}


