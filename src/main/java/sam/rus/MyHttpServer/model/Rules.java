package sam.rus.MyHttpServer.model;

public enum Rules {
    VISITOR("visitor"),
    USER("user"),
    ADMIN("admin");

    private java.lang.String rule;

    Rules(java.lang.String rule) {
        this.rule = rule;
    }
}
