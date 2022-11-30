package com.example.exercise_10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText numberTV;
    private Button addBtn;
    private ListView numberListView;

    private ArrayList<String > numberList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberTV = findViewById(R.id.numberTV);
        addBtn = findViewById(R.id.addBtn);
        numberListView = findViewById(R.id.numberLV);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,numberList);
        numberListView.setAdapter(adapter);
        numberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                displayAlert(selectedItem,position);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    String number = numberTV.getText().toString();
                    int intNumber = Integer.parseInt(number);
                    numberList.add(number);
                    numberTV.setText("");
                    adapter.notifyDataSetChanged();

                }catch (NumberFormatException ex) {
                    Toast.makeText(getApplicationContext(),"Input is no a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void displayAlert(String number,int position){
        new AlertDialog.Builder(this)

                .setPositiveButton("Yes", (dialog, which) -> deleteRaw(position))
                .setTitle("Do you want to delete number " + number + "?")
                .setNegativeButton("No", null)
                .create()
                .show();
    }

    private void deleteRaw(int position){
        numberList.remove(position);
        adapter.notifyDataSetChanged();
    }
}