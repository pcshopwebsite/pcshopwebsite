#!/bin/bash
set -xe

APP_NAME="PCShopApp"
JAR_FILE="PCShopApp-0.0.1.jar"
LOG_FILE="application.log"

# Check if the app is already running
if pgrep -f $JAR_FILE >/dev/null; then
    echo "The $APP_NAME is already running. Will terminate"
    ./application-stop.sh
fi

# Start the app in the background
nohup java -jar /usr/local/codedeployresources/$JAR_FILE --spring.profiles.active=dev > $LOG_FILE 2>&1 &

echo "Starting $APP_NAME..."
echo "The application logs are being written to $LOG_FILE."
