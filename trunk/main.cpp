#include <QtGui/QApplication>
#include "icsmainform.h"
#include "icsloginform.h"
#include "database.h"
#include "staff.h"
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    if(!createConnection()){
     return 0;
    }


    ICSLoginForm loginForm;


    if(loginForm.exec()==QDialog::Accepted){
        ICSMainForm  mainForm;
        //Staff *loginer=new Staff();

        mainForm.show();
        return a.exec();
    }
    else
    return 0;
}
