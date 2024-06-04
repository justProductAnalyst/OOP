package ru.nsu.vetrov;

public class Student {
    private String name;
    private String nickname;
    private int group;

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getGroup() {
        return group;
    }
}
