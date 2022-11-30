package com.example.exercise_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberTV = findViewById(R.id.numberTV);
        addBtn = findViewById(R.id.addBtn);
        numberListView = findViewById(R.id.numberLV);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,numberList);
        numberListView.setAdapter(adapter);

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
}