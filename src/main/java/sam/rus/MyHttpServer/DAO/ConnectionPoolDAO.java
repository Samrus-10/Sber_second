package sam.rus.MyHttpServer.DAO;

public final class ConnectionPoolDAO {
    private static ConnectionPoolDAO instance;

    private ConnectionPoolDAO() {

    }

    public static synchronized ConnectionPoolDAO getInstance() {
        if (instance == null) {
            synchronized (ConnectionPoolDAO.class) {
                if (instance == null) {
                    instance = new ConnectionPoolDAO();
                }
            }
        }
        return instance;
    }
}
