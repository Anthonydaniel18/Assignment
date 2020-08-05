package com.example.hotelbooking2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button reg,lgn;
    EditText emi,pass,cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);

        reg = (Button)findViewById(R.id.register);
        lgn = (Button)findViewById(R.id.login);
        emi = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        cpass = (EditText)findViewById(R.id.confirmpassword);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String email = emi.getText().toString();
                String password = pass.getText().toString();
                insert(email,password);
                Toast.makeText(getApplicationContext(),"HAII",Toast.LENGTH_LONG).show();

            }
        });

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });

    }

    public void insert(String email, String password){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_2,email);
        cv.put(DatabaseHelper.COL_3,password);
        db.insert(DatabaseHelper.TABEL_NAME,null,cv);
    }

}
