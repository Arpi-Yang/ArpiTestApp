package com.example.arpitestapp.Gateways;

import com.example.arpitestapp.Entities.GenreLibrary;
import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.User;
import com.example.arpitestapp.Entities.UserSecurity;

import java.util.*;

public class Constants {
    // Create Constants

    public static GenreLibrary GENRELIBRARY =  Constants.createDataset();
    public static UserSecurity USERSECURITY = Constants.createUsers();

    public static UserSecurity createUsers(){
        List<String> interestList1 = Arrays.asList( "Mexican", "Western");
        List<String> interestList2 = Arrays.asList( "Chinese", "Indian", "Korean");

        ArrayList<String> interest1 = new ArrayList<>(interestList1);
        ArrayList<String> interest2 = new ArrayList<>(interestList2);

        User user1 = new User("username1", "password1", "Dan",
                10, "I love food", interest1);

        User user2 = new User("username2", "password2", "Bob",
                11, "I love chicken", interest2);

        User user3 = new User("a", "a", "Bob",
                11, "for text ui testing", interest2);

        UserSecurity us = new UserSecurity();
        us.addUser(user1);
        us.addUser(user2);
        us.addUser(user3);

        return us;
        //return Read.populateUserSecurity();
    }
    public static GenreLibrary createDataset() {
        ArrayList<String> r1genres = new ArrayList<>();
        r1genres.add("Mexican");
        ArrayList<String> r2genres = new ArrayList<>();
        r2genres.add("Chinese");
        ArrayList<String> r3genres = new ArrayList<>();
        r3genres.add("Western");
        ArrayList<String> r4genres = new ArrayList<>();
        r4genres.add("Egyptian");
        ArrayList<String> r5genres = new ArrayList<>();
        r5genres.add("Canadian");

        Recipe recipe1 = new Recipe("Set on Fire", "Salt",
                r1genres, "Burnt Food", 5, 1, "burnt.jpg", "",5);

        Recipe recipe2 = new Recipe("Throw in Oven", "Chicken",
                r2genres, "Chicken", 4, 2, "chicken.jpg", "",10);

        Recipe recipe3 = new Recipe("Pan fry in pan", "Steak, butter",
                r3genres, "Good Steak", 5, 3, "steak.jpg", "",30);

        Recipe recipe4 = new Recipe("Boil in water", "Spinach, Mushrooms",
                r4genres, "Random Veggies", 2, 4, "veg.jpg", "",15);

        Recipe recipe5 = new Recipe("Throw maple syrup on pancakes", "Pancakes, salt, butter",
                r5genres, "Pancakes", 3, 5, "pancake.jpg", "",5);

        Recipe recipe6 = new Recipe("1. Catch a shark. \n2. Boil the shark for 99 seconds.\n3. Eat the shark.", "One shark",
                r2genres, "Shark Fin Soup", 5, 6, "pancake.jpg", "This is a chinese food",100);
        GenreLibrary dataset = new GenreLibrary();
        for (String g: recipe1.getGenre()){
            dataset.addRecipes(g, recipe1);
        }
        for (String g: recipe2.getGenre()){
            dataset.addRecipes(g, recipe2);
        }
        for (String g: recipe3.getGenre()){
            dataset.addRecipes(g, recipe3);
        }
        for (String g: recipe4.getGenre()){
            dataset.addRecipes(g, recipe4);
        }
        for (String g: recipe5.getGenre()){
            dataset.addRecipes(g, recipe5);
        }
        for (String g: recipe6.getGenre()){
            dataset.addRecipes(g, recipe6);
        }
        return dataset;
        //return Read.populateGenreLibrary();
    }

}
