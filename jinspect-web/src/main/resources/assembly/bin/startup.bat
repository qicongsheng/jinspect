@echo off
rem ---------------------------------------------------------------------------
rem Start script for the JInspect Server
rem ---------------------------------------------------------------------------

java -Xbootclasspath/a:../lib/jinspect-core.jar -jar ../lib/jinspect-web.jar

@echo on