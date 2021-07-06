package sam.rus.MyHttpServer.DAO;

import sam.rus.MyHttpServer.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

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
//                        resultSet.getString("token"),
//                        resultSet.getString("token_refresh")
                        null,
                        null
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

    public static User getUser(String login, String password) {
        User user = null;
        try (
                Connection connection = ConnPoolDAO.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login = ? and password = ?;");
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("rule"),
                        null,
                        null
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean setToken(String idUser, String tokenRefresh, String tokenAccess) {
        boolean result = false;
        try (
                Connection connection = ConnPoolDAO.getConnection();
                PreparedStatement deletStatement = connection.prepareStatement(
                        "DELETE FROM access_user WHERE id_user = ?");
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO access_user(id_user,token_refresh,token_access)VALUES(?,?,?)");
        ) {
            deletStatement.setInt(1, parseInt(idUser));
            result = deletStatement.execute();
            System.out.println(result);
            preparedStatement.setInt(1, parseInt(idUser));
            preparedStatement.setString(2, tokenRefresh);
            preparedStatement.setString(3, tokenAccess);
            result = preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getToken(int idUser, String typeToken) {
        String result = "";
        try (
                Connection connection = ConnPoolDAO.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from access_user where id_user = ?");
        ) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(typeToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteToken(int idUser) {
        boolean result = false;
        try (
                Connection connection = ConnPoolDAO.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM access_user where id_user = ?");
        ) {
            preparedStatement.setInt(1, idUser);
            result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
