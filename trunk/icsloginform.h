#ifndef ICSLOGINFORM_H
#define ICSLOGINFORM_H

#include <QDialog>

namespace Ui {
    class ICSLoginForm;
}

class ICSLoginForm : public QDialog
{
    Q_OBJECT

public:
    explicit ICSLoginForm(QWidget *parent = 0);
    ~ICSLoginForm();

private slots:
    void on_loginButton_clicked();

private:
    Ui::ICSLoginForm *ui;
};

#endif // ICSLOGINFORM_H
