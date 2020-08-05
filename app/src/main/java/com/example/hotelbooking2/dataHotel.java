package com.example.hotelbooking2;

public class dataHotel {

    String id,name,address,notel,price,lat,lng,gambar;

    public dataHotel(String id,String name,String address,String notel,String price,String lat,String lng,String gambar){
        this.id=id;
        this.name=name;
        this.address = address;
        this.notel = notel;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
        this.gambar = gambar;
    }

    public void setid(String id){
        this.id = id;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setnotel(String notel){
        this.notel = notel;
    }

    public void setprice(String price){
        this.price = price;
    }

    public void setlat(String lat){
        this.lat = lat;
    }

    public void setlng(String lng){
        this.lng = lng;
    }

    public void setgambar(String gambarm){
        this.gambar = gambar;
    }

    public String getid(){ return id; }

    public String getname(){
        return name;
    }

    public String getaddress(){
        return address;
    }

    public String getnotel(){
        return notel;
    }

    public String getprice(){
        return price;
    }

    public String getlat(){
        return lat;
    }

    public String getlng(){
        return lng;
    }

    public String getgambar() {return gambar;}


}
