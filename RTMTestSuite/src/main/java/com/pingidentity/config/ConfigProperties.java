package com.pingidentity.config;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import java.io.IOException;

public class ConfigProperties {
	
	Properties properties;
	
	private static String baseUrl;
	private String browser;
	private String username;
	private String password;
	
	private static final String ENVIRONMENTAL_PATH = "config.properties";
	private static ConfigProperties instance = new ConfigProperties();
	
	protected ConfigProperties() {
		loadConfigurationFromPropertyFiles();
	}
	
	public static ConfigProperties getInstance() {
		return instance;
	}
	
	protected void loadConfigurationFromPropertyFiles() {
		properties = new Properties();
		loadEnvironmentConfigFromPropertyFiles();
		extractProperties();
	}
	
	protected void loadEnvironmentConfigFromPropertyFiles() {	
		InputStream input=null;
		
		try {
			input = this.getClass().getClassLoader().getResourceAsStream(ENVIRONMENTAL_PATH);	
			properties.load(input);
					
		} catch (IOException ioExcept) {
			ioExcept.printStackTrace();
		} finally {
			if (input!=null) {
				try {
					input.close();
				} catch (IOException ioExcept) {
					ioExcept.printStackTrace();
				}
			} 
		}
	} 
	
	protected void extractProperties() {
		baseUrl = (String) properties.get("base.url");
	    browser = (String) properties.get("browser");;
	    username = (String) properties.get("user.username");
		password = (String) properties.get("user.password");
	}

	public static String getBaseUrl() {
		return baseUrl;
	}

	public String getBrowser() {
		return browser;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}