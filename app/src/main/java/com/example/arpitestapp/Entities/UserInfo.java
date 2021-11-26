package com.example.arpitestapp.Entities;

import java.util.ArrayList;

public class UserInfo {
    /**
     * This class is the UserInfo Entity to be used during user creation.
     * It possesses 6 attributes:
     * •username - the unique identifier for the user (String)
     * •password - the collection of characters to be used as a security check during log in (String)
     * •displayName - the name the user chooses to appear as to the public (String)
     * •age - the age of the user (int)
     * •biograpphy - a description of the user (String)
     * •interests - a list of genres(Strings) the user is interested in (ArrayList<String>)
     */
    private String username;
    private String password;
    private String displayName;
    private int age;
    private String biography;
    private ArrayList<String> interests;

    /**
     * Constructors for a UserInfo
     */
    public UserInfo(){}
    public UserInfo(String username, String password, String displayName, int age, String biography,
                    ArrayList<String> interests){
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.age = age;
        this.biography = biography;
        this.interests = interests;
    }

    /**
     * Getter Methods for UserInfo
     */
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getDisplayName() {return displayName;}
    public int getAge() {return age;}
    public String getBiography() {return biography;}
    public ArrayList<String> getInterests() {return interests;}

    /**
     * Setter Methods for UserInfo
     */
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setDisplayName(String displayName) {this.displayName = displayName;}
    public void setAge(int age) {this.age = age;}
    public void setBiography(String biography) {this.biography = biography;}
    public void setInterests(ArrayList<String> interests) {this.interests = interests;}





}

