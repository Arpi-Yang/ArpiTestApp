package com.example.arpitestapp.Entities;

import java.util.HashMap;
import java.util.ArrayList;

public class GenreLibrary {
    public HashMap<String, HashMap<Integer, Recipe>> ListOfAllRecipes = new HashMap<>();

    public HashMap<String, HashMap<Integer, Recipe>> getListOfAllRecipes() {
        return ListOfAllRecipes;
    }
    public void addRecipes(String genre, Recipe recipe) {
        if (!this.ListOfAllRecipes.containsKey(genre)) {
            HashMap<Integer, Recipe> recipe_map = new HashMap<>();
            recipe_map.put(recipe.getID(), recipe);
            this.ListOfAllRecipes.put(genre, recipe_map);
        } else {
            HashMap<Integer, Recipe> recipe_map = this.ListOfAllRecipes.get(genre);
            recipe_map.put(recipe.getID(), recipe);
        }
    }

    // TODO: create some methods to make grabbing a recipe and testing easier
    public ArrayList<String> getAllGenres(){
        return new ArrayList<>(this.ListOfAllRecipes.keySet());
    }


    public HashMap<Integer, Recipe> getAllRecipes(String genre){
        return this.ListOfAllRecipes.get(genre);
    }
    public Recipe getRecipeByID(String genre, int id){
        return this.getAllRecipes(genre).get(id);
    }
}

