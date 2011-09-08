#include "icsmainform.h"
#include "ui_icsmainform.h"
#include "goods.h"
#include "batch.h"
#include "category.h"

ICSMainForm::ICSMainForm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ICSMainForm)
{
    ui->setupUi(this);
    QSqlTableModel *goodsModel = Goods::getTableModel();
    goodsModel->select();
    ui->goodsTableView->setModel(goodsModel);

    QSqlTableModel *batchModel = Batch::getTableModel();
    batchModel->select();
    ui->warehouseTableView->setModel(batchModel);

    QSqlTableModel *categoryModel = Category::getTableModel();
    categoryModel->select();
    ui->categoryTableView->setModel(categoryModel);



    // add status bar message
     statusBar()->showMessage("Status:Now you are in the mainform of Inventory Control System");



}

ICSMainForm::~ICSMainForm()
{
    delete ui;
}
