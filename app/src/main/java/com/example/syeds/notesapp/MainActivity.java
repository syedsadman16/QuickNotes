package com.example.syeds.notesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    static ArrayList<String> notes = new ArrayList<String>();
    static ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Created ListView
        final ListView listView = findViewById(R.id.listView);

        //Get saved data
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.syeds.notesapp", MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Notes", null);

        if(set == null){
            //On first use
            new AlertDialog.Builder(MainActivity.this)
                    .setIcon(android.R.drawable.star_big_on)
                    .setTitle("Quick Start")
                    .setMessage("This is a basic note taking app to quickly jot down notes.")
                    .setPositiveButton("Ok", null);
            notes.add("How to use: Click activity bar to create a new note or click on note to edit. Hit the back key to save and go to notes summary. Hold note to delete ");
        } else {
            //If there is data inside the set, then convert to arrayList
            notes = new ArrayList(set);
        }

        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditNote.class);
                //In order to access array index, we need to pass the position

                intent.putExtra("notePosition",position);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Prevent ovveride of onClick,
                final int deletePos = position;

                //Add alert dialog before deleting
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Delete")
                        .setMessage("You are deleting this note")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(deletePos);
                                //notify adapter of change
                                adapter.notifyDataSetChanged();

                                //Convert arraylist to set
                                HashSet<String> hashSet = new HashSet<>(MainActivity.notes);
                                //Now save set
                                sharedPreferences.edit().putStringSet("Notes",hashSet).apply();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Create menu item
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //whenever an item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         //check menu item and get the id
         if (item.getItemId() == R.id.newNote) {
             //when new note is clicked
             Intent toEditNote = new Intent(getApplicationContext(), EditNote.class);
             startActivity(toEditNote);
             return true;
         }
         else {
             return false;
         }

    }

}
