package com.enesuzun._2_week;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class _15_1_Date {
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
        String specialFormat="Zaman  "
                .concat(String.valueOf(now.getHours()))//String.valueOf(now.getHours()) içerisinde yazılmazsa hata verir
                .concat(".")
                .concat(String.valueOf(now.getMinutes()))
                .concat(".")
                .concat(String.valueOf(now.getSeconds()))
                .toString();
        return specialFormat;
    }
    public static String nowFormat2() throws NullPointerException{
        Date now=new Date();
        //%s string
        //%d decimal
        //%f float
        return String.format("Zaman :%02d:%02d:%02d",now.getHours(),now.getMinutes(), now.getSeconds());
    }


    public static String nowFormat3() throws NullPointerException{
        Date now=new Date();
        Locale locale=new Locale("tr","TR");
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//sdf de turkey yok bu yüzden ben ekliyorum
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss",locale);
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMMM-dd HH-mm-ss",locale);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMMM-dd HH-mm-ss  zzzz",locale);
        String formatedDate=String.format("Şimdiki zaman : %s",sdf.format(now));
        return new Date().toString()+" "+formatedDate ;
    }




    public static void main(String[] args) {
        /*String nowDate=nowFormat();
        System.out.println(nowDate);*/

        /*String nowDate2=nowFormat2();
        System.out.println(nowDate2);*/

        String nowDate3=nowFormat3();
        System.out.println(nowDate3);
    }
}
