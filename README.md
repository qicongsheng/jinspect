# JInspect

Author: Qi Congsheng <br>
Email: 875881559@qq.com <br>
Date: 6/30/2019

## Description: 
This program start a web application with default port 9088 and offer those functions
* Show the states(cpu usage percent,memory usage, etc.) of specific JVM' threads.
* Order by cpu usage percent, memory usage when showing thread state.
* Show the specific thread stack details.
* GC state analyse.(TODO)
* Deadlock thread analyse.(TODO)

## Limits:
Linux only(for now)

## Dependency:
#### compile dependency
* jdk1.7+
* maven3(+)<br>
#### Runtime dependency:
* jdk1.7+

## To compile([Download jinspect.jar directly](//handsontable.com/examples)):
1. Install maven3(+)
2. Download source code zip file
3. cd jinspect
4. mvn clean package
5. jinspect.jar file will be in "target" directory

## Starting up:
#### Starting up with default port 9088
```
java -jar jinspect.jar 
```
#### Starting up with customer port {CUSTOMER_PORT}
```
java -jar jinspect.jar --server.port={CUSTOMER_PORT}
```

## Analysing
#### default port(9088) URL
```
http://<ip>:9088
```
#### customer port URL
```
http://<ip>:{CUSTOMER_PORT}
```

