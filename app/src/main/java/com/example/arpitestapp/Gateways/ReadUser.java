package com.example.arpitestapp.Gateways;

import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.Review;
import com.example.arpitestapp.Entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReadUser {

    public static User readUser(DataSnapshot singleUserRef) {
        // Read user attributes from singleUserRef
        String userUsername = singleUserRef.child("username").getValue(String.class);
        String userDisplayName = singleUserRef.child("displayName").getValue(String.class);
        String userPassword = singleUserRef.child("password").getValue(String.class);
        String userBiography = singleUserRef.child("biography").getValue(String.class);
        int userAge = singleUserRef.child("age").getValue(Integer.class);
        ArrayList<String> userInterests = new ArrayList<>();
        DataSnapshot userInterestSnapshot = singleUserRef.child("interests");
        for (DataSnapshot interest : userInterestSnapshot.getChildren()) {
            userInterests.add(interest.getValue(String.class));
        }

        // Construct a new user object
        User newUser = new User(userUsername, userPassword, userDisplayName,
                userAge, userBiography, userInterests);

        // Load in user saved recipes
        DataSnapshot userSavedRecipeSnapshot = singleUserRef.child("SavedRecipes");
        for (DataSnapshot savedRecipeSnap : userSavedRecipeSnapshot.getChildren()) {
            Recipe savedRecipe = ReadRecipe.readRecipe(savedRecipeSnap);
            newUser.addSavedRecipes(savedRecipe);
        }

        // Load in user genre weights
        DataSnapshot userGenreWeightsSnapshot = singleUserRef.child("genreWeights");
        for (DataSnapshot genreWeightSnap : userGenreWeightsSnapshot.getChildren()) {
            String genreWeightName = genreWeightSnap.getKey();
            Double genreWeightValue = genreWeightSnap.getValue(Double.class);
            newUser.updateGenreWeight(genreWeightName, genreWeightValue);
        }

        // Load in user saved reviews
        DataSnapshot userReviewsSnapshot = singleUserRef.child("UserReviews");
        for (DataSnapshot userReviewSnap : userReviewsSnapshot.getChildren()) {
            Review userReview = ReadReview.readReview(userReviewSnap);
            int reviewID = userReview.getReviewID();
            newUser.addSavedReviews(userReview.getRecipeID(), userReview);
        }

        return newUser;
    }

}
