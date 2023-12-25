package com.example.gestionstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText _txtLogin,_txtPassword;
    Button _btnConnection,bs;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _txtLogin = (EditText) findViewById(R.id.txtLogin);
        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _btnConnection = (Button) findViewById(R.id.btnConnection);



        // Création de la base de données ou ouverture de connexion

        _btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strLogin = _txtLogin.getText().toString().trim();
                String strPwd = _txtPassword.getText().toString();


                if (strPwd.equals("1234") && strLogin.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Bienvenue " + strLogin, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    _txtLogin.setText("");
                    _txtPassword.setText("");
                    Toast.makeText(getApplicationContext(), "Echec de connexion", Toast.LENGTH_SHORT).show();

                }
            }
        });






    }


}