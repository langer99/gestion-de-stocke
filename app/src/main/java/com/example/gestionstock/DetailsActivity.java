package com.example.gestionstock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {


    TextView name, date, quantity, catName, catDescription;

    SQLiteDatabase db;
    Button sale, ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);


        String name_ = intent.getStringExtra("name");
        String date_ = intent.getStringExtra("date");
        int quantity_ = intent.getIntExtra("quantity", -1);
        int idCat = intent.getIntExtra("idCat", -1);
        int id = intent.getIntExtra("id", -1);


        Log.d("TAG", "onCreate: " + id + " " + idCat);

        Cursor cur = db.rawQuery("SELECT * FROM categories WHERE id_cat = ?", new String[]{String.valueOf(idCat)});

        if (cur.getCount() == 0) {
            finish();
        }

        cur.moveToFirst();

        Log.d("TAG", "onCreate: " + cur.getColumnCount());
        Log.d("TAG", "onCreate: " + cur.getColumnIndex("name"));
        Log.d("TAG", "onCreate: " + cur.getColumnIndex("description"));
        String nameCat = cur.getString(1);
        String descCat = cur.getString(2);

        Log.d("TAG", "onCreate: " + nameCat + " " + descCat);


        name = findViewById(R.id.nameDetail);
        name.setText(name_);
        date = findViewById(R.id.dateDetail);
        date.setText("Date aqu: " + date_);
        quantity = findViewById(R.id.quantityDetail);
        quantity.setText("Quantity: " + quantity_);
        sale = findViewById(R.id.sale);
        ret = findViewById(R.id.ret);

        catName = findViewById(R.id.CatNameDetail);
        catName.setText(nameCat);
        catDescription = findViewById(R.id.descriptionDetail);
        catDescription.setText(descCat);


        sale.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MainActivityEmprunter.class);
            intent1.putExtra("id", id);
            intent1.putExtra("nameComp", name_);
            startActivityForResult(intent1, 1);
        });

        ret.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), MainActivityRetour.class);
            intent1.putExtra("id", id);
            startActivity(intent1);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            setResult(1);
            Log.d("TAG", "onActivityResult: resualt");
            finish();
        }
    }
}