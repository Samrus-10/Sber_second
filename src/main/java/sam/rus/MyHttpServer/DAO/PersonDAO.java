package sam.rus.MyHttpServer.DAO;

import sam.rus.MyHttpServer.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class PersonDAO {

    private PersonDAO() {
    }

    public static boolean enter(String login, String password) {
        boolean result = false;
        try (
                Connection connection = ConnPoolDAO.getConnection();
                PreparedStatement statement = connection.prepareStatement("select * from users where login = ? and password = ?;");
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            List<User> listUser = new ArrayList<>();
            while (resultSet.next()) {
                listUser.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("rule"),
                        resultSet.getString("token"),
                        resultSet.getString("token_refresh")
                        ));
            }
            if (listUser.size() == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
