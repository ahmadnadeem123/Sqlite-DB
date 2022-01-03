package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText e3;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        e3=findViewById(R.id.e3);
        b=findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =getIntent();
                DbHelper dbHelper = new DbHelper(MainActivity2.this);
                dbHelper.update(intent.getStringExtra("name"), e3.getText().toString() );

                Intent obj=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(obj);

                        
            }
        });






    }
}