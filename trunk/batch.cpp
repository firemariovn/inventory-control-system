#include "batch.h"
#include <QSqlTableModel>
#include <QSqlQuery>
#include <QDebug>
#include <database.h>

Batch::Batch()
{
}

Batch::~Batch()
{
}

Batch::Batch(int id)
{
    QSqlQuery query;
    query.exec("SELECT bid,gid,unitprice,quantity,expireddate, type, batchnumber,btime FROM Batch WHERE bid = :id");
    query.bindValue(":id", id);
    query.exec();
    int gid = 0;
    while (query.next())
    {
        this->bid = query.value(0).toInt();
        gid = query.value(1).toInt();
        this->unitPrice = query.value(2).toFloat();
        this->quantity = query.value(3).toInt();
        this->expiredDate = query.value(4).toDateTime();
        this->type = query.value(5).toInt();
        this->batchNumber = query.value(6).toString();
        this->btime = query.value(7).toDateTime();
    }
}

QSqlTableModel* Batch::getTableModel()
{

    QSqlTableModel *model = new QSqlTableModel();
    model->setTable("Batch");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    return model;
}


int Batch::getBid()
{
    return bid;
}

void Batch::setBid(int bid)
{
    this->bid = bid;
}

int Batch::getGid()
{
    return gid;
}

void Batch::setGid(int gid)
{
    this->gid = gid;
}

float Batch::getUnitPrice()
{
    return unitPrice;
}

void Batch::setUnitPrice(int unitPrice)
{
    this->unitPrice = unitPrice;
}

int Batch::getQuantity()
{
    return quantity;
}

void Batch::setQuantity(int quantity)
{
    this->quantity = quantity;
}

QDateTime Batch::getExpiredDate()
{
    return expiredDate;
}

void Batch::setExpiredDate(QDateTime expiredDate)
{
    this->expiredDate = expiredDate;
}

int Batch::getType()
{
    return type;
}

void Batch::setType(int type)
{
    this->type = type;
}

QString Batch::getBatchNumber()
{
    return batchNumber;
}

void Batch::setBatchNumber(QString batchNumber)
{
    this->batchNumber = batchNumber;
}

QDateTime Batch::getBTime()
{
    return btime;
}

void Batch::setBTime(QDateTime btime)
{
    this->btime = btime;
}

bool Batch::addBatch()
{
    QSqlQuery query;
    query.prepare("insert into Batch(gid,unitprice,quantity,expireddate,type,batchnumber,btime)""values(?,?,?,?,?,?,?)");
    query.addBindValue(this->gid);
    query.addBindValue(this->unitPrice);
    query.addBindValue(this->quantity);
    query.addBindValue(this->expiredDate);
    query.addBindValue(this->type);
    query.addBindValue(this->batchNumber);
    query.addBindValue(this->btime);
    return query.exec();
}

