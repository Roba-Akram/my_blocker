package com.iug.blocker.model;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Profile extends RealmObject{

    @PrimaryKey
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //private HashMap<Time, Time> intervals;
   // private List<String> apps;
    private boolean status;
    boolean sat;
    boolean sun;
    boolean mon;
    boolean tue;
    boolean wed;
    boolean tur;
    boolean fri;

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isTur() {
        return tur;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public void setTur(boolean tur) {
        this.tur = tur;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



/*    public HashMap<Time, Time> getIntervals() {
        return intervals;
    }

    public void setIntervals(HashMap<Time, Time>intervals) {
        this.intervals = intervals;
    }

    public List<String> getApps() {
        return apps;
    }

    public void setApps(List<String> apps) {
        this.apps = apps;
    }*/



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
