package com.example.gestionstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class add extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SQLiteDatabase db;
    private TextInputEditText nameComponent, date, quantity;
    private Button submit;
    private Spinner spinner;
    private List<ItemCategory> list;
    private ItemCategory selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        nameComponent = findViewById(R.id.id_name_add);
        date = findViewById(R.id.date_add_item);
        quantity = findViewById(R.id.qu_item_add);
        submit = findViewById(R.id.buttonAdd);

        submit.setOnClickListener(v -> {
            String name = nameComponent.getText().toString();
            String date_ = date.getText().toString();
            int quantity_ = Integer.parseInt(quantity.getText().toString());
            int cat_id = selectedItem.getId_cat();
            db.execSQL("INSERT INTO composants VALUES(NULL, ?, ?, ?, ?)", new Object[]{name, date_, quantity_, cat_id});
            Log.d("TAG", "onCreate: inserted");
            finish();
        });
        list = new ArrayList<>();

        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);

        Cursor cu = db.rawQuery("SELECT * FROM categories", null);
        while (cu.moveToNext()) {
            ItemCategory item = new ItemCategory(cu.getInt(0), cu.getString(1), cu.getString(2));
            list.add(item);
        }
        selectedItem = list.get(0);
        spinner = findViewById(R.id.spinner);

        Log.d("TAG", "onCreate: " + list);

        ArrayAdapter<ItemCategory> adapterCategory = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
        spinner.setAdapter(adapterCategory);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = list.get(position);
        Log.d("TAG", "onItemSelected: " + selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}