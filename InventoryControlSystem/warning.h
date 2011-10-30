#ifndef WARNING_H
#define WARNING_H
#include <QSqlTableModel>
#include <QDateTime>

class Warning{

public:
     Warning();
     Warning(int id);
    ~Warning();

     static QSqlTableModel* getTableModel();

     int getWid();
     void setWid(int wid);

     QDateTime getWtime();
     void setWtime(QDateTime wtime);

     QString getWmsg();
     void setWmsg(QString wmsg);

     int getWtype();
     void setWtype(int wtype);

     int getBid();
     void setBid(int bid);

     bool addWarning();
     bool deleteWarning();
     bool updateWarning();



private:
 int wid;
 QDateTime wtime;
 QString wmsg;
 //wtype = 0 The current quantity of this goods is smaller than its depletion line
 //wtype = 1 after you sell this batch, the quantity of this goods is smaller than its depletion line
 int wtype;
 int bid;

};

#endif // WARNING_H
