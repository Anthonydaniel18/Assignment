package com.example.hotelbooking2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailHotel extends AppCompatActivity {

    TextView namae,alamate,notele;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        namae = findViewById(R.id.nama);
        alamate = findViewById(R.id.alamat);
        notele = findViewById(R.id.notel);

        Intent i = this.getIntent();

        String nama = i.getExtras().getString("nama");
        String alamat = i.getExtras().getString("alamat");
        String notel = i.getExtras().getString("notel");

        namae.setText(nama);
        alamate.setText(alamat);
        notele.setText(notel);
    }
}
