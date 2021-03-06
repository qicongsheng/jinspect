[English](https://github.com/qicongsheng/jinspect) &nbsp; 
[简体中文](https://github.com/qicongsheng/jinspect/blob/master/README_CN.md)
# JInspect

Author: Qi Congsheng <br>
Email: 875881559@qq.com <br>

## Description: 
This program starts a web application with default port 9088. No invasion of the target JVM process and offering these functions
* Show the states(cpu usage percent,memory usage, etc.) of specific JVM' threads.
* Order by cpu usage percent,memory usage when showing thread state.
* Show the specific thread stack details.
* GC state analyse.(TODO)
* Deadlock thread analyse.(TODO)

## Limits:
Linux only(for now)

## Dependency:
#### compile dependency:
* jdk1.7+
* maven3(+)<br>
#### Runtime dependency:
* jdk1.7+

## To compile:
1. Install maven3(+)
2. Download source code zip file and decompress it
3. cd jinspect
4. mvn clean package
5. jinspect.jar file will be in "target" directory
#### [Download jinspect.jar directly](https://raw.githubusercontent.com/qicongsheng/warehouse/master/jinspect/jinspect.jar)


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

