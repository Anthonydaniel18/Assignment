package com.example.hotelbooking2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class home extends AppCompatActivity {

    private String TAG = home.class.getSimpleName();

    private ProgressDialog pd;
    private ListView lv;

    private static String url = "https://bit.ly/34YfE67";

    //ArrayList<HashMap<String, String>> hotellist;
    List<dataHotel> dataHotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataHotelList = new ArrayList<>();

        //hotellist = new ArrayList<>();

        lv = (ListView) findViewById(R.id.listView);

        new muncul().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mybooking:
                Toast.makeText(this, "mybooking", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(home.this,MyBooking.class);
                //startActivity(intent);
                return true;

            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                //Intent intent2 = new Intent(Home.this,MainActivity.class);
                //startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class muncul extends AsyncTask<Void, Void, Void> {

        Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(home.this);
            pd.setMessage("Tunggu sebentar ...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            String jsonstring = hh.panggil(url);

            Log.e(TAG, "Response from url : " + jsonstring);

            if (jsonstring != null) {
                try {

                    JSONArray hotel = new JSONArray(jsonstring);

                    for (int i = 0; i < hotel.length(); i++) {
                        JSONObject h = hotel.getJSONObject(i);

                        String id = h.getString("id");
                        String name = h.getString("name");
                        String address = h.getString("address");
                        String phone_number = h.getString("phone_number");
                        String price_per_night = Integer.toString(h.getInt("price_per_night"));
                        String LAT = h.getString("LAT");
                        String LNG = h.getString("LNG");
                        String image = h.getString("image");

                        dataHotelList.add(new dataHotel(id, name, address, phone_number, price_per_night, LAT, LNG, image));

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error : " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json error" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "Couldnt get json from server");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Json error", Toast.LENGTH_LONG).show();
                    }
                });

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void hasil) {
            super.onPostExecute(hasil);

            if (pd.isShowing())
                pd.dismiss();
            Lv();
        }
    }

    void Lv(){
        ListView listView = findViewById(R.id.listView);

        adaptermenu adapter = new adaptermenu(this, R.layout.home_layout,dataHotelList);
        listView.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < dataHotelList.size(); i++) {
                    if (position == i) {
                        Intent intent = new Intent(getApplicationContext(), detailHotel.class);

                        dataHotel dh = dataHotelList.get(i);

                        intent.putExtra("name", dh.getname());
                        intent.putExtra("address", dh.getaddress());
                        intent.putExtra("notel", dh.getnotel());
                        intent.putExtra("price", dh.getprice());
                        intent.putExtra("LAT", dh.getlat());
                        intent.putExtra("LNG", dh.getlng());
                        intent.putExtra("image", dh.getgambar());

                        startActivity(intent);
                    }
                }
            }
        });
    }


    class adaptermenu extends ArrayAdapter<dataHotel> {

        List<dataHotel> dataHotelList;

        Context c;

        int res;

        public adaptermenu(Context c, int res, List<dataHotel> dataHotelList) {
            super(c, res, dataHotelList);
            this.c = c;
            this.res = res;
            this.dataHotelList = dataHotelList;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(c);
            View view = layoutInflater.inflate(res, null, false);

            TextView txnama = view.findViewById(R.id.nama);
            TextView txalamat = view.findViewById(R.id.alamat);
            TextView txharga = view.findViewById(R.id.harga);
            ImageView gambare = view.findViewById(R.id.gambar);

            dataHotel dh = dataHotelList.get(position);

            txnama.setText(dh.getname());
            txalamat.setText(dh.getaddress());
            txharga.setText(dh.getprice());

            Picasso.get().load(dh.getgambar()).into(gambare);

            return view;
        }

    }
}


