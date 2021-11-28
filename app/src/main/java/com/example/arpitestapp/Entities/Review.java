package com.example.arpitestapp.Entities;

import com.example.arpitestapp.Gateways.Constants;

public class Review {
    // NOTE: NOT USED IN SKELETON MODEL
    private int reviewID;
    private int recipeID;
    private String username;
    private String comments;
    private int rating;

    public Review(int reviewID,int recipeID, String username, String comments, int rating){
        this.rating = rating;
        this.recipeID = recipeID;
        this.username = username;
        this.comments =  comments;
        this.reviewID = reviewID;
    }

    public Review(String username, int recipeID, String comment, int rating) {
        this.username = username;
        this.recipeID = recipeID;
        this.comments = comment;
        this.rating = rating;
    }

    public int getReviewID() {return reviewID;}
    public int getRecipeID() {return recipeID;}
    public String getUsername() {return username;}
    public String getComments() {return comments;}
    public int getRating() {return rating;}


    public void setReviewID(int reviewID) {this.reviewID = reviewID;}
    public void setRecipeID(int recipeID) {this.recipeID = recipeID;}
    public void setUsername(String username) {this.username = username;}
    public void setComments(String comments) {this.comments = comments;}
    public void setRating(int rating) {this.rating = rating;}

    public boolean saveToUser(String username, int recipeID, Review review) {
        User accUser = Constants.USERSECURITY.getUserByID(username);
        accUser.addSavedReviews(recipeID, review);
        return true;
    }

    public boolean saveToRecipe(int recipeID, String username, Review review) {
        Recipe recipe = Constants.GENRELIBRARY.getRecipeByID("All", recipeID);
        recipe.addSavedReviews(username, review);
        return true;
    }
}

