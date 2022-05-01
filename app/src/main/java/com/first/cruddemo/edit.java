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

public class edit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4, ed5;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.contact);
        ed4 = findViewById(R.id.fee);
        ed5 = findViewById(R.id.id);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("course").toString();
        String t4 = i.getStringExtra("contact").toString();
        String t5 = i.getStringExtra("fee").toString();

        ed5.setText(t1);
        ed4.setText(t5);
        ed3.setText(t4);
        ed2.setText(t3);
        ed1.setText(t2);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                edit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                delete();
            }
        });
    }

    public void edit(){
        try{
            String name = ed1.getText().toString();
            String course = ed2.getText().toString();
            String contact = ed3.getText().toString();
            String fee = ed4.getText().toString();
            String id = ed5.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            String sql = "UPDATE records SET name=?,course=?,contact=?,fee=? WHERE id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, course);
            statement.bindString(3, contact);
            statement.bindString(4, fee);
            statement.bindString(5, id);
            statement.execute();
            Toast.makeText(this, "Record has been updated!", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();

        }catch(Exception ex){
            Toast.makeText(this, "Record cannot be updated!", Toast.LENGTH_LONG).show();
        }
    }
    public void delete(){
        try{
            String id = ed5.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            String sql = "DELETE FROM records WHERE id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, id);
            statement.execute();
            Toast.makeText(this, "Record has been deleted!", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();

        }catch(Exception ex){
            Toast.makeText(this, "Record cannot be deleted!", Toast.LENGTH_LONG).show();
        }
    }
}