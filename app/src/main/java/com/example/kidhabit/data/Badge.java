package com.example.kidhabit.data;

public class Badge {

    private int id;
    private String name;
    private int starRequirement;
    private boolean isUnlocked;

    public Badge(int id, String name, int starRequirement, boolean isUnlocked) {
        this.id = id;
        this.name = name;
        this.starRequirement = starRequirement;
        this.isUnlocked = isUnlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarRequirement() {
        return starRequirement;
    }

    public void setStarRequirement(int starRequirement) {
        this.starRequirement = starRequirement;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }
}
