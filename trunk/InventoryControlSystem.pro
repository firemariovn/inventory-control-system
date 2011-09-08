#-------------------------------------------------
#
# Project created by QtCreator 2011-08-29T17:15:18
#
#-------------------------------------------------

QT       += core gui
QT       += sql

TARGET = InventoryControlSystem
TEMPLATE = app


SOURCES += main.cpp\
    icsloginform.cpp \
    icsmainform.cpp \
    goods.cpp \
    staff.cpp \
    category.cpp \
    warning.cpp

HEADERS  += \
    icsloginform.h \
    icsmainform.h \
    database.h \
    goods.h \
    staff.h \
    batch.h \
    category.h \
    warning.h

FORMS    += \
    icsloginform.ui \
    icsmainform.ui
