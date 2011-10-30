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
    ui->comboBox->addItem("Grocery Manager");
    ui->comboBox->addItem("Grocery Worker");
}

ICSLoginForm::~ICSLoginForm()
{
    delete ui;
}

void ICSLoginForm::on_loginButton_clicked()
{
    //Check if the user is authorized
    //createConnection();
    bool position=true;
    Staff * loginer=new Staff();
    loginer->setName(ui->usrLineEdit->text());
    loginer->setPassword(ui->pwdLineEdit->text());
    if(ui->comboBox->currentIndex()==0)
    {
        loginer->ismanager=true;
        if(!loginer->isManager())
           {
               //QMessageBox::warning(this,tr("Warning"),tr("please choose right position!"),QMessageBox::Yes);
               position=false;
           }

    }
    else
    {
        loginer->ismanager=false;
        if(loginer->isManager())
        {
            //QMessageBox::warning(this,tr("Warning"),tr("please choose right position!"),QMessageBox::Yes);
            position=false;
        }

    }
    if(loginer->login()&&position)
        accept();
    else
        if(!loginer->login())

        {

            QMessageBox::warning(this,tr("Warning"),tr("user name or password error!"),QMessageBox::Yes);
        //clear all infomation of user label and password label
        ui->usrLineEdit->clear();
        ui->pwdLineEdit->clear();
        ui->usrLineEdit->setFocus();
        }
        else
        {

            QMessageBox::warning(this,tr("Warning"),tr("please choose right position!"),QMessageBox::Yes);
        }
    }

void ICSLoginForm::on_exitButton_clicked()
{
    close();
}
