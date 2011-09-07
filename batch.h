#ifndef BATCH_H
#define BATCH_H
#include <QSqlTableModel>
class Batch{

public:
     Batch();
     Batch(int id);
    ~Batch();

     static QSqlTableModel* getTableModel();

     int getBid();
     void setBid(int bid);

     int getGid();
     void setGid(int gid);

     float getUnitPrice();
     void setUnitPrice(int unitPrice);

     int getQuantity();
     void setQuantity(int quantity);

     int getType();
     void setType(int type);

     QString getBatchNumber();
     void setBatchNumber(QString batchNumber);


     bool addBatch();
     bool inbound();
     bool outbound();





private:
 int bid;
 int gid;
 float unitPrice;
 int type;
 QString batchNumber;

};
#endif // BATCH_H
