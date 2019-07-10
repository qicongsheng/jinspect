package org.jinspect;

import org.apache.log4j.PropertyConfigurator;
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
    	printSystemProperty();
        SpringApplication.run(SpringBootApplicationMain.class, args);
    }
    
    private static void setCustomerConfig(){
    	String logConfFilePath = System.getProperty("log4j.config.file");
    	if(logConfFilePath != null && !"".equals(logConfFilePath)){
    		PropertyConfigurator.configure(logConfFilePath);
    	}
    }
    
    private static void printSystemProperty(){
    	System.out.println("System Property : [user.dir=" + System.getProperty("user.dir") + "]");
    	System.out.println("System Property : [loader.path=" + System.getProperty("loader.path") + "]");
		System.out.println("System Property : [log4j.config.file=" + System.getProperty("log4j.config.file") + "]");
    }
    
}
