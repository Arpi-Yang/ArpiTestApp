package com.example.arpitestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arpitestapp.Entities.GenreLibrary;
import com.example.arpitestapp.Entities.Recipe;
import com.example.arpitestapp.Entities.User;
import com.example.arpitestapp.Gateways.Constants;
import com.example.arpitestapp.Gateways.Create;
import com.example.arpitestapp.Gateways.Update;
import com.example.arpitestapp.Gateways.Read;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    TextView mConditionTextView;
    Button mButtonSunny;
    Button mButtonFoggy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI elements
        mConditionTextView = (TextView) findViewById(R.id.textViewCondition);
        mButtonSunny = (Button) findViewById(R.id.button_sunny);
        mButtonFoggy = (Button) findViewById(R.id.button_foggy);

    }

    @Override
    protected void onStart() {
        super.onStart();

//        GenreLibrary thisGenreLibrary = Read.populateGenreLibrary();
//        String name = "+" + thisGenreLibrary.toString();
//        mConditionTextView.setText(name);

        GenreLibrary testLib = Read.populateGenreLibrary();
        String answer = Boolean.toString(testLib.ListOfAllRecipes.isEmpty());

//        Recipe garbo = testLib.getRecipeByID("All", 0);


//        Recipe recipe = Constants.GENRELIBRARY.getRecipeByID("All",220);
//        mConditionTextView.setText(recipe.getDescription());
        mConditionTextView.setText(answer);


//
    }
}