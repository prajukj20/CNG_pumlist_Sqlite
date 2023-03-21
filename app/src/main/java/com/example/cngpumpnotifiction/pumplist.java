package com.example.cngpumpnotifiction;

public class pumplist {
    String id;
    String Name;
    String Address;
    String location;
    String time;
    String user_id;

    public pumplist(String id, String name, String address, String location, String time, String user_id) {
        this.id = id;
        Name = name;
        Address = address;
        this.location = location;
        this.time = time;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
