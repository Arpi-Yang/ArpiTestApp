package com.example.arpitestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.arpitestapp.Entities.User;
import com.example.arpitestapp.Gateways.ReadRecipe;
import com.example.arpitestapp.Gateways.ReadUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView mConditionTextView;
    Button mButtonSunny;
    Button mButtonFoggy;

    private static final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference mRecipeRef = mRootRef.child("recipes");
    private static final DatabaseReference mUserRef = mRootRef.child("users");

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
        mConditionTextView.setText("lmao");

        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot mirSnapshot = snapshot.child("mir");
                User user = ReadUser.readUser(mirSnapshot);
                ArrayList<String> userInterests = user.getInterests();
                String displayText = userInterests.toString();
                mConditionTextView.setText(displayText);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}