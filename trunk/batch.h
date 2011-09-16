#ifndef BATCH_H
#define BATCH_H
#include <QSqlTableModel>
#include <qdatetime.h>

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
         void setUnitPrice(float unitPrice);

         int getQuantity();
         void setQuantity(int quantity);

         QDate getExpiredDate();
         void setExpiredDate(QDate expiredDate);

         int getType();
         void setType(int type);

         QString getBatchNumber();
         void setBatchNumber(QString batchNumber);

         QDateTime getBTime();
         void setBTime(QDateTime btime);


         bool addBatch();
         bool deleteBatch();
         bool updateBatch();





private:
         int bid;
         int gid;
         float unitPrice;
         int quantity;
         // 0 = inbound, 1= outbound
         int type;
         QDateTime btime;
         QDate expiredDate;
         QString batchNumber;

};
#endif // BATCH_H
