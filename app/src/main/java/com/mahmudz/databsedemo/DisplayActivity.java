package com.mahmudz.databsedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

public class DisplayActivity extends AppCompatActivity {
    TextView outName, outPhone;
    Button prevBTN, nextBTN, homeBTN;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        prevBTN = findViewById(R.id.prevBTN);
        nextBTN = findViewById(R.id.nextBTN);
        homeBTN = findViewById(R.id.homeBTN);

        outName = findViewById(R.id.outName);
        outPhone = findViewById(R.id.outPhone);

        homeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homePage);
            }
        });

        db = openOrCreateDatabase("database_demo",MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from persons",null);

        c.moveToFirst();

        outName.setText(c.getString(c.getColumnIndex("name")));
        outPhone.setText(c.getString(c.getColumnIndex("phone")));

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.moveToNext();
                    outName.setText(c.getString(c.getColumnIndex("name")));
                    outPhone.setText(c.getString(c.getColumnIndex("phone")));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Last Record",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


        prevBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    c.moveToPrevious();
                    outName.setText(c.getString(c.getColumnIndex("name")));
                    outPhone.setText(c.getString(c.getColumnIndex("phone")));
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"First Record",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
