package com.example.syeds.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashMap;
import java.util.HashSet;

public class EditNote extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText color;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        //Get the position of array index
        position = intent.getIntExtra("notePosition", -1);

        //If you got here by clicking list item
        if(position != -1){
            editText.setText(MainActivity.notes.get(position));
        } else {
            //When adding a new note
            MainActivity.notes.add("");
            position = MainActivity.notes.size() - 1;
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Updates array list with the text
                MainActivity.notes.set(position, editText.getText().toString());
                //Add changes to listview using adapter
                MainActivity.adapter.notifyDataSetChanged();


                //Save the data
                sharedPreferences = getApplicationContext().getSharedPreferences("com.example.syeds.notesapp", MODE_PRIVATE);
                //Convert arraylist to set
                HashSet<String> hashSet = new HashSet<>(MainActivity.notes);
                //Now save set
                sharedPreferences.edit().putStringSet("Notes",hashSet).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //Setting up the colors
    public void changeGreen(View view){
        int green = Color.parseColor("#D4EFDF");
        color = findViewById(R.id.editText);
        color.setBackgroundColor(green);
        sharedPreferences.edit().putInt("color", green).apply();

    }
    public void changeWhite(View view){
        color = findViewById(R.id.editText);
        color.setBackgroundColor(Color.parseColor("#FEF9E7"));
    }
    public void changeBlue(View view){
        color = findViewById(R.id.editText);
        color.setBackgroundColor(Color.parseColor("#AED6F1"));
    }
    public void changeYellow(View view){
        color = findViewById(R.id.editText);
        color.setBackgroundColor(Color.parseColor("#F9E79F"));
    }
    public void changeRed(View view){
        color = findViewById(R.id.editText);
        color.setBackgroundColor(Color.parseColor("#F1948A"));
    }
    public void changePurple(View view){
        color = findViewById(R.id.editText);
        color.setBackgroundColor(Color.parseColor("#D2B4DE"));
    }



}
