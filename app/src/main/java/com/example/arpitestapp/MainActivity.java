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
        mConditionTextView = (TextView)findViewById(R.id.textViewCondition);
        mButtonSunny = (Button)findViewById(R.id.button_sunny);
        mButtonFoggy = (Button)findViewById(R.id.button_foggy);

    }

    @Override
    protected void onStart() {
        super.onStart();

        GenreLibrary thisGenreLibrary = Read.populateGenreLibrary();
        String name = "+" + thisGenreLibrary.toString();
        mConditionTextView.setText(name);
        //Update.test();


//        mRecipeRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//
//                GenreLibrary newGenreLibrary = Read.fillGenreLibrary(dataSnapshot);
//                Recipe recipeZero = newGenreLibrary.getRecipeByID("all", 0);
//                String name0 = recipeZero.getName();
//
//                DataSnapshot recipe0 = dataSnapshot.child("0");
//                Recipe recipe = Read.readRecipe(recipe0);
//                String name = recipe.getImage();
//                mConditionTextView.setText(name0);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    //    /** Called when the user taps the Send button */
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

}