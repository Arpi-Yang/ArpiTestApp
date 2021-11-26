package com.example.arpitestapp.Entities;

import java.util.ArrayList;

public class FullPreview extends Preview{
    private int ID;
    private String name;
    private int rating;
    private ArrayList<String> genre;
    private String description;
    private String image;
    private int preptime;
    private String ingredients;
    private String instructions;

    public FullPreview(Recipe recipe){
        super(recipe.getID(), recipe.getName(), recipe.getRating(), recipe.getGenre(), recipe.getDescription());
        this.image = recipe.getImage();
        this.preptime = recipe.getPreptime();
        this.ingredients = recipe.getIngredients();
        this.instructions = recipe.getInstructions();
    }

    /**
     * Getter Methods for FullPreview:
     * •getGenre - returns genre
     * •getName - returns name
     * •getRating - returns rating
     * •getID - returns ID
     * •getDescription - returns Description
     */
    public String getImage() {return image;}
    public int getPreptime() {return preptime;}
    public String getIngredients() {return ingredients;}
    public String getInstructions() {return instructions;}


    // TODO: docstring
    /**
     * Setter Methods for FullPreview:
     * The Preview Entity's attributes should not be set directly.
     * Instead, when their corresponding Recipe Entity's attributes
     *          are changed so are the Preview's. All of Preview's
     *          setter methods are inherited, and extras are defined directly.
     */
    public void setImage(String img) {this.image = img;}
    public void setPreptime(int rating) {this.rating = rating;}
    public void setIngredients(String ingredients) {this.ingredients = ingredients;}
    public void setInstructions(String instructions) {this.instructions = instructions;}

}
