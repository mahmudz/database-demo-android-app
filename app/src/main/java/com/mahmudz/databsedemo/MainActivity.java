package com.mahmudz.databsedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button insertBTN, exitBTN, displayBTN;
    EditText inputName, inputPhone;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBTN = findViewById(R.id.insertBTN);
        exitBTN = findViewById(R.id.exitBTN);
        displayBTN = findViewById(R.id.displayBTN);

        inputName = findViewById(R.id.inputName);
        inputPhone = findViewById(R.id.inputPhone);


        db = openOrCreateDatabase("database_demo",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons(name VARCHAR,phone VARCHAR);");

        insertBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("INSERT INTO persons VALUES('" + inputName.getText().toString() + "', '" + inputPhone.getText().toString() + "');");
                inputName.setText("");
                inputPhone.setText("");

                Toast.makeText(getApplicationContext(),"Row Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        displayBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displayPage = new Intent(getApplicationContext(), DisplayActivity.class);

                startActivity(displayPage);
            }
        });
    }
}
