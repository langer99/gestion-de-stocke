package com.example.gestionstock;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Historique extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        ListView list1 = findViewById(R.id.l1);
        List<Histitem> listhis = new ArrayList<>();
        sqLiteDatabase = openOrCreateDatabase("stock", MODE_PRIVATE, null);

        Cursor cu = sqLiteDatabase.rawQuery("select * from historique", null);

        while (cu.moveToNext()) {
            Histitem item = new Histitem(cu.getInt(4), cu.getInt(0), cu.getInt(1), cu.getInt(2), cu.getString(3));
            listhis.add(item);
            list1.setAdapter(new HistoryAdapter(getApplicationContext(), listhis));

            // nom TEXT , id INTEGER,   quantite2 INTEGER ,idhist INTEGER
//            Histitem history = new Histitem(cu.getString(0), cu.getInt(1), cu.getInt(2),cu.getInt((3)));
        }
    }
}