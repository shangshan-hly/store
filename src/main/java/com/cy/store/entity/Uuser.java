package com.cy.store.entity;
import java.io.Serializable;
import java.util.Objects;
public class Uuser extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uuser)) return false;
        Uuser uuser = (Uuser) o;
        return Objects.equals(getUid(), uuser.getUid()) && Objects.equals(getUsername(), uuser.getUsername()) && Objects.equals(getPassword(), uuser.getPassword()) && Objects.equals(getSalt(), uuser.getSalt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getUsername(), getPassword(), getSalt());
    }
}