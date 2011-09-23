#include "icsmainform.h"
#include "ui_icsmainform.h"
#include "goods.h"
#include "batch.h"
#include "category.h"
#include "staff.h"
#include <QSqlRecord>
#include <QtGui>
#include <QTextCodec>
#include <QSqlQuery>
#include <QDebug>
#include "database.h"

ICSMainForm::ICSMainForm(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ICSMainForm)
{
    QTextCodec::setCodecForTr(QTextCodec::codecForLocale());
    ui->setupUi(this);
    QSqlQueryModel *goodsModel = Goods::getTableModel();
    ui->goodsTableView->setModel(goodsModel);

    QSqlQueryModel *batchModel = Batch::getTableModel();
    ui->warehouseTableView->setModel(batchModel);

    QSqlTableModel *categoryModel = Category::getTableModel();
    categoryModel->select();
    ui->categoryTableView->setModel(categoryModel);

    QSqlTableModel *warningModel = Warning::getTableModel();
    warningModel->removeColumns(0,2);
     warningModel->removeColumn(2);
    warningModel->select();

    ui->warningTableView->setModel(warningModel);
    ui->warningTableView->resizeColumnToContents(0);

    //Initially, set the time of inboundDateTimeEdit and outboundDateTimeEdit to current time
    ui->inboundDateTimeEdit->setDateTime(QDateTime::currentDateTime());
    ui->outboundDateTimeEdit->setDateTime(QDateTime::currentDateTime());

    ui->deTo->setDate(QDate::currentDate());
    ui->deFrom->setDate(QDate::currentDate().addMonths(-1));

    bindCategory();
    bindGoods();


    //Set a timer to check Database
    QTimer *dbCheckTimer = new QTimer(this);

    //Start a task to check database every 6 hours(21600000 ms)
    dbCheckTimer->start(5000);
    connect(dbCheckTimer,SIGNAL(timeout()),this,SLOT(on_dbCheckTimer_timeout()));


    // add status bar message
     statusBar()->showMessage("Status:Now you are in the mainform of Inventory Control System");
     Staff * loginer=new Staff();
     if(!loginer->ismanager)
     {
         ui->statisticsDockWidget->hide();
     }


}


ICSMainForm::~ICSMainForm()
{
    delete ui;
}

void ICSMainForm::on_dbCheckTimer_timeout(){
    qDebug()<<QDateTime::currentDateTime().toString();
    checkoutAllGoodsNearDepletion();



   //Refresh Warning Table Views
    QSqlTableModel *warningModel = Warning::getTableModel();
    warningModel->removeColumns(0,2);
     warningModel->removeColumn(2);
    warningModel->select();

    ui->warningTableView->setModel(warningModel);
    ui->warningTableView->resizeColumnToContents(0);

}

void ICSMainForm::bindCategory()
{
    QSqlTableModel *model = Category::getTableModel();
    model->select();
    ui->comboBox_3->clear();//clear all the comobox items before bind
    ui->comboBox_2->clear();

    for(int i=0; i <model->rowCount();i++)
    {


//      ui->comboBox_3->addItem(model->record(i).value(1).toString(),model->record(i).value(0));//bind name as displayed item, and id as invisible item
        ui->comboBox_2->addItem(model->record(i).value(1).toString(),model->record(i).value(0));
    }

    ui->comboBox_3->setModel(ui->comboBox_2->model());


}


void ICSMainForm::bindGoods()
{
    QSqlQueryModel *model = Goods::getTableModel();

    ui->comboBox->clear();//clear all the comobox items before bind
    ui->comboBox_5->clear();
    ui->comboBox_7->clear();


    for(int i=0; i <model->rowCount();i++)
    {


        ui->comboBox->addItem(model->record(i).value(1).toString(),model->record(i).value(0));//bind name as displayed item, and id as invisible item
//        ui->comboBox_5->addItem(model->record(i).value(1).toString(),model->record(i).value(0));
//        ui->comboBox_7->addItem(model->record(i).value(1).toString(),model->record(i).value(0));
    }

    ui->comboBox_5->setModel(ui->comboBox->model());
    ui->comboBox_7->setModel(ui->comboBox->model());

}

void ICSMainForm::on_pushButton_5_clicked() //add category
{
    QString name = ui->lineEdit_2->text();
       if(name.trimmed()!="")
       {
           Category category;
           category.setName(name);
           if(category.addCategory())
           {
               QMessageBox::warning(this,tr("Succeed!"),tr("Category has been successful added"),QMessageBox::Yes);
               ui->lineEdit_2->clear();
               bindCategory();
               QSqlTableModel *categoryModel = Category::getTableModel();
               categoryModel->select();
               ui->categoryTableView->setModel(categoryModel);
           }
           else
           {

              QMessageBox::warning(this,tr("Failed"),tr("Unknown reasonoperation was failed!"),QMessageBox::Yes);

           }
       }
       else
       {
           QMessageBox::warning(this,tr("Failed"),tr("category name is null, operation was failed!"),QMessageBox::Yes);
       }


}

void ICSMainForm::on_pbtnSatistics_clicked()
{
    QDate df =  ui->deFrom->date();
    QDate dt =ui->deTo->date();
    if(df<=dt)
    {
            ui->lbOutbound->clear();
            ui->lbInbound->clear();

            QString dateFrom = ui->deFrom->date().toString("yyyy-MM-dd");
            QString dateTo = ui->deTo->date().addDays(1).toString("yyyy-MM-dd");
            QString goodsId = ui->comboBox->itemData(ui->comboBox->currentIndex(), Qt::UserRole).toString();
            //    QSqlQuery query;

            //    query.exec("select sum(quantity),sum(quantity *unitprice) from Batch where type=0 and gid ="+goodsId+" and (btime between '"+dateFrom+"' and '"+dateTo+"')");

            //    while(query.next())
            //    {
            //        if(!query.value(0).toString().isNull())
            //        {
            //            ui->lbOutbound->setText(query.value(0).toString()+"items /$"+query.value(1).toString());
            //        }

            //    }
            QSqlQueryModel *model = Goods::statistic(goodsId,dateFrom,dateTo);
            //    qDebug()<<model->record(0).value(0).toString();
            //    qDebug()<<model->record(0).value(1).toString();
            //    qDebug()<<model->record(0).value(2).toString();
            //    qDebug()<<model->record(0).value(3).toString();
            if(!model->record(0).value(0).isNull())
            {
                  ui->lbOutbound->setText(model->record(0).value(0).toString()+ " items /$"+ model->record(0).value(1).toString());
                  ui->lbInbound->setText(model->record(0).value(2).toString()+ " items /$"+ model->record(0).value(3).toString());
            }
            else
            {
                  ui->lbOutbound->setText("0 items /$0");
                  ui->lbInbound->setText("0 items /$0");
            }

            //    query.exec("select sum(quantity),sum(quantity *unitprice) from Batch where type=1 and gid ="+goodsId +" and (btime between '"+dateFrom+"' and '"+dateTo+"')");
            //    while(query.next())
            //    {
            //        if(!query.value(0).toString().isNull())
            //        {
            //            ui->lbInbound->setText(query.value(0).toString()+" items /$"+query.value(1).toString());
            //        }
            //    }
            //    model->clear();

            //    *model= Goods::statistic(goodsId,"1",dateFrom,dateTo);
            //    qDebug()<<model->record(0).value(0).toString();
            //    if(!model->record(0).value(0).isNull())
            //    {
            //        ui->lbInbound->setText(model->record(0).value(0).toString()+ "items /$"+ model->record(0).value(1).toString());
            //    }
      }
      else
     {
            QMessageBox::warning(this,tr("Error"),tr("Starting time selected is greater than ending time! "),QMessageBox::Yes);

     }


}

void ICSMainForm::on_comboBox_3_currentIndexChanged(const QString &arg1) //bind Goods accodring to the category choosed by user
{
    ui->comboBox->clear();
    QString categoryId = ui->comboBox_3->itemData(ui->comboBox_3->currentIndex(), Qt::UserRole).toString();
    QSqlQuery query;
    query.exec("select * from Goods where catid ="+categoryId);
    while(query.next())
    {

        ui->comboBox->addItem(query.value(1).toString(),query.value(0));

    }
}

void ICSMainForm::on_pushButton_4_clicked() //add goods
{
    Goods *g=new Goods();
    if(ui->lineEdit->text()==NULL)
    {
        QMessageBox::warning(this,tr("Warning"),tr("goods name can not be null!"),QMessageBox::Yes);
    }
    else{
       g->setName(ui->lineEdit->text());
       g->setDepletionLine(ui->spinBox_3->value());
       g->setcatid(ui->comboBox_2->itemData(ui->comboBox_2->currentIndex(), Qt::UserRole).toInt());
       if(g->addGoods())
       {
           QSqlQueryModel *model = Goods::getTableModel();
           //model->select();
           ui->goodsTableView->setModel(model);
           ui->goodsTableView->reset();
            qDebug()<<"ok!\n";
           }
    }
}

void ICSMainForm::on_pushButton_6_clicked()
{
    ui->lineEdit_2->clear();
}



void ICSMainForm::on_outboundSubmitButton_clicked() // add warehouse outbound
{
    Batch batch;
    batch.setBatchNumber(ui->spinBox_13->text());
    batch.setBTime(ui->outboundDateTimeEdit->dateTime());
    batch.setGid(ui->comboBox_7->currentIndex() + 1);
    batch.setQuantity(ui->spinBox_12->value());
    batch.setUnitPrice(ui->doubleSpinBox_6->value());
    batch.setExpiredDate(ui->dateEdit_7->date());
    batch.setType(1);

    int gid = batch.getGid();
    Goods *g = new Goods(gid);
    int afterOutboundQuantity = g->getTotalQuantity() - batch.getQuantity();

    if(afterOutboundQuantity>0){


    if(batch.addBatch())
    {
        QMessageBox::warning(this,tr("ok"),tr("Out Bound Successful!"),QMessageBox::Yes);
        bindGoods();
        QSqlQueryModel *model = Batch::getTableModel();

        ui->warehouseTableView->setModel(model);
        ui->warehouseTableView->reset();

        //Update totalquantity of Goods and then refresh GoodsTableView
        Goods::updateGoodsQuantity(gid,afterOutboundQuantity);
        QSqlQueryModel *goodsModel = Goods::getTableModel();

        ui->goodsTableView->setModel(goodsModel);
        ui->goodsTableView->reset();
    }
    else
    {
       QMessageBox::warning(this,tr("failed"),tr("Operation failed!"),QMessageBox::Yes);
    }

    Warning* w = Staff::checkIfGoodsNearDepletion(&batch,g,afterOutboundQuantity);
    if(w!=NULL){
        QSqlTableModel *warningModel = Warning::getTableModel();
        warningModel->removeColumn(1);
        warningModel->removeColumn(3);
        warningModel->select();

        ui->warningTableView->setModel(warningModel);
        ui->warningTableView->resizeColumnToContents(2);
        QMessageBox::warning(this,tr("failed"),w->getWmsg(),QMessageBox::Yes);

    }


    }else{

      QMessageBox::warning(this,tr("failed"),tr("No enough goods to sell!"),QMessageBox::Yes);
    }
}


void ICSMainForm::on_inboundSubmitButton_clicked() //add warehouse inbound
{
    Batch batch;
    QDateTime inboundDateTime = ui->inboundDateTimeEdit->dateTime();
    QDateTime expiredDate = ui->dateEdit_5->dateTime();
    batch.setBatchNumber(ui->spinBox_9->text());
    batch.setBTime(inboundDateTime);
    batch.setGid(ui->comboBox_5->currentIndex() + 1);
    batch.setQuantity(ui->spinBox_8->value());
    batch.setUnitPrice(ui->doubleSpinBox_4->value());
    batch.setExpiredDate(ui->dateEdit_5->date());
    batch.setType(0);
    int gid = batch.getGid();
    if(inboundDateTime<expiredDate){

    if(batch.addBatch())
    {
        QMessageBox::warning(this,tr("ok"),tr("Inbound Successful!"),QMessageBox::Yes);
        bindGoods();
        QSqlQueryModel *model = Batch::getTableModel();

        ui->warehouseTableView->setModel(model);
        ui->warehouseTableView->reset();

        //Update totalquantity of Goods and then refresh GoodsTableView
        Goods *g = new Goods(gid);
        int currentQuantity = batch.getQuantity() + g->getTotalQuantity();
        Goods::updateGoodsQuantity(gid,currentQuantity);
        QSqlQueryModel *goodsModel = Goods::getTableModel();

        ui->goodsTableView->setModel(goodsModel);
        ui->goodsTableView->reset();






    }
    else
    {
       QMessageBox::warning(this,tr("failed"),tr("Operation failed!"),QMessageBox::Yes);
    }

    }else{

     QMessageBox::warning(this,tr("failed"),tr("You cannot buy this batch of goods Because the goods has been expired!"),QMessageBox::Yes);
    }
}


void ICSMainForm::on_pushButton_3_clicked()
{
    ui->lineEdit->clear();
}
