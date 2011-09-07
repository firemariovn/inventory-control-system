#ifndef CATEGORY_H
#define CATEGORY_H
#include <QSqlTableModel>
class Category{

public:
     Category();
     Category(int id);
    ~Category();

     static QSqlTableModel* getTableModel();

     int getCid();
     void setCid(int cid);

     QString getName();
     void setName(QString name);

     bool addCategory();
     bool deleteCategory();
     bool updateCategory();



private:
 int cid;
 QString name;



};

#endif // CATEGORY_H
