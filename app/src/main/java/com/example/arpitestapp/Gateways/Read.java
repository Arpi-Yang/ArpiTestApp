package com.example.arpitestapp.Gateways;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.arpitestapp.Entities.GenreLibrary;
import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.Review;
import com.example.arpitestapp.Entities.User;
import com.example.arpitestapp.Entities.UserSecurity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Read {
    private static final String TAG = "Read";

    // Declare database references
    private static final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference mRecipeRef = mRootRef.child("recipes");
    private static final DatabaseReference mUserRef = mRootRef.child("users");

    public interface userDataStatus {
        void userSecurityLoaded(UserSecurity userSecurity);
    }
    public interface recipeDataStatus {
        void genreLibraryLoaded(GenreLibrary genreLibrary);
    }

    public  static UserSecurity populateUserSecurity(final userDataStatus dataStatus) {
        // Initialize an empty UserSecurity object to populate
        final UserSecurity[] populatedUserSecurity = {new UserSecurity()};

        // Add a listener event object to the user database reference
        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Populate the UserSecurity object
                UserSecurity newUserSecurity = Read.fillUserSecurity(dataSnapshot);
                dataStatus.userSecurityLoaded(newUserSecurity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("ArpiTestApp", "Error.");
            }
        });

        return populatedUserSecurity[0];
    }

    private static UserSecurity fillUserSecurity(DataSnapshot dataSnapshot) {
        // Create empty UserSecurity object
        UserSecurity usersUserSecurity = new UserSecurity();

        // Loop through all the users in the database
        for(DataSnapshot singleUserRef : dataSnapshot.getChildren()){

            // Add a user from the database to the UserSecurity object
            User user = readUser(singleUserRef);
            usersUserSecurity.addUser(user);
        }

        return usersUserSecurity;
    }

    public static User readUser(DataSnapshot singleUserRef) {
        // Read user attributes from singleUserRef
        String userUsername = singleUserRef.child("username").getValue(String.class);
        String userDisplayName = singleUserRef.child("displayName").getValue(String.class);
        String userPassword = singleUserRef.child("password").getValue(String.class);
        String userBiography = singleUserRef.child("biography").getValue(String.class);
        int userAge = singleUserRef.child("age").getValue(Integer.class);

        // TODO: fix this one and fix the database for it
        ArrayList<String> userInterests = new ArrayList<>();
        DataSnapshot userInterestSnapshot = singleUserRef.child("interests");
        for (DataSnapshot interest : userInterestSnapshot.getChildren()) {
            userInterests.add(interest.getValue(String.class));
        }

        // TODO: load in user saved recipes
        ArrayList<Recipe> userSavedRecipes = new ArrayList<>();
        DataSnapshot userSavedRecipeSnapshot = singleUserRef.child("SavedRecipes");
        for (DataSnapshot savedRecipeSnap : userSavedRecipeSnapshot.getChildren()) {
            Recipe savedRecipe = readRecipe(savedRecipeSnap);
            userSavedRecipes.add(savedRecipe);
        }

        // TODO: load in user genre weights
        Map<String, Double> userGenreWeights = new HashMap<>();
        DataSnapshot userGenreWeightsSnapshot = singleUserRef.child("genreWeights");
        for (DataSnapshot genreWeightSnap : userGenreWeightsSnapshot.getChildren()) {
            String genreWeightName = genreWeightSnap.getKey();
            Double genreWeightValue = genreWeightSnap.getValue(Double.class);
            userGenreWeights.put(genreWeightName, genreWeightValue);
        }

        // TODO: load in user saved reviews
        Map<Integer, Review> userReviews = new HashMap<>();
        DataSnapshot userReviewsSnapshot = singleUserRef.child("UserReviews");
        for (DataSnapshot userReviewSnap : userReviewsSnapshot.getChildren()) {
            Review userReview = readReview(userReviewSnap);
            int reviewID = userReview.getReviewID();
            userReviews.put(reviewID, userReview);
        }

        // Construct and return a new user
        User newUser = new User(userUsername, userPassword, userDisplayName, userAge, userBiography, userInterests);
        // TODO: modify extra factors
        return newUser;
    }

    public static Review readReview(DataSnapshot reviewSnapshot) {
        int reviewID = reviewSnapshot.child("reviewID").getValue(int.class);
        int recipeID = reviewSnapshot.child("recipeID").getValue(int.class);
        String username = reviewSnapshot.child("username").getValue(String.class);
        String comments = reviewSnapshot.child("comments").getValue(String.class);
        int rating = reviewSnapshot.child("rating").getValue(int.class);

        return new Review(reviewID, recipeID, username, comments, rating);
    }

    public static GenreLibrary populateGenreLibrary(final recipeDataStatus dataStatus) {
        // Initialize an empty GenreLibrary object to populate
        final GenreLibrary[] populatedGenreLibrary = {new GenreLibrary()};

        // Add a listener event object to the recipe database reference
        mRecipeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Populate the GenreLibrary object
                GenreLibrary newGenreLibrary = Read.fillGenreLibrary(dataSnapshot);
                dataStatus.genreLibraryLoaded(newGenreLibrary);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("ArpiTestApp", "Error.");
            }
        });

        return populatedGenreLibrary[0];
    }

    /**
     * note: dataSnapshot must be mRecipeRef
     */
    private static GenreLibrary fillGenreLibrary(DataSnapshot dataSnapshot) {
        // Create empty GenreLibrary object
        GenreLibrary genreLibrary = new GenreLibrary();
        // Loop through all the recipes in the database
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Recipe recipe = readRecipe(ds);
//            System.out.println(recipe.getName());

            // Add each recipe to recipeGenreLibrary
            for(String genre: recipe.getGenre()){
                genreLibrary.addRecipes(genre, recipe);
            }

        }
        return genreLibrary;
    }
    /**
     * @param singleRecipeRef
     * @return
     */
    public static Recipe readRecipe(DataSnapshot singleRecipeRef) {
        // Read recipe attributes from singleRecipeRef
        int recipeID = singleRecipeRef.child("id").getValue(Integer.class);
        String recipeDescription = singleRecipeRef.child("description").getValue(String.class);


        ArrayList<String> recipeGenres = new ArrayList<>();
        DataSnapshot recipeGenreListDatabase = singleRecipeRef.child("genre");
        for (DataSnapshot genre : recipeGenreListDatabase.getChildren()) {
            recipeGenres.add(genre.getValue(String.class));
        }

        String recipeImage = singleRecipeRef.child("image").getValue(String.class);
        String recipeIngredients = singleRecipeRef.child("ingredients").getValue(String.class);
        String recipeInstructions = singleRecipeRef.child("instructions").getValue(String.class);
        String recipeName = singleRecipeRef.child("name").getValue(String.class);

        int recipePreptime = recipePrepReader(singleRecipeRef);
        int recipeRating = recipeRatingReader(singleRecipeRef);

        // Construct and return a new recipe
        Recipe recipe = new Recipe(recipeInstructions, recipeIngredients,
                recipeGenres, recipeName, recipeRating, recipeID, recipeImage,
                recipeDescription, recipePreptime);

        return recipe;
    }

    private static int recipePrepReader(DataSnapshot singleRecipeRef) {

        // Check if the recipe from the database has a valid prep time attribute.
        // If it does, return the prep time attribute value.
        if (singleRecipeRef.child("prep time").exists()) {
            Object recipePrepHolder = singleRecipeRef.child("preptime").getValue();

            if (recipePrepHolder instanceof Integer) {
                return (Integer) recipePrepHolder;

            }
        }

        // If no eligible value for prep time exists, return default value of 60 minutes.
        return 60;


    }

    private static int recipeRatingReader(DataSnapshot singleRecipeRef) {

        // Check if the recipe from the database has a valid rating attribute.
        // If it does, return the rating attribute value.
        if (singleRecipeRef.child("rating").exists()) {
            Object recipeRatingHolder = singleRecipeRef.child("rating").getValue();

            if (recipeRatingHolder instanceof Integer) {
                return (Integer) recipeRatingHolder;

            }
        }

        // If no eligible value for rating exists, return default value of 60 minutes.
        return 60;


    }
}