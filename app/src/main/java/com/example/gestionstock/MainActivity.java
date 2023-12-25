package com.example.gestionstock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button bs, be, br, badd, Member, Produit;
    private ImageButton brech;
    private EditText txtRechercheComposant, txtcomposant, txtqt, txtfamille, txtdate;
    private String queryInsertCat = "INSERT INTO categories VALUES(NULL,?, ?)";
    private String queryInsertCom = "INSERT INTO composants VALUES(NULL, ?, ?, ?, ?)";
    private ListView listView;
    private List<ItemComponent> listItem;
    private SQLiteDatabase db;
    private MaterialToolbar topappbar;
    ComponentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.list_dashboard);


        topappbar = findViewById(R.id.topappbar);
        topappbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.addmember) {
                    Intent intent = new Intent(MainActivity.this, MainActivitySauvgarder.class);
                    startActivityForResult(intent, 7);
                } else if (item.getItemId() == R.id.deletemember) {
                    Intent intent = new Intent(MainActivity.this, deleteMember.class);
                    startActivityForResult(intent, 8);

                } else if (item.getItemId() == R.id.addproduit) {
                    Intent intent = new Intent(MainActivity.this, add.class);
                    startActivityForResult(intent, 5);
                } else if (item.getItemId() == R.id.deleteproduit) {
                    Intent intent = new Intent(MainActivity.this, deleteProduit.class);
                    startActivityForResult(intent, 9);
                } else if (item.getItemId() == R.id.history_) {
                    startActivity(new Intent(getApplicationContext(), Historique.class));
                }
                return true;
            }
        });


        txtRechercheComposant = (EditText) findViewById(R.id.txtRechercheComposant);


        brech = (ImageButton) findViewById(R.id.btnRecherche);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);


        db.execSQL("CREATE TABLE IF NOT EXISTS composants (id_comp INTEGER PRIMARY KEY, nom VARCHAR  , date_acq DATE, quantite INTEGER, id_cat INTEGER  REFERENCES categories(id_cat));");
        db.execSQL("CREATE TABLE IF NOT EXISTS membres (id_user INTEGER primary key , nom VARCHAR, email  VARCHAR UNIQUE, tel VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS historique ( id_comp INTEGER  , id_user INTEGER,   quantite2 INTEGER ,date DATE,id_hist INTEGER PRIMARY KEY AUTOINCREMENT ,FOREIGN KEY (id_comp) REFERENCES composants(id_comp), FOREIGN KEY (id_user) REFERENCES membres(id_user));");
        db.execSQL("CREATE TABLE IF NOT EXISTS categories(id_cat INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, description TEXT )");


        SQLiteStatement s = db.compileStatement("select count(*) from composants;");
        long c = s.simpleQueryForLong();
        Cursor cursor = db.rawQuery("SELECT * FROM categories;", null);

        if (cursor.getCount() == 0) {
            db.execSQL(queryInsertCat, new Object[]{"carte development", "description carte de development"});
            db.execSQL(queryInsertCat, new Object[]{"capteur", "description capteur"});
            db.execSQL(queryInsertCat, new Object[]{"actionneur", "description Actionneur"});
            db.execSQL(queryInsertCat, new Object[]{"electronique", "description electronique"});
        }

        if (c == 0) {
            db.execSQL(queryInsertCom, new Object[]{"esp32", "22/02/2002", 100, 1});
            db.execSQL(queryInsertCom, new Object[]{"rasberry pi", "22/02/2002", 103, 1});
            db.execSQL(queryInsertCom, new Object[]{"led red", "22/02/2002", 1000, 3});
            db.execSQL(queryInsertCom, new Object[]{"servo motor", "22/02/2002", 100, 3});
            db.execSQL(queryInsertCom, new Object[]{"battery lithom", "22/02/2002", 100, 4});
            db.execSQL(queryInsertCom, new Object[]{"bmp280", "22/02/2002", 100, 2});
        }

        listItem = new ArrayList<>();
        brech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = db.rawQuery("select * from composants where nom like ? ", new String[]{"%" + txtRechercheComposant.getText().toString().trim() + "%"});
                Log.d("TAG", "onClick: " + txtRechercheComposant.getText().toString().trim());

                Log.d("TAG", "onClick: " + cur.getCount());
                if (txtRechercheComposant.getText().toString().trim().isEmpty()) {
                    bindData();
                    Log.d("TAG", "onClick: binded");
                }
                try {
                    List<ItemComponent> components = new ArrayList<>();


                    while (cur.moveToNext()) {
                        ItemComponent item = new ItemComponent(cur.getInt(0),
                                cur.getString(1),
                                cur.getString(2),
                                cur.getInt(3),
                                cur.getInt(4));
                        components.add(item);
                    }
                    listView.deferNotifyDataSetChanged();
//                    listView.setAdapter(new ComponentAdapter(getApplicationContext(), components));
                    adapter.listComponents = components;
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemComponent item = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                intent.putExtra("name", item.getName());
                intent.putExtra("date", item.getDate());
                intent.putExtra("quantity", item.getQuantity());
                intent.putExtra("idCat", item.getCategorie_id());
                intent.putExtra("id", item.getId_comp());
                startActivityForResult(intent, 1);

            }
        });

        txtRechercheComposant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Cursor cur = db.rawQuery("select * from composants where nom like ? ", new String[]{"%" + s.toString().trim() + "%"});
                Log.d("TAG", "onClick: " + txtRechercheComposant.getText().toString().trim());

                Log.d("TAG", "onClick: " + cur.getCount());
                if (txtRechercheComposant.getText().toString().trim().isEmpty()) {
                    bindData();
                    Log.d("TAG", "onClick: binded");
                }
                try {
                    List<ItemComponent> components = new ArrayList<>();


                    while (cur.moveToNext()) {
                        ItemComponent item = new ItemComponent(cur.getInt(0),
                                cur.getString(1),
                                cur.getString(2),
                                cur.getInt(3),
                                cur.getInt(4));
                        components.add(item);
                    }
                    listView.deferNotifyDataSetChanged();
//                    listView.setAdapter(new ComponentAdapter(getApplicationContext(), components));
                    adapter.listComponents = components;
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bindData();
        Log.d("TAG", "onCreate: " + listItem);


    }


    public void bindData() {

        Cursor cursor1 = db.rawQuery("SELECT * FROM composants", null);

        while (cursor1.moveToNext()) {
            ItemComponent item = new ItemComponent(cursor1.getInt(0),
                    cursor1.getString(1),
                    cursor1.getString(2),
                    cursor1.getInt(3),
                    cursor1.getInt(4));

            listItem.add(item);
        }


        Log.d("Tag From bind", "bindData: " + listItem);
        adapter = new ComponentAdapter(getApplicationContext(), listItem);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: restart ");
        bindData();

    }
}
