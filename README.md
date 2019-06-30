# jinspect
1.dependency
	a.maven3+
	b.jdk1.7+
2.compile source code
	a.cd jinspect
	b.mvn clean compile
	c.jinspect.jar file will be in target directory
3.start up jinspect
	a.starting up with default port 9088
		java -jar jinspect.jar 
	b.starting up with customer port {CUSTOMER_PORT}
		java -jar jinspect.jar --server.port={CUSTOMER_PORT}
		
4. http://<ip>:9088 or http://<ip>:{CUSTOMER_PORT}

