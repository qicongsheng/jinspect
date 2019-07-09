@echo off
rem ---------------------------------------------------------------------------
rem Start script for the JInspect Server
rem ---------------------------------------------------------------------------

java -cp %JAVA_HOME%/lib/tools.jar;../lib/jinspect-web.jar;../lib/jinspect-core.jar -Dlog4j.config.file=../conf/log4j.properties org.springframework.boot.loader.JarLauncher

@echo on
