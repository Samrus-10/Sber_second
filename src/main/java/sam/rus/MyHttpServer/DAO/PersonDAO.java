package sam.rus.MyHttpServer.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PersonDAO {
    private Connection connection;

    private PersonDAO(String urlBD, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(urlBD, user, password);
    }
    private void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static PersonDAO getInstance(String urlBD, String user, String password) {
        PersonDAO result = null;
        try {
            result = new PersonDAO(urlBD, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public
}
