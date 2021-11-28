package com.example.arpitestapp.Gateways;

import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.Review;
import com.example.arpitestapp.Entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Update {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void test(){
        ArrayList<String> interests = new ArrayList<>();
        interests.add("Western");

        User tester = new User("hi", "pw", "name", 9, "bio", interests);
        DatabaseReference myRef = database.getReference("users/"+tester.getUsername());

        myRef.setValue(tester);
        Review testReview = new Review("hi", 20, "pls work", 40);

        tester.addSavedReviews(20, testReview);
        for (Review review: tester.getUserReviews().values()){
            DatabaseReference myRef2 = database.getReference("users/"+tester.getUsername()+"/UserReviews/"+review.getRecipeID());
            myRef2.setValue(review);
        }


    }

    public static void updateRecipe(){
        ArrayList<String> interests = new ArrayList<>();
        interests.add("Western");
        User tester = new User("hihihi", "pw", "name", 9, "bio", interests);

        DatabaseReference myRef = database.getReference("users/"+tester.getUsername());

        myRef.setValue(tester);
    }

    public static void reviewCreated(User user, Recipe recipe){
        Map<String, Object> reviewUpdates = new HashMap<>();
        reviewUpdates.put("/users/" + user.getUsername() + "/reviews", user.getUserReviews());

    }
}
