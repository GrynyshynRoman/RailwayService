package ua.nure.hrynyshyn.core.entities.service;

/**
 * Created by GrynyshynRoman on 21.08.2016.
 */
public class Role {
    private int role_ID;
    private String login;
    private String role;

    public int getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
