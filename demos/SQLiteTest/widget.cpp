#include "widget.h"
#include "ui_widget.h"
#include <QMessageBox>
#include <QSqlError>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    model = new QSqlTableModel(this);
    model->setTable("Goods");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
    model->select(); //选取整个表的所有行
    ui->tableView->setModel(model);
}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_viewBtn_clicked()
{
    QString name = ui->lineEdit->text();
    model->setFilter(QObject::tr("name like '%%1%'").arg(name));
    model->select(); //显示结果
}

void Widget::on_editBnt_clicked()
{
    model->database().transaction(); //开始事务操作
       if (model->submitAll()) {
           model->database().commit(); //提交
       } else {
           model->database().rollback(); //回滚
           QMessageBox::warning(this, tr("tableModel"),
                                tr("数据库错误: %1")
                                .arg(model->lastError().text()));
       }
}

void Widget::on_cancleBnt_clicked()
{
    model->revertAll();
}

void Widget::on_viewAllBnt_clicked()
{
    model->setTable("Goods");
    model->select();
}

void Widget::on_increaseBnt_clicked()
{
    model->setSort(0,Qt::AscendingOrder);
    model->select();
}

void Widget::on_decreaseBnt_clicked()
{
    model->setSort(0,Qt::DescendingOrder);
    model->select();
}

void Widget::on_delBnt_clicked()
{
    int curRow = ui->tableView->currentIndex().row();
    model->removeRow(curRow);
    int ok = QMessageBox::warning(this,tr("删除当前行!"),tr("你确定""删除当前行吗？"),
                                  QMessageBox::Yes,QMessageBox::No);
    if(ok == QMessageBox::No)
    {
        model->revertAll(); //如果不删除，则撤销
    }
    else model->submitAll(); //否则提交，在数据库中删除该行
}

void Widget::on_addBnt_clicked()
{
    int rowNum = model->rowCount(); //获得表的行数
    int id = 10;
    model->insertRow(rowNum); //添加一行
    model->setData(model->index(rowNum,0),id);
    //model->submitAll(); //可以直接提交
}
