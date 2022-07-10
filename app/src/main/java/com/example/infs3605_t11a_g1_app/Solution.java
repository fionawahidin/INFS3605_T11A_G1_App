package com.example.infs3605_t11a_g1_app;

import java.util.ArrayList;

public class Solution {
    private String title;
    private String challenge;
    private String rating;
    private int image;
    private String code;
    private String desc;

    // Constructor
    public Solution(String title, String challenge, String rating, int image, String code, String desc) {
        this.title = title;
        this.challenge = challenge;
        this.rating = rating;
        this.image = image;
        this.code = code;
        this.desc = desc;
    }

    // Getters and setters
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getChallenge() { return challenge; }

    public void setChallenge(String challenge) { this.challenge = challenge; }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }

    public int getImage() { return image; }

    public void setImage(int image) { this.image = image; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public static ArrayList<Solution> getSolutions() {
        ArrayList<Solution> solutions = new ArrayList<>();

        solutions.add(new Solution("Wombat-Powered Recovery", "Bushfire Regeneration Challenge",
                "9.5", R.drawable.wombat, "s1",
                "Wombat-powered recovery: harnessing an ecosystem engineer to increase bushfire resilience"));
        solutions.add(new Solution("Solar VHF Koala Ear Tag", "Bushfire Regeneration Challenge",
                "9.0", R.drawable.koala, "s2",
                "Finding furry friends forever - solar VHF koala ear tag"));
        solutions.add(new Solution("Econic Technologies - CO2 Conversion", "Cities of Tomorrow",
                "8.7", R.drawable.econic, "s3",
                "Turning CO2 into endless value Econic creates value - environmental, economic and performance - from captured waste CO2 for the polymer industry"));
        return solutions;
    }

    public static Solution getSolution(String code) {
        for (Solution solution : getSolutions()) {
            if(solution.getCode().equals(code)) {
                return solution;
            }
        }
        return null;
    }
}
