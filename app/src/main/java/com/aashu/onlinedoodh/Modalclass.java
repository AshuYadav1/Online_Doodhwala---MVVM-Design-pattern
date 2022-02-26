package com.aashu.onlinedoodh;

public class Modalclass {

    String Mobilenumber,name,shopname,surl;

    Modalclass()
    {

    }


    public Modalclass(String mobilenumber, String name, String shopname, String surl) {
        Mobilenumber = mobilenumber;
        this.name = name;
        this.shopname = shopname;
        this.surl = surl;
    }

    public String getMobilenumber() {
        return Mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        Mobilenumber = mobilenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
