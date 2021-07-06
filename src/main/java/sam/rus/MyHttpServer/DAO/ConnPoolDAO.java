package sam.rus.MyHttpServer.DAO;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnPoolDAO {
    private static String JDBC_DRIVER;
    private static String JDBC_DB_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static JdbcConnectionPool connectionPool;

    private ConnPoolDAO() {

    }


    private static void init() {
        //Properties properties = ReaderBDProperties.getProperties("/home/sam/кайф/SberWork/TestBankAPI/Sber_second/src/main/resources/connectionPool.properties");
        Properties properties = ReaderBDProperties.getProperties("/Users/u19305165/IdeaProjects/soketExampleServer/src/main/resources/connectionPool.properties");
        JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
        JDBC_DB_URL = properties.getProperty("JDBC_DB_URL");
        JDBC_USER = properties.getProperty("JDBC_USER");
        JDBC_PASS = properties.getProperty("JDBC_PASS");
        connectionPool = JdbcConnectionPool.create(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
        updateBD(connectionPool);
    }

    private static void updateBD(JdbcConnectionPool connectionPool) {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connectionPool == null) {
            init();
        }
        Connection result = null;
        try {
            result = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean closeConnectionPool() {
        boolean result = false;
        if (connectionPool != null) {
            connectionPool.dispose();
            result = true;
        }
        return result;
    }
}
