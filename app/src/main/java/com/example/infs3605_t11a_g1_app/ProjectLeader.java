package com.example.infs3605_t11a_g1_app;

import android.net.Uri;

public class ProjectLeader extends User {
    private String name;
    private String solutionName;
    private String challengeSpin;
    private String desc;
    private String kpiOneSpin;
    private String kpiOne;
    private String kpiTwoSpin;
    private String kpiTwo;
    private String targetOne;
    private String targetTwo;
    private String currentOne;
    private String currentTwo;
    private Uri imageuri;
    private String impactScore;
    private String baselineAchieve;

    private boolean checkbox;

    public ProjectLeader(String name, String solutionName, String challengeSpin, String desc,
                         String kpiOneSpin, String kpiOne, String kpiTwoSpin, String kpiTwo,
                         String targetOne, String targetTwo, String currentOne, String currentTwo,
                         Uri imageuri, String impactScore, String baselineAchieve, boolean checkbox) {
        this.name = name;
        this.solutionName = solutionName;
        this.challengeSpin = challengeSpin;
        this.desc = desc;
        this.kpiOneSpin = kpiOneSpin;
        this.kpiOne = kpiOne;
        this.kpiTwoSpin = kpiTwoSpin;
        this.kpiTwo = kpiTwo;
        this.targetOne = targetOne;
        this.targetTwo = targetTwo;
        this.currentOne = currentOne;
        this.currentTwo = currentTwo;
        this.imageuri = imageuri;
        this.impactScore = impactScore;
        this.baselineAchieve = baselineAchieve;
        this.checkbox = checkbox;
    }

    public ProjectLeader() {
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

    public String getTargetOne() {
        return targetOne;
    }

    public void setTargetOne(String targetOne) {
        this.targetOne = targetOne;
    }

    public String getTargetTwo() {
        return targetTwo;
    }

    public void setTargetTwo(String targetTwo) {
        this.targetTwo = targetTwo;
    }

    public String getCurrentOne() {
        return currentOne;
    }

    public void setCurrentOne(String currentOne) {
        this.currentOne = currentOne;
    }

    public String getCurrentTwo() {
        return currentTwo;
    }

    public void setCurrentTwo(String currentTwo) {
        this.currentTwo = currentTwo;
    }

    public Uri getImageuri() {
        return imageuri;
    }

    public void setImageuri(Uri imageuri) {
        this.imageuri = imageuri;
    }

    public String getImpactScore() {
        return impactScore;
    }

    public void setImpactScore(String impactScore) {
        this.impactScore = impactScore;
    }

    public String getBaselineAchieve() {
        return baselineAchieve;
    }

    public void setBaselineAchieve(String baselineAchieve) {
        this.baselineAchieve = baselineAchieve;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
