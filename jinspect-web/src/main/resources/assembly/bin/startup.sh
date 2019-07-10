#!/bin/sh

# -----------------------------------------------------------------------------
# Start script for the JInspect Server
# -----------------------------------------------------------------------------

PRG="$0"

while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

PRGDIR=`dirname "$PRG"`

java "-Dloader.path=$JAVA_HOME/lib/tools.jar,$PRGDIR/lib/jinspect-core.jar" -jar $PRGDIR/lib/jinspect-web.jar

