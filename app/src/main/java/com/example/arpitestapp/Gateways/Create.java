package com.example.arpitestapp.Gateways;

import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.Review;
import com.example.arpitestapp.Entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Create{

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static void createUser(User user){
        DatabaseReference reference = database.getReference("users/"+user.getUsername());
        reference.setValue(user);
    }
    public static void createRecipe(Recipe recipe){
        DatabaseReference reference = database.getReference("recipes/"+ recipe.getID());
        reference.setValue(recipe);
    }

    public static void testCreate(){
        ArrayList<String> interests = new ArrayList<>();
        interests.add("Western");

        User tester = new User("mir", "pw", "name", 9, "bio", interests);
        Create.createUser(tester);

    }
    public static void testRecipe(){
        ArrayList<String> interests = new ArrayList<>();
        interests.add("Western");
         Recipe recipe = new Recipe("nada", "love",interests, "bun up food", 7, 220, "img_208.png",
                "short n sweet", 10);
        Create.createRecipe(recipe);

    }
}
