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

int Goods::getcatid(){
  return catid;
}

void Goods::setcatid(int catid){
 this->catid = catid;
}


Goods::Goods(int id){
    QSqlQuery query;
    query.prepare("SELECT gid,name,catid,depletionline,totalquantity FROM Goods WHERE gid = :id");
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
      query.prepare("insert into Goods(name,catid,depletionline)""values(?,?,?)");
      query.addBindValue(this->name);
      query.addBindValue(this->catid);
      query.addBindValue(this->depletionLine);
       return query.exec();

}

bool Goods::updateGoodsQuantity(int goodsID, int quantity){

    QSqlQuery query;
    query.prepare("update Goods set totalquantity = :quantity where gid = :id");
    query.bindValue(":quantity",quantity);
    query.bindValue(":id",goodsID);


    return query.exec();
}

QSqlQueryModel * Goods::statistic(QString goodsID, QString fromDate, QString toDate)
{
     QSqlQueryModel *model = new QSqlQueryModel();
     model->setQuery("select sum(case type when 1 then quantity else 0 end) ,sum(case type when 1 then quantity *unitprice else 0 end),sum(case type when 0 then quantity else 0 end) ,sum(case type when 0 then quantity *unitprice else 0 end)from Batch where gid ="+goodsID+" and (btime between '"+fromDate+"' and '"+toDate+"')");
     qDebug()<<"select sum(case type when 1 then quantity else 0 end) ,sum(case type when 1 then quantity *unitprice else 0 end),sum(case type when 0 then quantity else 0 end) ,sum(case type when 0 then quantity *unitprice else 0 end)from Batch where gid ="+goodsID+" and (btime between '"+fromDate+"' and '"+toDate+"')";
     return model;
}
