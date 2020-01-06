package com.example.diamond_miner;

public class player implements Comparable<player> {
    private String name;
    private int score;
    private String date;


    public player(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;

    }

    public player() {
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
        return "player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int compareTo(player p) {
        return p.getScore() - this.getScore();
    }
}

