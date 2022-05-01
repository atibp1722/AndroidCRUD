package com.first.cruddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.contact);
        ed4 = findViewById(R.id.fee);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                insert();
            }
        });
    }
    public void insert(){
        try{
            String name = ed1.getText().toString();
            String course = ed2.getText().toString();
            String contact = ed3.getText().toString();
            String fee = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR, course VARCHAR, contact VARCHAR, fee VARCHAR) ");
            String sql = "INSERT INTO records (name,course,contact,fee)VALUES(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, course);
            statement.bindString(3, contact);
            statement.bindString(4, fee);
            statement.execute();
            Toast.makeText(this, "Record has been added!", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();

        }catch(Exception ex){
            Toast.makeText(this, "Record cannot be added!", Toast.LENGTH_LONG).show();
        }
    }
}