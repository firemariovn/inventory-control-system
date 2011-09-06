#include "icsmainform.h"
#include "ui_icsmainform.h"
#include "goods.h"

ICSMainForm::ICSMainForm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ICSMainForm)
{
    Goods *g = new Goods(1);
    ui->setupUi(this);
    QSqlTableModel *model = Goods::getTableModel();
    model->select();
    ui->goodsTableView->setModel(model);




    // add status bar message
     statusBar()->showMessage("Status:Now you are in the mainform of Inventory Control System");



}

ICSMainForm::~ICSMainForm()
{
    delete ui;
}
