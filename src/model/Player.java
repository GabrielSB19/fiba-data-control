package model;

public class Player {
    private String name;
    private int age;
    private String team;
    private int point;
    private int bounces;
    private int assists;
    private int steals;
    private int blocks;

    public Player(String name, int age, String team, int point, int assists, int blocks, int bounces, int steals) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.point = point;
        this.bounces = bounces;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getBounces() {
        return bounces;
    }

    public void setBounces(int bounces) {
        this.bounces = bounces;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }
}
