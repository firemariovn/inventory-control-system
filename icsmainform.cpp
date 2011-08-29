#include "icsmainform.h"
#include "ui_icsmainform.h"

ICSMainForm::ICSMainForm(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::ICSMainForm)
{
    ui->setupUi(this);
}

ICSMainForm::~ICSMainForm()
{
    delete ui;
}
