#ifndef ICSMAINFORM_H
#define ICSMAINFORM_H

#include <QWidget>

namespace Ui {
    class ICSMainForm;
}

class ICSMainForm : public QWidget
{
    Q_OBJECT

public:
    explicit ICSMainForm(QWidget *parent = 0);
    ~ICSMainForm();

private:
    Ui::ICSMainForm *ui;
};

#endif // ICSMAINFORM_H
