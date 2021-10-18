package model;

public class Player {
    private String name;
    private int age;
    private String team;
    private int point;

    public Player(String name, int age, String team, int point) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.point = point;
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

}
