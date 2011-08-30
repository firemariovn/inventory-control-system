#include "icsmainform.h"
#include "ui_icsmainform.h"

ICSMainForm::ICSMainForm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ICSMainForm)
{
    ui->setupUi(this);

     // add status bar message
     statusBar()->showMessage("Now you are in the mainform of Inventory Control System");



}

ICSMainForm::~ICSMainForm()
{
    delete ui;
}
