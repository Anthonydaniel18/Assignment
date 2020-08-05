package com.example.hotelbooking2;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler(){

    }

    public String panggil(String urll){
        String response = null;

        try {
            URL url = new URL(urll);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(con.getInputStream());
            response = ubah(in);

        } catch (MalformedURLException e) {
            Log.e(TAG,"MalformedURLException: "+ e.getMessage());
        }catch(ProtocolException e){
            Log.e(TAG,"ProtocolException: "+ e.getMessage());
        } catch (IOException e) {
            Log.e(TAG,"IOException: "+ e.getMessage());
        }catch (Exception e){
            Log.e(TAG,"Exception: "+ e.getMessage());
        }
        return response;
    }

    private String ubah(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;

        try{
            while((line = br.readLine())!=null){
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
