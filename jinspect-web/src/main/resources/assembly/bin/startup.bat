@echo off
rem ---------------------------------------------------------------------------
rem Start script for the JInspect Server
rem ---------------------------------------------------------------------------

set "CURRENT_DIR=%cd%"
cd ..
set "JINSPECT_HOME=%cd%"

java "-Duser.dir=%JINSPECT_HOME%" "-Dloader.path=%JAVA_HOME%/lib/tools.jar,%JINSPECT_HOME%/lib/jinspect-core.jar" "-Dlog4j.config.file=%JINSPECT_HOME%/conf/log4j.properties" "-Dsigar.lib.path=%JINSPECT_HOME%/lib/sigar" -jar %JINSPECT_HOME%/lib/jinspect-web.jar

@echo on
