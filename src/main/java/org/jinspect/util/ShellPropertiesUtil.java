package org.jinspect.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShellPropertiesUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ShellPropertiesUtil.class);
	
	private static ShellPropertiesUtil instance = new ShellPropertiesUtil();
	private Properties props = new Properties();
	private ShellPropertiesUtil(){
		try {
			props.load(ShellPropertiesUtil.class.getResourceAsStream("shell.properties"));
		} catch (IOException e) {
			logger.error("load shell.properties error!", e);
		}
	}
	
	public static ShellPropertiesUtil getInstance(){
		return instance;
	}
	
	public String getProperty(String key, String defaultValue){
		return props.getProperty(key, defaultValue);
	}
	
	public String getProperty(String key){
		return props.getProperty(key);
	}
}
