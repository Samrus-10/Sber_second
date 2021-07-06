package sam.rus.MyHttpServer.Util;

import sam.rus.MyHttpServer.DAO.PersonDAO;
import sam.rus.MyHttpServer.model.Rules;
import sam.rus.MyHttpServer.model.User;

import java.util.HashMap;
import java.util.Map;

public class AccessRule {
    private Map<String, Rules> mapRule;

    public AccessRule() {
        this.mapRule = new HashMap<>();
        {
            mapRule.put("cards", Rules.USER);
        }
    }

    public AccessRule(Map<String, Rules> mapRule) {
        this.mapRule = mapRule;
    }

    public boolean checkAccess(String rule, String path) {
        return  this.mapRule.get(path).chek(rule);
    }

    public boolean checkAccess(String login, String password, String path) {
        User user = PersonDAO.getUser(login, password);
        return this.mapRule.get(path).chek(user.getRule());
    }
}
