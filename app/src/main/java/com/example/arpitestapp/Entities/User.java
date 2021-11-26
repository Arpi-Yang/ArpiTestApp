package com.example.arpitestapp.Entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the User Entity. It possesses 7 attributes:
 *  •displayName- the name the user chooses to make public(String)
 *  •age - the age of the user (int)
 *  •password - a collection of characters used to gain access to the user's account (String)
 *  •username - the user's unique identifier; used in conjunction with password to login. Sometimes called ID(String)
 *  •biography- a description of the user(String)
 *  •interests- a list of genres(Strings) the user is interested in (ArrayList<String>)
 *  •SavedRecipes - a list of the recipes the user has saved (ArrayList<Recipe>)
 *  •UserReviews - a list of the reviews the user has made (ArrayList<Review>)
 */
public class User {
    private String displayName;
    private int age;
    private String password;
    private String username;
    private String biography;
    private ArrayList<String> interests;
    private ArrayList<Recipe> SavedRecipes = new ArrayList<>();

    private HashMap<Integer, Review> UserReviews = new HashMap<>();
    private HashMap<String, Double> GenreWeights = new HashMap<>();

    public User(){
        this.interests = new ArrayList<>();
    }

    /**
     * Constructor for User
     */
    public User(String username, String pws, String name, int age, String bio, ArrayList<String> interests){
        this.displayName = name;
        this.age = age;
        this.password = pws;
        this.username = username;
        this.biography = bio;
        this.interests = interests;
        updateGenreWeights(this.interests);
    }

    /* Updates GenreWeights to match interests */
    private void updateGenreWeights(ArrayList<String> interests) {
        for (String interest: interests){
            // Does not override previous interests data
            if (!this.GenreWeights.containsKey(interest)){
                this.GenreWeights.put(interest, 0.70);
            }
        }
    }

    /* Updates GenreWeights when a recipe is saved */
    private void updateGenreWeights(Integer recipeID) {
        Recipe recipe = Constants.GENRELIBRARY.getRecipeByID("All", recipeID);
        ArrayList<String> recipeGenre = recipe.getGenre();
        for (String genre: recipeGenre){
            if (!genre.equals("All")) {
                this.GenreWeights.put(genre, 0.05);
            } // make sure that GenreWeights.get(genre) is not greater than 1.0
        }
    }

    /* Updates GenreWeights when an interest is removed */
    private void updateGenreWeights(String genre) {
        this.GenreWeights.put(genre, 0.0);
    }


    /**
     * Getter Methods for User:
     * •getDisplayName - returns displayname
     * •getAge -  returns age
     * •getPassword - returns password
     * •getUsername - returns username
     * •getBiography - returns biography
     * •getInterests - returns interests
     * •getSavedRecipes - returns SavedRecipes
     * •getUserReviews - returns UserReviews
     */
    public String getDisplayName(){return displayName;}
    public int getAge() {return age;}
    public String getPassword() {return password;}
    public String getUsername() {return username;}
    public String getBiography() {return biography;}
    public ArrayList<String> getInterests() {return interests;}
    public ArrayList<Recipe> getSavedRecipes() {return SavedRecipes;}
    public HashMap<Integer, Recipe> getSavedRecipesHash() {
        HashMap<Integer, Recipe> h = new HashMap<>();
        for (Recipe recipe: SavedRecipes) {
            h.put(recipe.getID(), recipe);
        }
        return h;
    }
    public HashMap<Integer, Review> getUserReviews() {return UserReviews;}

    public HashMap<String, Double> getGenreWeights() { return GenreWeights; }

    /**
     * Setter Methods for User:
     * •setDisplayName - accepts displayname attribute for a User
     * •setAge -  accepts age attribute for a User
     * •setPassword - accepts password attribute for a User
     * •setUsername - accepts username attribute for a User
     * •setBiography - accepts biography attribute for a User
     * •addInterests - adds an interest to the given User's interests
     * •addSavedRecipes - adds a Recipe to the given User's  SavedRecipes
     */
    public void setDisplayName(String displayName) {this.displayName = displayName;}
    public void setAge(int age) {this.age = age;}
    public void setPassword(String password) {this.password = password;}
    public void setUsername(String username) {this.username = username;}
    public void setBiography(String biography) {this.biography = biography;}

    public void addInterests(String interest) {
        this.interests.add(interest);
        this.updateGenreWeights(this.interests);
    }
    public void deleteInterests(String interest) {
        this.interests.remove(interest);
        this.updateGenreWeights(interest);
    }


    public void addSavedRecipes(Recipe recipe) {
        SavedRecipes.add(recipe);
        updateGenreWeights(recipe.getID());
    }

    public void addSavedReviews(int reviewID, Review review) {
        UserReviews.put(reviewID, review);
    }

    /**
     * Generates a profile based on a given User's displayname, username, interests, biography and age
     * @return ArrayList<Object> representing the profile that has been generated
     */
    public UserInfo getProfile(){
        UserInfo profile = new UserInfo(username, password, displayName, age, biography, interests);
        return profile;
    }


    public void removeSavedRecipes(Recipe recipe) {
        SavedRecipes.remove(recipe);
    }
}

