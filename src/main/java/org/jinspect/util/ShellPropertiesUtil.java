package org.jinspect.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShellPropertiesUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ShellPropertiesUtil.class);
	
	private static ShellPropertiesUtil instance = new ShellPropertiesUtil();
	private Properties props = new Properties();
	private ShellPropertiesUtil(){
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("shell.properties");
			props.load(inputStream);
		} catch (IOException e) {
			logger.error("load shell.properties error!", e);
		} finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// do nothing
				}
			}
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
