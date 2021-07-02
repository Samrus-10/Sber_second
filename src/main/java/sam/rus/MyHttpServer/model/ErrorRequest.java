package sam.rus.MyHttpServer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ErrorRequest {
    private String reason;

    public ErrorRequest() {
    }

    public ErrorRequest(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
