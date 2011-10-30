/********************************************************************************
** Form generated from reading UI file 'widget.ui'
**
** Created: Wed Sep 7 01:18:49 2011
**      by: Qt User Interface Compiler version 4.7.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_WIDGET_H
#define UI_WIDGET_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QLabel>
#include <QtGui/QLineEdit>
#include <QtGui/QPushButton>
#include <QtGui/QTableView>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Widget
{
public:
    QPushButton *viewBtn;
    QTableView *tableView;
    QLineEdit *lineEdit;
    QPushButton *editBnt;
    QPushButton *cancleBnt;
    QPushButton *addBnt;
    QPushButton *delBnt;
    QLabel *label;
    QPushButton *viewAllBnt;
    QPushButton *increaseBnt;
    QPushButton *decreaseBnt;

    void setupUi(QWidget *Widget)
    {
        if (Widget->objectName().isEmpty())
            Widget->setObjectName(QString::fromUtf8("Widget"));
        Widget->resize(644, 277);
        viewBtn = new QPushButton(Widget);
        viewBtn->setObjectName(QString::fromUtf8("viewBtn"));
        viewBtn->setGeometry(QRect(200, 210, 75, 23));
        tableView = new QTableView(Widget);
        tableView->setObjectName(QString::fromUtf8("tableView"));
        tableView->setGeometry(QRect(10, 10, 401, 171));
        lineEdit = new QLineEdit(Widget);
        lineEdit->setObjectName(QString::fromUtf8("lineEdit"));
        lineEdit->setGeometry(QRect(60, 210, 113, 20));
        editBnt = new QPushButton(Widget);
        editBnt->setObjectName(QString::fromUtf8("editBnt"));
        editBnt->setGeometry(QRect(510, 50, 75, 23));
        cancleBnt = new QPushButton(Widget);
        cancleBnt->setObjectName(QString::fromUtf8("cancleBnt"));
        cancleBnt->setGeometry(QRect(510, 90, 75, 23));
        addBnt = new QPushButton(Widget);
        addBnt->setObjectName(QString::fromUtf8("addBnt"));
        addBnt->setGeometry(QRect(510, 10, 75, 23));
        delBnt = new QPushButton(Widget);
        delBnt->setObjectName(QString::fromUtf8("delBnt"));
        delBnt->setGeometry(QRect(510, 130, 75, 23));
        label = new QLabel(Widget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(20, 210, 31, 16));
        viewAllBnt = new QPushButton(Widget);
        viewAllBnt->setObjectName(QString::fromUtf8("viewAllBnt"));
        viewAllBnt->setGeometry(QRect(200, 250, 75, 23));
        increaseBnt = new QPushButton(Widget);
        increaseBnt->setObjectName(QString::fromUtf8("increaseBnt"));
        increaseBnt->setGeometry(QRect(510, 190, 75, 23));
        decreaseBnt = new QPushButton(Widget);
        decreaseBnt->setObjectName(QString::fromUtf8("decreaseBnt"));
        decreaseBnt->setGeometry(QRect(510, 240, 75, 23));

        retranslateUi(Widget);
        QObject::connect(viewBtn, SIGNAL(clicked()), Widget, SLOT(show()));

        QMetaObject::connectSlotsByName(Widget);
    } // setupUi

    void retranslateUi(QWidget *Widget)
    {
        Widget->setWindowTitle(QApplication::translate("Widget", "Widget", 0, QApplication::UnicodeUTF8));
        viewBtn->setText(QApplication::translate("Widget", "\346\237\245\350\257\242", 0, QApplication::UnicodeUTF8));
        lineEdit->setText(QString());
        editBnt->setText(QApplication::translate("Widget", "\346\217\220\344\272\244\344\277\256\346\224\271", 0, QApplication::UnicodeUTF8));
        cancleBnt->setText(QApplication::translate("Widget", "\346\222\244\351\224\200\344\277\256\346\224\271", 0, QApplication::UnicodeUTF8));
        addBnt->setText(QApplication::translate("Widget", "\346\267\273\345\212\240\350\256\260\345\275\225", 0, QApplication::UnicodeUTF8));
        delBnt->setText(QApplication::translate("Widget", "\345\210\240\351\231\244\351\200\211\344\270\255\350\241\214", 0, QApplication::UnicodeUTF8));
        label->setText(QApplication::translate("Widget", "\345\220\215\347\247\260", 0, QApplication::UnicodeUTF8));
        viewAllBnt->setText(QApplication::translate("Widget", "\346\237\245\350\257\242\345\205\250\350\241\250", 0, QApplication::UnicodeUTF8));
        increaseBnt->setText(QApplication::translate("Widget", "\346\214\211id\345\215\207\345\272\217\346\216\222\345\210\227", 0, QApplication::UnicodeUTF8));
        decreaseBnt->setText(QApplication::translate("Widget", "\346\214\211id\351\231\215\345\272\217\346\216\222\345\210\227", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class Widget: public Ui_Widget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_WIDGET_H
