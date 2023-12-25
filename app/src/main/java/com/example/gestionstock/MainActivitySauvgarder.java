package com.example.gestionstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivitySauvgarder extends AppCompatActivity {

    private Button send;
    private TextInputEditText name, email, tel;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sauvgarder);
        name = findViewById(R.id.name_add_user);
        email = findViewById(R.id.email_add_user);
        tel = findViewById(R.id.tel_add_user);
        send = findViewById(R.id.submit_add_user);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);


        send.setOnClickListener(
                v -> {
                    String name_ = name.getText().toString();
                    String email_ = email.getText().toString();
                    String tel_ = email.getText().toString();
                    try {
                        db.execSQL("INSERT INTO membres VALUES(NULL, ?, ?, ?)", new Object[]{name_, email_, tel_});

                        Log.d("TAG", "onCreate: user added");
                        setResult(1);
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Email Exists", Toast.LENGTH_SHORT);
                    }

                }
        );


    }
}