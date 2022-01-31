package utils;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

public class ApplicationProperties {

    private static Properties instance = null;
    private static final String APLICCATION_PREFIX = "application";
    private static final String APLICCATION_SUFFIX = "properties";
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);

    private ApplicationProperties(){}

    public static synchronized Properties getInstance(){
        if(instance == null){
            instance = loadPropertiesFile();
        }
        return instance;
    }

     private static Properties loadPropertiesFile(){
        String enviroment = Optional.ofNullable(System.getenv("env")).orElse("dev");
        String fileName = String.format("%s-%s.%s", APLICCATION_PREFIX, enviroment, APLICCATION_SUFFIX);

        logger.info("Property file to read {}", fileName);

        Properties prop = new Properties();

        try {
            prop.load(getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("failed to load {}", fileName);
        }
        return prop;
    }

}
