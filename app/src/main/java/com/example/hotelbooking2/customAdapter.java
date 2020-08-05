package com.example.hotelbooking2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter {

    Context c;
    ArrayList<dataHotel> hotel = new ArrayList<>();


    public customAdapter(Context c, ArrayList<dataHotel>hotel){
        this.c=c;
        this.hotel=hotel;
    }


    @Override
    public int getCount() {
        return hotel.size();
    }

    @Override
    public Object getItem(int i) {
        return hotel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.activity_detail_hotel, viewGroup, false);
        }

        TextView nama = view.findViewById(R.id.nama);
        final TextView alamat = view.findViewById(R.id.alamat);
        final TextView notel = view.findViewById(R.id.notel);

        dataHotel hotel = (dataHotel) this.getItem(i);

        final String name = hotel.getname();
        final String address = hotel.getaddress();
        final String notel1 = hotel.getnotel();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,detailHotel.class);
                buka(name,address,notel1);
            }
        }
        );
    return view;
    }

    private void buka(String...details){
        Intent i = new Intent(c,detailHotel.class);
        i.putExtra("nama",details[0]);
        i.putExtra("alamat",details[1]);
        i.putExtra("notel",details[2]);

        c.startActivity(i);
    }
    }

