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
//        // NOTE this is temporary placeholder to test android app usage
//
//        List<String> interestList1 = Arrays.asList( "Mexican", "Western");
//        List<String> interestList2 = Arrays.asList( "Chinese", "Indian", "Korean");
//
//        ArrayList<String> interest1 = new ArrayList<>(interestList1);
//        ArrayList<String> interest2 = new ArrayList<>(interestList2);
//
//        User user1 = new User("username1", "password1", "Dan",
//                10, "I love food", interest1);
//
//        User user2 = new User("username2", "password2", "Bob",
//                11, "I love chicken", interest2);
//
//        User user3 = new User("a", "a", "Bob",
//                11, "for text ui testing", interest2);


        UserSecurity us = Read.populateUserSecurity();
//        us.addUser(user1);
//        us.addUser(user2);
//        us.addUser(user3);

        return us;
    }
    public static GenreLibrary createDataset() {
        return Read.populateGenreLibrary();

    }

    // TODO: create another function that will create a genre list from the database

}
