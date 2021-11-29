package com.example.arpitestapp.Entities;

import com.example.arpitestapp.Entities.Preview;
import com.example.arpitestapp.Entities.Review;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the Recipe Entity. It possesses 11 attributes:
 *  •instructions - the instructions to make the recipe (String)
 *  •ingredients - the different ingredients required (String where each ingredient is
 *                 separated by a comma)
 *  •genre - the types of recipe in question, for example "Jamaican" and "Dessert" (ArrayList of Strings)
 *  •name - the name of the recipe (String)
 *  •rating - the rating given to the Recipe as designated by users (int from 1-5)
 *  •ID - the unique identifier for the recipe (int)
 *  •RecipeReviews - a list of reviews generated from users (ArrayList<Review>)
 *  •image - text representing a jpg file of the recipe (String)
 *  •description - brief description of the recipe(String)
 *  •preview - contains a subset of the recipe's attributes (name, id, genres, rating, description)
 *  •preptime  - the number of minutes it takes to prepare for the cooking/baking of recipe
 */

public class Recipe {
    private String instructions;
    private String ingredients;
    private ArrayList<String> genre;
    private String name;
    private int rating;
    private int ID;
    private HashMap<String, Review> RecipeReviews;
    private String image;
    private String description;
    private Preview preview;
    private int preptime;
    private ArrayList<Integer> ratingList;

    /**
     * Constructor for Recipe
     */
    public Recipe() {}
    public Recipe(String instructions, String ingredients, ArrayList<String>  g, String name, int rating, int id, String img,
                  String description, int preptime) {
        this.instructions = instructions;
        this.ingredients = ingredients;
        ArrayList<String> genres = new ArrayList<>();
        genres.add("All");
        genres.addAll(g);
        this.genre = genres;
        this.name = name;
        this.rating = rating;
        this.ID = id;
        this.image = img;
        this.description = description;
        this.preptime = preptime;
        this.RecipeReviews = new HashMap<>();
        this.preview = new Preview(this);
        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(rating);
        this.ratingList = ratings;
    }


    /**
     * Getter Methods for Recipe:
     * •getInstructions - returns instructions
     * •getIngredients -  returns ingredients
     * •getGenre - returns genre
     * •getName - returns name
     * •getRating - returns rating
     * •getID - returns ID
     * •getImage - returns image
     * •getDescription - returns Description
     * •getRecipeReviews - returns RecipeReviews
     * •getPreptime- returns preptime
     * •getPreview - returns Preview Entity
     * •getFull - returns all the attributes but the preview attribute
     */
    public String getInstructions() {return instructions;}
    public String getIngredients() {return ingredients;}
    public ArrayList<String> getGenre() {return genre;}
    public String getGenreStrings(){
        StringBuilder s = new StringBuilder();
        int counter = 1;
        for (String str: genre){
            if (counter < genre.size()){
                counter ++;
                s.append(str).append(", ");
            } else {
                s.append(str);
            }
        }
        return s.toString();
    }

    public String getName() {return name;}
    public int getRating() {return rating;}
    public int getID() {return ID;}
    public String getImage() {return image;}
    public String getDescription() {return description;}
    public HashMap<String, Review> getRecipeReviews() {return RecipeReviews;}
    public int getPreptime() {return preptime;}

    public Preview getPreview() {
        return new Preview(this);
    }
    public ArrayList<Integer> getRatingList() {
        return ratingList;
    }


    /**
     * Setter Methods for Recipe:
     * •setInstructions - accepts String for instructions attribute for a recipe
     * •setIngredients - accepts String for ingredients attribute for a recipe
     * •setGenre - accepts ArrayList of Strings for genre attribute for a recipe, alters recipe's preview
     * •setName - accepts String for name attribute for a recipe, alters recipe's preview
     * •setRating - accepts integer from 1-5 for rating attribute for a recipe, alters recipe's preview
     * •setID - accepts integer for ID attribute for a recipe, alters recipe's preview
     * •setImage - accepts image attribute for a recipe
     * •setDescription - accepts String for Description attribute for a recipe, alters recipe's preview
     * •setPreptime - accepts integer for preptime attribute
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
        this.preview.setGenre(genre);
    }
    public void setRating(int rating) {
        this.rating = rating;
        this.preview.setRating(rating);
    }
    public void setID(int ID) {
        this.ID = ID;
        this.preview.setID(ID);
    }
    public void setName(String name) {
        this.name = name;
        this.preview.setName(name);
    }
    public void setDescription(String description) {
        this.description = description;
        this.preview.setDescription(description);
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setPreptime(int preptime) {
        this.preptime = preptime;
    }

    public void addSavedReviews(String username, Review review) {
        this.RecipeReviews.put(username, review);
        this.ratingList.add(review.getRating());
        double sum = 0;
        for(Integer i : ratingList)
            sum += i;
        this.rating = (int) Math.round(sum/ratingList.size());
    }

}


