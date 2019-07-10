@echo off
rem ---------------------------------------------------------------------------
rem Start script for the JInspect Server
rem ---------------------------------------------------------------------------

set "CURRENT_DIR=%cd%"
cd ..
set "JINSPECT_HOME=%cd%"

java "-Dloader.path=%JAVA_HOME%/lib/tools.jar,%JINSPECT_HOME%/lib/jinspect-core.jar" -jar %JINSPECT_HOME%/lib/jinspect-web.jar

@echo on
