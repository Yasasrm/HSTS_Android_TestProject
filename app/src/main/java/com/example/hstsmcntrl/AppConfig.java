package com.example.hstsmcntrl;

public enum AppConfig {
    GET_HOUSE_STATUS("hsts","To get House parameters"),
    OPEN_DOOR_ONE("sstsdraOpen","Door One open"),
    CLOSE_DOOR_ONE("sstsdraClose","Door One closed"),
    OPEN_DOOR_TWO("sstsdrbOpen","Door Two open"),
    CLOSE_DOOR_TWO("sstsdrbClose","Door Two closed"),
    LIGHT_ONE_ON("sstsltaOn","Light One on"),
    LIGHT_ONE_OFF("sstsltaOff","Light One off"),
    LIGHT_TWO_ON("sstsltbOn","Light Two on"),
    LIGHT_TWO_OFF("sstsltbOff","Light Two off"),
    ON("N","On"),
    OFF("O","On"),
    OPEN("P","Open"),
    CLOSE("C","Close"),
    WAIT("Please wait...","");
    private String code;
    private String name;

    AppConfig(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
