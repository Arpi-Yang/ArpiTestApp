package com.example.arpitestapp.Gateways;

import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.Review;
import com.example.arpitestapp.Entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class Update {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void test(){
        ArrayList<String> interests = new ArrayList<>();
        interests.add("Western");

        User tester = new User("testUserUpdates", "pw", "name", 9, "bio", interests);


        ArrayList<String> genres = new ArrayList<>();
        genres.add("German");

        Recipe testRecipe = new Recipe("instructions", "ingredients", genres, "name", 3, 210, "img_210", "description", 10);
        Recipe testRecipe2 = new Recipe("instructions", "ingredients", genres, "name2", 3, 211, "img_210", "description", 10);
        tester.addSavedRecipes(testRecipe);
        tester.addSavedRecipes(testRecipe2);

        Review testReview = new Review(1, 210, tester.getUsername(), "test review", 5);
        tester.addSavedReviews(20, testReview);
        testRecipe.addSavedReviews(tester.getUsername(), testReview);

        DatabaseReference myRef = database.getReference("recipes/"+testRecipe.getID());

        myRef.setValue(testRecipe);

        reviewCreated(testReview);
        recipesSaved(tester);
        userGenreWeights(tester.getUsername(), tester.getGenreWeights());
        recipeRating(testRecipe);

        tester.setAge(22);
        userProfile(tester.getUsername(), 22, "age");

        tester.addInterests("Indonesian");
        userProfile(tester.getUsername(), tester.getInterests(), "interests");
    }

    // updates recipe rating (to be used after updating recipe reviews)
    public static void recipeRating(Recipe recipe){
        DatabaseReference recipeRatingRef = database.getReference("recipes/"+recipe.getID()+"/rating");
        DatabaseReference recipeRatingListRef = database.getReference("recipes/"+recipe.getID()+"/ratingList");
        recipeRatingRef.setValue(recipe.getRating());
        recipeRatingListRef.setValue(recipe.getRatingList());
    }

    // UPDATES (OVERRIDES WHOLE, SO CAN BE USED FOR DELETION)
    public static void recipesSaved(User user){
        DatabaseReference userSavedRef = database.getReference("users/"+user.getUsername()+"/SavedRecipes");
        userSavedRef.removeValue();
        for (Recipe recipe: user.getSavedRecipes()){
            DatabaseReference recipeRef = database.getReference("users/"+user.getUsername()+"/SavedRecipes/"+recipe.getID());
            recipeRef.setValue(recipe);
        }
    }

    // Updates User and Recipe's List of Reviews
    public static void reviewCreated(Review review){
        DatabaseReference userReviewsRef = database.getReference("users/"+review.getUsername()+"/UserReviews/"+review.getRecipeID());
        DatabaseReference recipeReviewsRef = database.getReference("recipes/"+review.getRecipeID()+"/RecipeReviews/"+review.getUsername());

        userReviewsRef.setValue(review);
        recipeReviewsRef.setValue(review);
    }

    // updates specific property of user profile
    public static void userProfile(String username, Object property, String propertyName){
        DatabaseReference userRef = database.getReference("users/"+username+"/"+propertyName);
        userRef.setValue(property);
    }

    // updates user genre weights (to be used after saving recipe, adding/deleting interests)
    public static void userGenreWeights(String username, Map<String, Double> genreWeights){
        DatabaseReference userRef = database.getReference("users/"+username+"/genreWeights");
        userRef.setValue(genreWeights);
    }
}
