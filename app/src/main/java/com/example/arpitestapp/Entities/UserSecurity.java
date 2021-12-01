package com.example.arpitestapp.Entities;

import android.os.Build;

import com.example.arpitestapp.Gateways.Create;
import com.example.arpitestapp.Gateways.Update;

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
        Update.userProfile(username, password, "password");
    }

    public void addUser(User user) {
        UsernameList.put(user.getUsername(), user);
        UserPassword.put(user.getUsername(), user.getPassword());
        Create.createUser(user);
    }

    // TODO: Does this validate usernames to check if it's taken or not??
    public void changeUsername(String username, String newUsername) {
        User user = this.UsernameList.get(username);
        user.setUsername(newUsername);
        this.UsernameList.remove(username);
        this.UsernameList.put(newUsername, user);
        Update.userProfile(username, newUsername, "username");
    }

    public void changeBio(String username, String bio) {
        User user = this.UsernameList.get(username);
        user.setBiography(bio);
        Update.userProfile(username, bio, "biography");
    }

    public void changeAge(String username, Integer age) {
        User user = this.UsernameList.get(username);
        user.setAge(age);
        Update.userProfile(username, age, "age");
    }

    public void changeName(String username, String name) {
        User user = this.UsernameList.get(username);
        user.setDisplayName(name);
        Update.userProfile(username, name, "displayName");
    }
}
