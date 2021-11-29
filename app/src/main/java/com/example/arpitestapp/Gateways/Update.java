package com.example.arpitestapp.Gateways;

import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.Review;
import com.example.arpitestapp.Entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Update {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void test(){
        ArrayList<String> interests = new ArrayList<>();
        interests.add("Western");

        User tester = new User("testUserUpdates", "pw", "name", 9, "bio", interests);


        ArrayList<String> genres = new ArrayList<>();
        genres.add("French");

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
        recipeRating(testRecipe);
    }

    public static void recipeRating(Recipe recipe){
        DatabaseReference recipeRatingRef = database.getReference("recipes/"+recipe.getID()+"/rating");
        recipeRatingRef.setValue(recipe.getRating());
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
}
