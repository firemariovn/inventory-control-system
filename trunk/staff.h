#ifndef STAFF_H
#define STAFF_H
#include <QSqlTableModel>
class Staff{
public:
     Staff();
     Staff(int id);
    ~Staff();

     static QSqlTableModel* getTableModel();

     int getSid();
     void setSid(int sid);

     QString getName();
     void setName(QString name);

     QString getPassword();
     void setPassword(QString password);

     QString getTitle();
     void setTitle(QString title);

     int getRoleid();
     void setRoleid(int roleid);

     bool addStaff();
     bool deleteStaff();
     bool updateStaff();
     bool login();



private:
 int sid;
 QString name;
 QString password;
 QString title;
 int roleid;
};
#endif // STAFF_H
