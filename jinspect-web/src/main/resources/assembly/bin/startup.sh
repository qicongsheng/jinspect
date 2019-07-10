#!/bin/sh

# -----------------------------------------------------------------------------
# Start script for the JInspect Server
# -----------------------------------------------------------------------------

CURRENT_DIR=`pwd`
JINSPECT_HOME=`dirname "$CURRENT_DIR"`

java "-Duser.dir=$JINSPECT_HOME" "-Dloader.path=$JAVA_HOME/lib/tools.jar,$JINSPECT_HOME/lib/jinspect-core.jar" "-Dlog4j.config.file=$JINSPECT_HOME/conf/log4j.properties" -jar $JINSPECT_HOME/lib/jinspect-web.jar


