package com.example.gestionstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.Date;

public class MainActivityEmprunter extends AppCompatActivity {
    private Button send;
    private TextInputEditText email, nameComponent, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase db = openOrCreateDatabase("stock", MODE_PRIVATE, null);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_emprunter);

        email = findViewById(R.id.email_sell);
        nameComponent = findViewById(R.id.name_component_sell);
        quantity = findViewById(R.id.quantitySell);
        send = findViewById(R.id.buttonSell);

        int id = getIntent().getIntExtra("id", -1);
        String nameCom = getIntent().getStringExtra("nameComp");
        nameComponent.setText(nameCom);

        if (id == -1) {
            finish();

        }
        Date localDate = new Date();

        send.setOnClickListener(
                v -> {
                    String email_ = email.getText().toString();
                    String nameComponent_ = nameComponent.getText().toString();
                    int quantity_ = Integer.parseInt(quantity.getText().toString());
                    Cursor cursorUser = db.rawQuery("SELECT * FROM membres WHERE email = ?", new String[]{email_});
                    Cursor cursorProduit = db.rawQuery("SELECT * FROM composants WHERE id_comp = ?", new String[]{String.valueOf(id)});
                    cursorProduit.moveToNext();
                    if (!cursorUser.moveToNext()) {
                        setResult(-1);
                        finish();
                    } else {
                        if (quantity_ > cursorProduit.getInt(3)) {
                            Toast.makeText(getApplicationContext(), "Not Enough Quantity", Toast.LENGTH_SHORT);
                        }
                        db.execSQL("INSERT INTO historique VALUES( ?, ?, ?,?, null)", new Object[]{id, cursorUser.getInt(0), quantity_, localDate.toString()});
                        db.execSQL("UPDATE composants SET quantite = quantite - ? WHERE id_comp = ? ", new Object[]{quantity_, id});
                        setResult(1);
                        Log.d("TAG", "onCreate: end with " + 1);
                        finish();
                    }


                }
        );


    }
}