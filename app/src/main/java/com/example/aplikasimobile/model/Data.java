package com.example.aplikasimobile.model;

public class Data {
    private String id;
    private String number;
    private String name;
    private String birth;
    private String gender;
    private String address;

    public Data(){
    }

    public Data(String id, String number, String name, String birth, String gender, String address){
        this.id = id;
        this.number = number;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.address = address;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number = number;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getBirth(){
        return birth;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
