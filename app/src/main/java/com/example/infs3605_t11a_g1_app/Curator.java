package com.example.infs3605_t11a_g1_app;

public class Curator extends User {
    String curatorName;
    String curatorChallenge;
    String reason;

    public Curator(String curatorName, String curatorChallenge, String reason) {
        this.curatorName = curatorName;
        this.curatorChallenge = curatorChallenge;
        this.reason = reason;
    }

    public String getCuratorName() {
        return curatorName;
    }

    public void setCuratorName(String curatorName) {
        this.curatorName = curatorName;
    }

    public String getCuratorChallenge() {
        return curatorChallenge;
    }

    public void setCuratorChallenge(String curatorChallenge) {
        this.curatorChallenge = curatorChallenge;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
