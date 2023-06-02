#!/bin/bash
set -xe
echo "$(date) - Starting the application-stop script" >> command-execution.log
APP_NAME="PCShopApp"
JAR_FILE="PCShopApp-0.0.1.jar"

# Check if the app is running
if ! pgrep -f $JAR_FILE >/dev/null; then
    echo "The $APP_NAME is not running."
    exit 0
fi

# Find and kill the app process
PID=$(pgrep -f $JAR_FILE)
kill $PID

echo "Stopping $APP_NAME (PID: $PID)..."
