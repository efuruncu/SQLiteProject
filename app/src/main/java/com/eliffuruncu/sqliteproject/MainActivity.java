package com.eliffuruncu.sqliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.sqlTxt);

        try {
            SQLiteDatabase database=this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)");

            //veri kaydetme
           // database.execSQL("INSERT INTO musicians (name,age) VALUES ('James',50)");
           // database.execSQL("INSERT INTO musicians (name,age) VALUES ('Lars',60)");
            //database.execSQL("INSERT INTO musicians (name,age) VALUES ('Kirk',55)");

            //Güncelleme
            //database.execSQL("UPDATE musicians SET age = 61 where name = 'Lars'");

            //Silme
           // database.execSQL("DELETE FROM musicians WHERE id = 2");

            //Filtreleme
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians where age >52",null);

           //like
           // Cursor cursor=database.rawQuery("SELECT * FROM musicians WHERE name like 'K%'",null);

            //Tüm kayıtlı verileri getirir.
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idx= cursor.getColumnIndex("id");

            ArrayList<Integer> ids=new ArrayList<>();
            ArrayList<String> names=new ArrayList<>();
            ArrayList<Integer> ages=new ArrayList<>();

            while(cursor.moveToNext())
            {
                System.out.println("Name: "+ cursor.getString(nameIx));
                System.out.println("Age: "+ cursor.getString(ageIx));
                System.out.println("Id: "+ cursor.getInt(idx));
                ids.add(cursor.getInt(idx));
                names.add(cursor.getString(nameIx));
                ages.add(cursor.getInt(idx));
            }
            cursor.close();

            String RegisteredDatas="";
            for(int i=0;i<names.size();i++)
            {
                RegisteredDatas+=" Id: "+ids.get(i)+" - Name: "+names.get(i)+" - Age: "+ages.get(i);
            }
            txt.setText(RegisteredDatas);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
