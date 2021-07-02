package sam.rus.MyHttpServer.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnPoolDAO {
    private static final String JDBC_DRIVER;
    private static final String JDBC_DB_URL;
    private static final String JDBC_USER;
    private static final String JDBC_PASS;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/Users/u19305165/IdeaProjects/soketExampleServer/src/main/resources/connectionPool.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
        JDBC_DB_URL= properties.getProperty("JDBC_DB_URL");
        JDBC_USER = properties.getProperty("JDBC_USER");
        JDBC_PASS = properties.getProperty("JDBC_PASS");
    }
}
