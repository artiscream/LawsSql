package com.example.arti_scream.sqllaws;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayLaws extends AppCompatActivity {


    MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_laws);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query("Laws", null, null, null, null, null,null );

        ListView listView = findViewById(R.id.displaylistview);

        ArrayList<String> theList = new ArrayList<>();

        if (c.getCount()==0){
            Toast.makeText(DisplayLaws.this, "Empty database", Toast.LENGTH_LONG).show();
        }else {
            while(c.moveToNext()){
                theList.add(c.getString(3));
                theList.add(c.getString(4));
                ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listadapter);

            }
        }

    }
}
