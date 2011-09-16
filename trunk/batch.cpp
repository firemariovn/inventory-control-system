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
    query.prepare("SELECT bid,gid,unitprice,quantity,expireddate, type, batchnumber,btime FROM Batch WHERE bid = :id");
    query.bindValue(":id", id);
    query.exec();
    int gid = 0;
    while (query.next())
    {
        this->bid = query.value(0).toInt();
        gid = query.value(1).toInt();
        this->unitPrice = query.value(2).toFloat();
        this->quantity = query.value(3).toInt();
        this->expiredDate = query.value(4).toDate();
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
    return this->bid;
}

void Batch::setBid(int bid)
{
    this->bid = bid;
}

int Batch::getGid()
{
    return this->gid;
}

void Batch::setGid(int gid)
{
    this->gid = gid;
}

float Batch::getUnitPrice()
{
    return this->unitPrice;
}

void Batch::setUnitPrice(float unitPrice)
{
    this->unitPrice = unitPrice;
}

int Batch::getQuantity()
{
    return this->quantity;
}

void Batch::setQuantity(int quantity)
{
    this->quantity = quantity;
}

QDate Batch::getExpiredDate()
{
    return this->expiredDate;
}

void Batch::setExpiredDate(QDate expiredDate)
{
    this->expiredDate = expiredDate;
}

int Batch::getType()
{
    return this->type;
}

void Batch::setType(int type)
{
    this->type = type;
}

QString Batch::getBatchNumber()
{
    return this->batchNumber;
}

void Batch::setBatchNumber(QString batchNumber)
{
    this->batchNumber = batchNumber;
}

QDateTime Batch::getBTime()
{
    return this->btime;
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
    query.addBindValue(this->btime.toString("yyyy-MM-dd hh:mm:ss"));
    if(query.exec())
    {
        query.exec("select last_insert_rowid()");
        while (query.next())
        {
            this->bid = query.value(0).toInt();
        }
        return true;
    }
    return false;
}

