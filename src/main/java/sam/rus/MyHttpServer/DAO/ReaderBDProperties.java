package sam.rus.MyHttpServer.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ReaderBDProperties {
    private static Properties properties;

    public static Properties getProperties(String path){
        properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
