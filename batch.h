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

         float getUnitPrice();
         void setUnitPrice(int unitPrice);

         int getQuantity();
         void setQuantity(int quantity);

         QDateTime getExpiredDate();
         void setExpiredDate(QDateTime expiredDate);

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
         float unitPrice;
         int quantity;
         int type;
         QDateTime btime;
         QDateTime expiredDate;
         QString batchNumber;

};
#endif // BATCH_H
