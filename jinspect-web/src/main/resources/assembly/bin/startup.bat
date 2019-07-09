@echo off
rem ---------------------------------------------------------------------------
rem Start script for the JInspect Server
rem ---------------------------------------------------------------------------

java -cp %JAVA_HOME%/lib/tools.jar;../lib/jinspect-web.jar;../lib/jinspect-core.jar org.springframework.boot.loader.JarLauncher

@echo on
