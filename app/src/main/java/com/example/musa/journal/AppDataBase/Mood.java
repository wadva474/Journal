package com.example.musa.journal.AppDataBase;

public class Mood {
    private  String mood;
    private String description;
    private  String imageuri;
    private String videouri;
    public String id;


    public Mood(){

    }


    public Mood(String id ,String mood, String description, String imageuri, String videouri) {
        this.id=id;
        this.mood = mood;
        this.description = description;
        this.imageuri=imageuri;
        this.videouri=videouri;
    }

    public String getMood() {
        return mood;
    }



    public String getDescription() {
        return description;
    }



    public String getImageUri() {
        return imageuri;
    }



    public String getVideoUri() {
        return videouri;
    }



    public String getId() {
        return id;
    }


}
