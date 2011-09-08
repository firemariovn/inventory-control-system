#include <QMessageBox>
#include "icsloginform.h"
#include "ui_icsloginform.h"
#include "database.h"
#include "staff.h"

ICSLoginForm::ICSLoginForm(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ICSLoginForm)
{
    ui->setupUi(this);
    ui->pwdLineEdit->setEchoMode(QLineEdit::Password);

}

ICSLoginForm::~ICSLoginForm()
{
    delete ui;
}

void ICSLoginForm::on_loginButton_clicked()
{
    //Check if the user is authorized
    //createConnection();
    Staff * loginer=new Staff();
    loginer->setName(ui->usrLineEdit->text());
    loginer->setPassword(ui->pwdLineEdit->text());
    if(loginer->login())
        accept();
    else

        QMessageBox::warning(this,tr("Warning"),tr("user name or password error!"),QMessageBox::Yes);
        //clear all infomation of user label and password label
        ui->usrLineEdit->clear();
        ui->pwdLineEdit->clear();
        ui->usrLineEdit->setFocus();
    }

void ICSLoginForm::on_exitButton_clicked()
{
    close();
}
