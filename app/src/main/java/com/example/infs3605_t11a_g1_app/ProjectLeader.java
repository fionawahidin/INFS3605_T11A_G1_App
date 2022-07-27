package com.example.infs3605_t11a_g1_app;

public class ProjectLeader {
    private String name;
    private String solutionName;
    private String challengeSpin;
    private String desc;
    private String kpiOneSpin;
    private String kpiOne;
    private String kpiTwoSpin;
    private String kpiTwo;

    public ProjectLeader() {

    }

    public ProjectLeader(String name, String solutionName, String challengeSpin, String desc, String kpiOneSpin, String kpiOne, String kpiTwoSpin, String kpiTwo) {
        this.name = name;
        this.solutionName = solutionName;
        this.challengeSpin = challengeSpin;
        this.desc = desc;
        this.kpiOneSpin = kpiOneSpin;
        this.kpiOne = kpiOne;
        this.kpiTwoSpin = kpiTwoSpin;
        this.kpiTwo = kpiTwo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getChallengeSpin() {
        return challengeSpin;
    }

    public void setChallengeSpin(String challengeSpin) {
        this.challengeSpin = challengeSpin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKpiOneSpin() {
        return kpiOneSpin;
    }

    public void setKpiOneSpin(String kpiOneSpin) {
        this.kpiOneSpin = kpiOneSpin;
    }

    public String getKpiOne() {
        return kpiOne;
    }

    public void setKpiOne(String kpiOne) {
        this.kpiOne = kpiOne;
    }

    public String getKpiTwoSpin() {
        return kpiTwoSpin;
    }

    public void setKpiTwoSpin(String kpiTwoSpin) {
        this.kpiTwoSpin = kpiTwoSpin;
    }

    public String getKpiTwo() {
        return kpiTwo;
    }

    public void setKpiTwo(String kpiTwo) {
        this.kpiTwo = kpiTwo;
    }
}
