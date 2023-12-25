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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivityRetour extends AppCompatActivity {

    ListView listView;
    private Button send;
    private SQLiteDatabase db;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_retour);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);

        listView = findViewById(R.id.details_history);
        Intent intent = getIntent();
        int id_cmp = intent.getIntExtra("id", -1);

        if (id_cmp == -1) {
            finish();
        }

        Log.d("TAG", "onCreate: " + id_cmp);
        List<Histitem> listHistory = new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT * FROM historique WHERE id_comp = ?", new String[]{String.valueOf(id_cmp)});

        while (cursor.moveToNext()) {
            Histitem histitem = new Histitem(cursor.getInt(4), cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
            Log.d("TAG", "onCreate: history " + histitem);
            listHistory.add(histitem);
        }
        historyAdapter = new HistoryAdapter(getApplicationContext(), listHistory);
        listView.setAdapter(historyAdapter);
        Log.d("TAG", "onCreate: " + listHistory);

    }


    public void refresh(List<Histitem> list) {
        historyAdapter.his = list;
        historyAdapter.notifyDataSetChanged();
    }
}