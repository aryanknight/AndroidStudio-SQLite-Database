package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> arrayList=new ArrayList<String>();
        ListView listView=findViewById(R.id.listView);

        try{
            SQLiteDatabase myDatabase=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR,age INT(3))");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Aryan',19)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Katniss',19)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Sam',19)");
            Cursor c= myDatabase.rawQuery("SELECT * FROM users",null);
            int nameIndex=c.getColumnIndex("name");
            int ageIndex=c.getColumnIndex("age");
            c.moveToFirst();
            while (c != null){
                arrayList.add(c.getString(nameIndex));
                arrayList.add(Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
