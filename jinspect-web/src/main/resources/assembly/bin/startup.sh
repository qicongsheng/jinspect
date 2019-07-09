#!/bin/sh

# -----------------------------------------------------------------------------
# Start script for the JInspect Server
# -----------------------------------------------------------------------------

java -cp $JAVA_HOME/lib/tools.jar;../lib/jinspect-web.jar;../lib/jinspect-core.jar org.springframework.boot.loader.JarLauncher

