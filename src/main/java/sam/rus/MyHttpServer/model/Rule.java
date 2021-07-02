package sam.rus.MyHttpServer.model;

public enum Rule {
    VISITOR("visitor"),
    USER("user"),
        ADMIN("admin");

    private String rule;

    Rule(String rule) {
        this.rule = rule;
    }
}
