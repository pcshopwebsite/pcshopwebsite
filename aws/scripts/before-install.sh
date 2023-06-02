#!/bin/bash
set -xe
echo "$(date) - Starting the before-install script" >> command-execution.log
# Delete the old  directory as needed.
if [ -d /usr/local/codedeployresources ]; then
    rm -rf /usr/local/codedeployresources/
fi

mkdir -vp /usr/local/codedeployresources
