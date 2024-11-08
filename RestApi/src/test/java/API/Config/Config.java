package API.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static final Properties properties = new Properties();

	static {
		try {
			FileInputStream file = new FileInputStream("src/test/resources/routes.properties");
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
