# JInspect

Author: Qi Congsheng
Date: 6/30/2019

## Description: 
This program start a web application with default port 9088 and offer those functions
1. Show the states(cpu usage percent,memory usage, etc.) of specific JVM' threads.
2. Order by cpu usage percent, memory usage when showing threads state.
3. Show the specific thread stack details.
4. GC state analyse.(TODO)
5. Deadlock thread analyse.(TODO)

## Limits:
Linux only(for now)

## Dependency:
compile dependency
```
maven3(+)
```
runtime dependency
```
jdk1.7+
```
## To compile:
1. Install maven3(+)
2. Download source code zip file
3. cd jinspect
4. mvn clean package
5. jinspect.jar file will be in "target" directory

## Start up:
starting up with default port 9088
```
java -jar jinspect.jar 
```
starting up with customer port {CUSTOMER_PORT}
```
java -jar jinspect.jar --server.port={CUSTOMER_PORT}
```

## Analysing
default port(9088) URL
```
http://<ip>:9088
```
customer port URL
```
http://<ip>:{CUSTOMER_PORT}
```

