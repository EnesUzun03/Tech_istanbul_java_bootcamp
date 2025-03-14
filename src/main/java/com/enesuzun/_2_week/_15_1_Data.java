package com.enesuzun._2_week;

import javax.xml.crypto.Data;
import java.util.Date;

public class _15_1_Data {
    public static void dateMetot(){
        Date now=new Date();
        System.out.println("Simdiki zaman :"+now);
        System.out.println("1 ocak 1970'den günümüze geçen sürenin milisaniye cinsinden degeri"+now.getTime());
        System.out.println("Date :"+now.getDate());
        System.out.println("day"+now.getDay());
        System.out.println("Ay"+now.getMonth());
        System.out.println("Year"+(now.getYear()+1900));//Yılı 1900 den saymaya başlıyor
        System.out.println("saat"+now.getHours());
        System.out.println("dk"+now.getMinutes());
        System.out.println("Secpnds"+now.getSeconds());
    }
    public static String nowFormat() throws NullPointerException{
        Date now=new Date();
        String specialFormat="Zaman".concat(now.getHours())
                .concat(now.getMinutes())
                .concat(now.getSeconds())
                .toString();
        return specialFormat;
    }





    public static void main(String[] args) {
        dateMetot();
    }
}
