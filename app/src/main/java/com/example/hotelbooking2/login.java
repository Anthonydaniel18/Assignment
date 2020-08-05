package com.example.hotelbooking2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    EditText em,pass;
    Button lgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        em = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        lgn = (Button)findViewById(R.id.login);

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = em.getText().toString();
                String password = pass.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABEL_NAME + " WHERE "
                        + DatabaseHelper.COL_2 + "=? AND "
                        + DatabaseHelper.COL_3 + "=?", new String[]{email,password});

                if(cursor!=null){
                    if(cursor.getCount()>0){
                        Toast.makeText(getApplicationContext(),"bisa loginn",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"tidakk bisaa",Toast.LENGTH_LONG).show();
                    }
                }

                Intent i = new Intent(login.this,home.class);
                startActivity(i);
            }
        });

    }
}
