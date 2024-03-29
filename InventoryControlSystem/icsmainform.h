#ifndef ICSMAINFORM_H
#define ICSMAINFORM_H

#include <QMainWindow>

namespace Ui {
    class ICSMainForm;
}

class ICSMainForm : public QMainWindow
{
    Q_OBJECT

public:
    explicit ICSMainForm(QWidget *parent = 0);
    ~ICSMainForm();
    void bindCategory();
    void bindGoods();

private slots:
    void on_pushButton_5_clicked();

    void on_pbtnSatistics_clicked();

    void on_comboBox_3_currentIndexChanged(const QString &arg1);

    void on_pushButton_4_clicked();

    void on_pushButton_6_clicked();

    void on_outboundSubmitButton_clicked();

    void on_inboundSubmitButton_clicked();

    void on_dbCheckTimer_timeout();

    void on_pushButton_3_clicked();

private:
    Ui::ICSMainForm *ui;

};

#endif // ICSMAINFORM_H
