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
    EditText background;
    String color;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        //Get the position of array index
        position = intent.getIntExtra("notePosition", -1);
        editText.setText(MainActivity.notes.get(position));


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

        color = "#D4EFDF";

        MainActivity.listBackground.getChildAt(position).setBackgroundColor(Color.parseColor(color));

        background = findViewById(R.id.editText);
        background.setBackgroundColor(Color.parseColor("#D4EFDF"));
    }

    public void changeWhite(View view){
        color = "#FEF9E7";

        MainActivity.listBackground.getChildAt(position).setBackgroundColor(Color.parseColor(color));


        background = findViewById(R.id.editText);
        background.setBackgroundColor(Color.parseColor("#FEF9E7"));
    }

    public void changeBlue(View view){
        color = "#AED6F1";

        MainActivity.listBackground.getChildAt(position).setBackgroundColor(Color.parseColor(color));

        background = findViewById(R.id.editText);
        background.setBackgroundColor(Color.parseColor("#AED6F1"));
    }

    public void changeYellow(View view){
        color = "#F9E79F";

        MainActivity.listBackground.getChildAt(position).setBackgroundColor(Color.parseColor(color));

        background = findViewById(R.id.editText);
        background.setBackgroundColor(Color.parseColor("#F9E79F"));
    }

    public void changeRed(View view){
        color = "#F1948A";

        MainActivity.listBackground.getChildAt(position).setBackgroundColor(Color.parseColor(color));

        background = findViewById(R.id.editText);
        background.setBackgroundColor(Color.parseColor("#F1948A"));
    }

    public void changePurple(View view){
        color = "#D2B4DE";

        MainActivity.listBackground.getChildAt(position).setBackgroundColor(Color.parseColor(color));

        background = findViewById(R.id.editText);
        background.setBackgroundColor(Color.parseColor("#D2B4DE"));
    }

}
