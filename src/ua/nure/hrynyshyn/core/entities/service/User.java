package ua.nure.hrynyshyn.core.entities.service;

/**
 * Created by GrynyshynRoman on 03.08.2016.
 */
public class User {
    private int user_ID;
    private String name;
    private String passportCode;

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }
}
