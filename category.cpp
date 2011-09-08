#include "category.h"
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QDebug>
#include <database.h>

Category::Category(){

}

QString Category::getName(){
  return name;
}

void Category::setName(QString name){
  this->name = name;
}

int Category::getCid(){
  return cid;
}

void Category::setCid(int cid){
  this->cid = cid;
}

Category::~Category()
{
}

bool Category::addCategory()
{
    QSqlQuery query;
    query.prepare("insert into Category(name)""values(?)");
    query.addBindValue(this->name);
    return query.exec();
}

Category::Category(int id){
    QSqlQuery query;
    query.exec("SELECT cid,name FROM Category WHERE cid = :id");
    query.bindValue(":id", id);
    query.exec();

    while (query.next()) {
            this->cid = query.value(0).toInt();
            this->name = query.value(1).toString();

        }

}


QSqlTableModel* Category::getTableModel(){

    QSqlTableModel *model = new QSqlTableModel();
    model->setTable("Category");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    return model;
}
