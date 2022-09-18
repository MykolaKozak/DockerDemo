package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    private static void loadProperties() {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/java/config/properties.properties"))) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public  static String getProperty(String property) {
        loadProperties();
        return properties.getProperty(property);
    }

    public void setProperty(String key, String value) {
        loadProperties();
        properties.setProperty(key, value);
    }

}
