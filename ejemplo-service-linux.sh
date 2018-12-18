#!/bin/sh
SERVICE_NAME=$MyService
PATH_TO_JAR_FOLDER=$MyServiceFolder
JAR_ARGUMENTS=
PID_PATH_NAME=/tmp/$SERVICE_NAME-pid
PATH_TO_LOGS=/logs/$SERVICE_NAME.log
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            cd $PATH_TO_JAR_FOLDER
            source env-file
            nohup java -jar "$SERVICE_NAME.jar $JAR_ARGUMENTS"  >> $PATH_TO_LOGS 2>&1& echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            cd $PATH_TO_JAR_FOLDER
            source env-file
            nohup java -jar "$SERVICE_NAME.jar $JAR_ARGUMENTS"  >> $PATH_TO_LOGS 2>&1& echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac