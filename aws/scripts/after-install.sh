#!/bin/bash
set -xe
echo "$(date) - Starting the after-install script" >> command-execution.log
# Check if Java 17 is already installed
if ! command -v java &>/dev/null; then
    echo "Java is not installed. Installing Java 17..."

    # Update package lists
    sudo apt-get update

    # Install Java 17
    sudo apt-get install -y openjdk-17-jdk

    # Verify Java installation
    java -version

    echo "Java 17 installation completed."
else
    echo "Java is already installed. Skipping Java 17 installation."
fi

# Copy the JAR file to the /usr/local/codedeployresources directory.
aws s3 cp s3://pcshopwebsite-artifacts/PCShopApp-0.0.1.jar /usr/local/codedeployresources/PCShopApp-0.0.1.jar