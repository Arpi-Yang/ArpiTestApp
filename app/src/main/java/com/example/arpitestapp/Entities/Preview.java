package com.example.arpitestapp.Entities;


import java.util.ArrayList;

public class Preview {
    /**
     * This class is the Preview Entity for a corresponding Recipe Entity (meaning they share the same ID).
     * It possesses 5 attributes:
     * •ID - the unique identifier for the recipe (int)
     * •name - the name of the recipe (String)
     * •rating - the rating given to the Recipe as designated by users (int from 1-5)
     * •genre - the types of recipe in question, for example "Jamaican"
     * •description - brief description of the recipe(String)
     */
    private int ID;
    private String name;
    private int rating;
    private ArrayList<String> genre;
    private String description;

    /**
     * Constructors for a Recipe's Preview
     */
    public Preview(){};
    public Preview(Recipe recipe){
        this.ID = recipe.getID();
        this.name = recipe.getName();
        this.rating = recipe.getRating();
        this.genre = recipe.getGenre();
        this.description = recipe.getDescription();}
    public Preview(int id, String n, int rate, ArrayList<String> genres, String description){
        this.ID = id;
        this.name = n;
        this.rating = rate;
        this.genre = genres;
        this.description = description;}

    /**
     * Getter Methods for Preview:
     * •getGenre - returns genre
     * •getName - returns name
     * •getRating - returns rating
     * •getID - returns ID
     * •getDescription - returns Description
     */
    public ArrayList<String> getGenre() {return genre;}
    public String getName() {return name;}
    public int getRating() {return rating;}
    public int getID() {return ID;}
    public String getDescription() {return description;}



    /**
     * Setter Methods for Preview:
     * The Preview Entity's attributes should not be set directly.
     * Instead, when their corresponding Recipe Entity's attributes
     *          are changed so are the Preview's
     */
    public void setGenre(ArrayList<String> genre) {this.genre = genre;}
    public void setRating(int rating) {this.rating = rating;}
    public void setID(int id) {this.ID = id;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}

    /**
     * This method returns a preview entity in the form of an arraylist
     * The array list returned has all the preview attributes in the following order:
     * @return [id, name, rating, genre, description]
     */
    public ArrayList<Object> toList(){
        ArrayList<Object> lst = new ArrayList<Object>();
        lst.add(ID);
        lst.add(name);
        lst.add(rating);
        lst.add(genre);
        lst.add(description);
        return lst;
    }

}
