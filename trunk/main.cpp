#include <QtGui/QApplication>
#include "icsmainform.h"
#include "icsloginform.h"
#include "database.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    if(!createConnection()){
     return 0;
    }

    ICSMainForm  mainForm;
    ICSLoginForm loginForm;


    if(loginForm.exec()==QDialog::Accepted){
        mainForm.show();
        return a.exec();
    }
    else
    return 0;
}
