package org.javeriana.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WorldConfiguration {

    private static final Logger logger = LogManager.getLogger(WorldConfiguration.class);
    private static final String CONF_NAME = "app.properties";

    private static WorldConfiguration instance = null;
    private Properties appProperties;

    private WorldConfiguration() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream in = classLoader.getResourceAsStream(CONF_NAME);
            this.appProperties = new Properties();
            this.appProperties.load(in);
        }  catch (IOException e) {
            logger.error("No app config file found!!");
            throw new RuntimeException(e);
        }
    }

    public static WorldConfiguration getPropsInstance() {
        if (instance == null) {
            instance = new WorldConfiguration();
        }
        return instance;
    }

    public String getProperty(String key) {
        return this.appProperties.getProperty(key);
    }
}
