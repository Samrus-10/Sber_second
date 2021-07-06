package sam.rus.MyHttpServer.model;

public class TokenDTO implements JsonConvert {
    private long userId;
    private String data;
    private String exp;
    private String rule;
    private String login;

    public TokenDTO() {
    }

    public TokenDTO(long userId, String data, String exp, String rule, String login) {
        this.userId = userId;
        this.data = data;
        this.exp = exp;
        this.rule = rule;
        this.login = login;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "userId=" + userId +
                ", data='" + data + '\'' +
                ", exp='" + exp + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
