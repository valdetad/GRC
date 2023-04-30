package com.example.grc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//declaring several variables and initializes them
public class item_list extends AppCompatActivity {
    ListView listView1;
    EditText inputtext1;
    Button btnAdd, btnUpdate, btnDelete;

    ArrayList<String> fruits = new ArrayList<String>();
    ArrayAdapter<String> myAdapter1;

    Integer indexVal;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView1 = findViewById(R.id.listview1);
        btnAdd = findViewById(R.id.button2);
        btnAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btnUpdate = findViewById(R.id.button3);
        btnUpdate.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        btnDelete = findViewById(R.id.button4);
        btnDelete.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        inputtext1 = findViewById(R.id.editText);

        myAdapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, fruits);

        listView1.setAdapter(myAdapter1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer i = position;
                item = myAdapter1.getItem(i).toString() + " has been selected";
                indexVal = i;
                Toast.makeText(item_list.this, item, Toast.LENGTH_SHORT).show();

            }
        });

        // Update btn
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringval = inputtext1.getText().toString();
                fruits.set(indexVal, stringval);
                myAdapter1.notifyDataSetChanged();
            }
        });

        // Delete btn
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexVal != null) {
                    String item = fruits.get(indexVal) + " has been deleted";
                    Toast.makeText(item_list.this, item, Toast.LENGTH_SHORT).show();
                    fruits.remove(indexVal.intValue());
                    myAdapter1.notifyDataSetChanged();
                    indexVal = null;
                } else {
                    Toast.makeText(item_list.this, "The list is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Add btn
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringval = inputtext1.getText().toString();
                //fruits.add(stringval);
               // myAdapter1.notifyDataSetChanged();
                if(stringval == null || stringval.isEmpty()) {
                    Toast.makeText(item_list.this, "Please enter an item!", Toast.LENGTH_SHORT).show();
                } else {
                    fruits.add(stringval);
                    //The adapter is notified again to refresh the view with the updated data.
                    myAdapter1.notifyDataSetChanged();

                }
            }
        });
    }
}
