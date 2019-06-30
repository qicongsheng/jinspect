# JInspect

作者: 祁丛生 <br>
邮箱: 875881559@qq.com <br>
日期: 6/30/2019

## 描述: 
This program starts a web application with default port 9088 and offer these functions
* Show the states(cpu usage percent,memory usage, etc.) of specific JVM' threads.
* Order by cpu usage percent, memory usage when showing thread state.
* Show the specific thread stack details.
* GC state analyse.(TODO)
* Deadlock thread analyse.(TODO)

## 限制:
只支持Linux(目前)

## 依赖:
#### 编译依赖:
* jdk1.7+
* maven3(+)<br>
#### 运行时依赖:
* jdk1.7+

## 编译:
1. 安装maven3(+)
2. 下载zip源码文件并解压
3. cd jinspect
4. mvn clean package
5. jinspect.jar生成在"target"文件夹
#### [直接下载jinspect.jar](https://raw.githubusercontent.com/qicongsheng/warehouse/master/jinspect/jinspect.jar)


## 启动:
#### 默认端口9088启动
```
java -jar jinspect.jar 
```
#### 自定义端口{CUSTOMER_PORT}启动
```
java -jar jinspect.jar --server.port={CUSTOMER_PORT}
```

## 分析
#### 默认端口(9088)地址
```
http://<ip>:9088
```
#### 自定义端口地址
```
http://<ip>:{CUSTOMER_PORT}
```

