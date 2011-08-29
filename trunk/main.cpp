#include <QtGui/QApplication>
#include "icsmainform.h"
#include "icsloginform.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    ICSMainForm  mainForm;
    ICSLoginForm loginForm;
    if(loginForm.exec()==QDialog::Accepted){
        mainForm.show();
        return a.exec();
    }
    else
    return 0;
}
