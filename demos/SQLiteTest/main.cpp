#include <QtGui/QApplication>
#include "widget.h"
#include <QTextCodec>
#include <QtDebug>
#include <QDir>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QTextCodec::setCodecForTr(QTextCodec::codecForLocale());
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");
    db.setDatabaseName("inventory.tbl");
    db.open();
    Widget w;
    w.show();
    return a.exec();
}
