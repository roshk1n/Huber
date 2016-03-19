package com.example.roshk1n.test_hubert_dreyfus;

/**
 * Created by roshk1n on 13.03.16.
 */
public class User {
    private String username,password;
    private int valnovice,valadvanced_beginer,valcompetent,valproficient,valexpert;
    public User(String username, String password)
    {
        this.valnovice=0;
        this.valadvanced_beginer=0;
        this.valcompetent=0;
        this.valproficient=0;
        this.valexpert=0;
        this.password=password;
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public int getValadvanced_beginer() {
        return valadvanced_beginer;
    }

    public int getValcompetent() {
        return valcompetent;
    }

    public int getValexpert() {
        return valexpert;
    }

    public int getValnovice() {
        return valnovice;
    }

    public int getValproficient() {
        return valproficient;
    }

    public String getPassword() {
        return password;
    }

    public void setValadvanced_beginer(int valadvanced_beginer) {
        this.valadvanced_beginer = valadvanced_beginer;
    }

    public void setValcompetent(int valcompetent) {
        this.valcompetent = valcompetent;
    }

    public void setValexpert(int valexpert) {
        this.valexpert = valexpert;
    }

    public void setValnovice(int valnovice) {
        this.valnovice = valnovice;
    }

    public void setValproficient(int valproficient) {
        this.valproficient = valproficient;
    }
}
