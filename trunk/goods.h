#ifndef GOODS_H
#define GOODS_H
#include <QSqlTableModel>
class Goods{



public:
     Goods();
     Goods(int id);
    ~Goods();

     static QSqlTableModel* getTableModel();

     QString getName();
     void setName(QString name);

     int getDepletionLine();
     void setDepletionLine(int depletionLine);

     int getTotalQuantity();
     void setTotalQuantity(int totalQuantity);

     int getGid();
     void setGid(int gid);

     bool addGoods();
     bool deleteGoods();
     bool updateGoods();





private:
 int gid;
 QString name;
 int depletionLine;
 int totalQuantity;

};
#endif // GOODS_H
