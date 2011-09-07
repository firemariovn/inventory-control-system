#include "staff.h"
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QDebug>
#include <database.h>

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
    query.exec("SELECT sid,name,password,title,roleid FROM Staff WHERE sid = :id");
    query.bindValue(":id", id);
    query.exec();

    while (query.next()) {
            this->sid = query.value(0).toInt();
            this->name = query.value(1).toString();

            this->password= query.value(2).toInt();
            this->title = query.value(3).toInt();
            this->roleid = query.value(3).toInt();

        }

}

bool Staff::login(){

    QSqlQuery query;
    query.exec("SELECT sid FROM Staff WHERE name = :name and password = :password");
    query.bindValue(":name", this->name);
    query.bindValue(":password", this->password);
    query.exec();


    while (query.next()) {
       return true;

        }

    return false;


}

QSqlTableModel* Staff::getTableModel(){

    QSqlTableModel *model = new QSqlTableModel();
    model->setTable("Staff");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    return model;
}

