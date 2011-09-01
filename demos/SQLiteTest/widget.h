#ifndef WIDGET_H
#define WIDGET_H
#include <QSqlTableModel>
#include <QWidget>

namespace Ui {
    class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();

private slots:
    void on_viewBtn_clicked();

    void on_editBnt_clicked();

    void on_cancleBnt_clicked();

    void on_viewAllBnt_clicked();

    void on_increaseBnt_clicked();

    void on_decreaseBnt_clicked();

    void on_delBnt_clicked();

    void on_addBnt_clicked();

private:
    Ui::Widget *ui;
    QSqlTableModel *model;
};

#endif // WIDGET_H
