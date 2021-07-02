package sam.rus.MyHttpServer.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReaderBDProperties {
    private static final String pathToFileProperties = "/Users/u19305165/IdeaProjects/soketExampleServer/src/main/resources/connectionPool.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        properties.load(new FileInputStream(pathToFileProperties));
    }

    private void loadPropertiesFile(Properties properties) {
        try {
            properties.load(new FileInputStream(pathToFileProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
