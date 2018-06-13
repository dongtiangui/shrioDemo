package com.EntiryPackage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
@JsonSerialize
public class userEntiry implements Serializable {

    private String name;
    private String pass;
    public userEntiry(){}
    public userEntiry(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @JsonSerialize
    @Override
    public String toString() {
        return "userEntiry{" + "name='" + name + '\'' + ", pass='" + pass + '\'' + '}';
    }
}
