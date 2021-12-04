package com.example.arpitestapp.Gateways;
import android.util.Log;

import androidx.annotation.NonNull;


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

    public static void populateUserSecurity(final userDataStatus dataStatus) {
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
    }

    private static UserSecurity fillUserSecurity(DataSnapshot dataSnapshot) {
        // Create empty UserSecurity object
        UserSecurity usersUserSecurity = new UserSecurity();

        // Loop through all the users in the database
        for(DataSnapshot singleUserRef : dataSnapshot.getChildren()){

            // Add a user from the database to the UserSecurity object
            User user = ReadUser.readUser(singleUserRef);
            usersUserSecurity.addUser(user);
        }

        return usersUserSecurity;
    }


    public static void populateGenreLibrary(final recipeDataStatus dataStatus) {
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
    }

    /**
     * note: dataSnapshot must be mRecipeRef
     */
    private static GenreLibrary fillGenreLibrary(DataSnapshot dataSnapshot) {
        // Create empty GenreLibrary object
        GenreLibrary genreLibrary = new GenreLibrary();
        // Loop through all the recipes in the database
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Recipe recipe = ReadRecipe.readRecipe(ds);
//            System.out.println(recipe.getName());

            // Add each recipe to recipeGenreLibrary
            for(String genre: recipe.getGenre()){
                genreLibrary.addRecipes(genre, recipe);
            }

        }
        return genreLibrary;
    }
}