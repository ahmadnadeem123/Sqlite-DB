package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button b1,b2,b3,b4;
    EditText e1, e2;
    ListView v1;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b4=findViewById(R.id.b4);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        v1=findViewById(R.id.listview);


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                dbHelper.addStudent(e1.getText().toString());
                e1.setText("");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list);
                v1.setAdapter(arrayAdapter);



            }
        });


            v1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent obj=new Intent(MainActivity.this, MainActivity2.class);
                String name=list.get(position);


                obj.putExtra("name", name);
                startActivity(obj);



            }
        });




        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                dbHelper.delete(e2.getText().toString());
            }
        });


    }
}