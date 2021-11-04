package com.example.hstsmcntrl;

public class STS {
    private String door1;
    private String door2;
    private String light1;
    private String light2;

    public STS() {
    }

    public String getDoor1() {
        return door1;
    }

    public void setDoor1(String door1) {
        this.door1 = door1;
    }

    public String getDoor2() {
        return door2;
    }

    public void setDoor2(String door2) {
        this.door2 = door2;
    }

    public String getLight1() {
        return light1;
    }

    public void setLight1(String light1) {
        this.light1 = light1;
    }

    public String getLight2() {
        return light2;
    }

    public void setLight2(String light2) {
        this.light2 = light2;
    }

    @Override
    public String toString() {
        return "STS{" +
                "door1='" + door1 + '\'' +
                ", door2='" + door2 + '\'' +
                ", light1='" + light1 + '\'' +
                ", light2='" + light2 + '\'' +
                '}';
    }
}
