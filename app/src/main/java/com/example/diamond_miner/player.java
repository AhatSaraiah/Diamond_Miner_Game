package com.example.diamond_miner;

public class player implements Comparable<player> {
    private String name;
    private int score;
    private String date;
    private double latitude;
    private double longitude;

    public player() {
    }

    public player(String name, int score, String date, double latitude, double longitude) {
        this.name = name;
        this.score = score;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "player[" +
                "name=' " + name + '\'' +
                ", score=" + score +
                ", date=' " + date + '\'' +
                ']';
    }

    @Override
    public int compareTo(player p) {
        return p.getScore() - this.getScore();
    }
}

