package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    // Static block to load properties when the class is loaded
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/testData.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage(), e);
        }
    }

    // Method to get property value by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
