package com.example.animalalmanacversion1;

public class Animal {
    private String name;
    private String scientificName;
    private String dietType;
    private String description;
    private int imageResourceId;

    public Animal(String name, String scientificName, String dietType, String description, int imageResource) {
        this.name = name;
        this.scientificName = scientificName;
        this.dietType = dietType;
        this.description = description;
        this.imageResourceId = imageResource;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getDietType() {
        return dietType;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResourceId;
    }
}
