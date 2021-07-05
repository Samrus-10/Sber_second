package sam.rus.MyHttpServer.model;

public class User {
    private long id;
    private java.lang.String login;
    private java.lang.String password;
    private String rule;
    private java.lang.String token;
    private java.lang.String refreshToken;

    public User() {
    }

    public User(long id, java.lang.String login, java.lang.String password, String rule, java.lang.String token, java.lang.String refreshToken) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rule = rule;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public java.lang.String getLogin() {
        return login;
    }

    public void setLogin(java.lang.String login) {
        this.login = login;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public java.lang.String getToken() {
        return token;
    }

    public void setToken(java.lang.String token) {
        this.token = token;
    }

    public java.lang.String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(java.lang.String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
