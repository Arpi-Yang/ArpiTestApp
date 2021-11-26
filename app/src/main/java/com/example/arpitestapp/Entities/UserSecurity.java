package com.example.arpitestapp.Entities;

import android.os.Build;

import java.util.HashMap;

public class UserSecurity {
    // mapping of username to password
    private HashMap<String, String> UserPassword = new HashMap<>();

    // mapping of username to user
    private HashMap<String, User> UsernameList = new HashMap<>();

    public HashMap<String, User> getUsernames() {return UsernameList;}

    public User getUserByID (String username){
        return UsernameList.get(username);
    }
    public boolean validateLogin (String username, String password){
        return UserPassword.get(username).equals(password);
    }

    public boolean checkPassword(String username, String password){
        try{
            if (this.UserPassword.get(username).equals(password)){
                System.out.println("Password is valid");
                return true;
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void changePassword(String username, String password){
        // Changes user password for given username
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.UserPassword.replace(username, password);
        }

        // sets the new password for user.
        this.UsernameList.get(username).setPassword(password);
    }

    public void addUser(User user) {
        UsernameList.put(user.getUsername(), user);
        UserPassword.put(user.getUsername(), user.getPassword());
    }
}
