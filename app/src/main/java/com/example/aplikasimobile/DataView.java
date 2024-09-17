package com.example.aplikasimobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplikasimobile.helper.DbHelper;
import com.example.aplikasimobile.model.Data;
import com.example.aplikasimobile.adapter.Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataView extends AppCompatActivity {
    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<>();
    BaseAdapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NUMBER = "number";
    public static final String TAG_NAME = "name";
    public static final String TAG_BIRTH = "birth";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_ADDRESS = "address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("List Data");
        }

        SQLite = new DbHelper(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        listView = findViewById(R.id.list_view);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataView.this, InputData.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(DataView.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                final String idx = itemList.get(position).getId();
                final String number = itemList.get(position).getNumber();
                final String name = itemList.get(position).getName();
                final String birth = itemList.get(position).getBirth();
                final String gender = itemList.get(position).getGender();
                final String address = (String) itemList.get(position).getAddress();

                final CharSequence[] dialogItem = {"Lihat Data", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(DataView.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(DataView.this, DetailDataActivity.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NUMBER, number);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_BIRTH, birth);
                                intent.putExtra(TAG_GENDER, gender);
                                intent.putExtra(TAG_ADDRESS, address);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent editIntent = new Intent(DataView.this, InputData.class);
                                editIntent.putExtra(TAG_ID, idx);
                                editIntent.putExtra(TAG_NUMBER, number);
                                editIntent.putExtra(TAG_NAME, name);
                                editIntent.putExtra(TAG_BIRTH, birth);
                                editIntent.putExtra(TAG_GENDER, gender);
                                editIntent.putExtra(TAG_ADDRESS, address);
                                startActivity(editIntent);
                                break;
                            case 2:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(DataView.this, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void getAllData(){
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String number = row.get(i).get(TAG_NUMBER);
            String name = row.get(i).get(TAG_NAME);
            String birth = row.get(i).get(TAG_BIRTH);
            String gender = row.get(i).get(TAG_GENDER);
            String address = row.get(i).get(TAG_ADDRESS);

            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setNumber(number);
            data.setBirth(birth);
            data.setGender(gender);
            data.setAddress(address);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        itemList.clear();
        getAllData();
    }
}