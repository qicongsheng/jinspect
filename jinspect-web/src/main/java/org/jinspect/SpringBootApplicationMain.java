package org.jinspect;

import org.apache.log4j.PropertyConfigurator;
import org.jinspect.core.common.SigarContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootApplicationMain extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApplicationMain.class);
	}
	
    public static void main(String[] args) {
    	setCustomerConfig();
        SpringApplication.run(SpringBootApplicationMain.class, args);
    }
    
    private static void setCustomerConfig(){
    	String logConfFilePath = System.getProperty("log4j.config.file");
    	if(logConfFilePath != null && !"".equals(logConfFilePath)){
    		PropertyConfigurator.configure(logConfFilePath);
    	}
    	
    	String sigarLibPath = System.getProperty("sigar.lib.path");
    	if(sigarLibPath != null && !"".equals(sigarLibPath)){
    		SigarContextBuilder.initSigarContext(sigarLibPath);
    	}
    }
    
}
