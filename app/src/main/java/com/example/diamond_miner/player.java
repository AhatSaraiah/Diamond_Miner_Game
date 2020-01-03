package com.example.diamond_miner;

import android.location.Location;

public class player {
   private String name ;
    private Location location;
    private String score;
    public player() {
    }

    public player(String name, Location location, String score) {
        this.name = name;
        this.location = location;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(player p) {
        return Integer.parseInt(p.getScore())-Integer.parseInt(this.getScore());
    }

}


