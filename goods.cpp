#include "goods.h"
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QDebug>
#include <database.h>

Goods::Goods(){
}

QString Goods::getName(){
  return name;
}

void Goods::setName(QString name){
  this->name = name;
}

int Goods::getDepletionLine(){
  return depletionLine;
}

void Goods::setDepletionLine(int depletionLine){
  this->depletionLine = depletionLine;
}

int Goods::getTotalQuantity(){
  return totalQuantity;
}

void Goods::setTotalQuantity(int totalQuantity){
  this->totalQuantity = totalQuantity;
}

int Goods::getGid(){
  return gid;
}

void Goods::setGid(int gid){
 this->gid = gid;
}




Goods::Goods(int id){
    QSqlQuery query;
    query.exec("SELECT gid,name,catid,depletionline,totalquantity FROM Goods WHERE gid = :id");
    query.bindValue(":id", id);
    query.exec();
    int catid = 0;
    while (query.next()) {
            this->gid = query.value(0).toInt();
            this->name = query.value(1).toString();
            catid = query.value(2).toInt();
            this->depletionLine= query.value(3).toInt();
            this->totalQuantity = query.value(4).toInt();

        }

}


QSqlTableModel* Goods::getTableModel(){

    QSqlTableModel *model = new QSqlTableModel();
    model->setTable("Goods");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    return model;
}

bool Goods::addGoods(){

    QSqlQuery query;
    query.prepare("insert into Goods(name, depletionline)""values(?,?)");
    query.addBindValue(this->name);
    query.addBindValue(this->depletionLine);
     return query.exec();

    ;
}
