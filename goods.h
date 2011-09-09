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

     int getcatid();
     void setcatid(int catid);
     bool addGoods();
     bool deleteGoods();
     bool updateGoods();
     static  QSqlQueryModel * statistic(QString goodsID,QString fromDate, QString toDate);





private:
 int gid;
 QString name;
 int depletionLine;
 int totalQuantity;
 int catid;

};
#endif // GOODS_H
