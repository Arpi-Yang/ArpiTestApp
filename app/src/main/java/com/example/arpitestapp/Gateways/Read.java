package com.example.arpitestapp.Gateways;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.arpitestapp.Entities.GenreLibrary;
import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.User;
import com.example.arpitestapp.Entities.UserSecurity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Read {
    private static final String TAG = "Read";

    // Declare database references
    private static final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference mRecipeRef = mRootRef.child("recipes");
    private static final DatabaseReference mUserRef = mRootRef.child("users");

    public  static UserSecurity populateUserSecurity() {
        // Initialize an empty UserSecurity object to populate
        final UserSecurity[] populatedUserSecurity = {new UserSecurity()};

        // Add a listener event object to the user database reference
        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Populate the UserSecurity object
                UserSecurity newUserSecurity = Read.fillUserSecurity(dataSnapshot);
                populatedUserSecurity[0] = newUserSecurity;
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

    private static User readUser(DataSnapshot singleUserRef) {
        // Read user attributes from singleUserRef
        String userUsername = singleUserRef.child("username").getValue(String.class);
        String userDisplayName = singleUserRef.child("displayName").getValue(String.class);
        String userPassword = singleUserRef.child("password").getValue(String.class);
        String userBiography = singleUserRef.child("biography").getValue(String.class);
        int userAge = singleUserRef.child("age").getValue(Integer.class);

        // TODO: fix this one and fix the database for it
        ArrayList<String> userInterests = new ArrayList<>();

        // Construct and return a new user
        User newUser = new User(userUsername, userPassword, userDisplayName, userAge, userBiography, userInterests);
        return newUser;
    }


    public static GenreLibrary populateGenreLibrary() {
        // Initialize an empty GenreLibrary object to populate
        final GenreLibrary[] populatedGenreLibrary = {new GenreLibrary()};

        // Add a listener event object to the recipe database reference
        mRecipeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Populate the GenreLibrary object
                GenreLibrary newGenreLibrary = Read.fillGenreLibrary(dataSnapshot);
                populatedGenreLibrary[0] = newGenreLibrary;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("ArpiTestApp", "Error.");
            }
        });

        return populatedGenreLibrary[0];
    }

    /*
    note: dataSnapshot must be mRecipeRef
     */
    private static GenreLibrary fillGenreLibrary(DataSnapshot dataSnapshot) {
        // Create empty GenreLibrary object
        GenreLibrary recipeGenreLibrary = new GenreLibrary();

        // Loop through all the recipes in the database
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            // TODO: figure out how to input a genre string and what that means
            // Add a recipe from the database to the GenreLibrary object
            Recipe recipe = readRecipe(ds);
            recipeGenreLibrary.addRecipes("filler string", recipe);
        }

        return recipeGenreLibrary;
    }

    public static Recipe readRecipe(DataSnapshot singleRecipeRef) {
        // Read recipe attributes from singleRecipeRef
        int recipeID = singleRecipeRef.child("ID").getValue(Integer.class);
        String recipeDescription = singleRecipeRef.child("description").getValue(String.class);
        // TODO: add genre values to our recipes in the database and add them
        ArrayList<String> recipeGenres = new ArrayList<>();
        String recipeImage = singleRecipeRef.child("image").getValue(String.class);
        String recipeIngredients = singleRecipeRef.child("ingredients").getValue(String.class);
        String recipeInstructions = singleRecipeRef.child("instructions").getValue(String.class);
        String recipeName = singleRecipeRef.child("name").getValue(String.class);
        int recipePreptime = singleRecipeRef.child("preptime").getValue(Integer.class);
        int recipeRating = singleRecipeRef.child("rating").getValue(Integer.class);

        // Construct and return a new recipe
        Recipe recipe = new Recipe(recipeInstructions, recipeIngredients,
                recipeGenres, recipeName, recipeRating, recipeID, recipeImage,
                recipeDescription, recipePreptime);

        return recipe;
    }
}
