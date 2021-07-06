package sam.rus.MyHttpServer.model;

public enum Rules {
    VISITOR("visitor"),
    USER("user"),
    ADMIN("admin");

    private java.lang.String rule;

    Rules(java.lang.String rule) {
        this.rule = rule;
    }

    public boolean chek(String str){
        boolean result = false;
        if(str.equals("visitor")){
            if (this.rule.equals("visitore")) {
                result = true;
            }
        }else if(str.equals("user")){
            if (this.rule.equals("visitore") || this.rule.equals("user")) {
                result = true;
            }
        }else if(str.equals("admin")){
            result = true;
        }
        return result;
    }
}
