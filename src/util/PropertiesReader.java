package util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	private Properties properties;
	
	public PropertiesReader() {
		properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}