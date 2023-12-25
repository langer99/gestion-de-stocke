package com.example.gestionstock;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class deleteProduit extends AppCompatActivity {

    private TextView t1;
    private EditText t2;
    private Button send;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_produit);

        // Initialize variables using findViewById
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        send = findViewById(R.id.send);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);


        // Now you can use t1, t2, and send as references to your UI elements
        // For example, you can set a click listener on the button
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event here
                String nomproduit = t2.getText().toString();

                Cursor cur = db.rawQuery("select * from composants where nom like ?", new String[]{t2.getText().toString().trim()});


                if (cur.moveToFirst()) {


                    Toast.makeText(getApplicationContext(), "successfully operation", Toast.LENGTH_SHORT).show();


                    db.delete("composants", "nom " + " = ?", new String[]{nomproduit});

                    Toast.makeText(getApplicationContext(), " product was deleted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(deleteProduit.this, MainActivity.class);
                    setResult(1, intent);
                    finish();


                } else {
                    Toast.makeText(getApplicationContext(), "Erruer .", Toast.LENGTH_SHORT).show();
                    t2.setText("");

                }

            }
        });
    }
}
