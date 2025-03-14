package com.enesuzun._2_week;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class _15_3_LocalDateTime {
    //yeni nesil date yapısı
    //LocalDateTime 1
    public static void localDateFormat(){
        LocalDateTime now=LocalDateTime.now();
        Locale locale=new Locale("tr","TR");
        //DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH-mm-ss");
        //normal çıktı
        System.out.println("Şu anda zaman : "+now);
        //bizim formatımız
        System.out.println("Şu anda zaman : "+now.format(dateTimeFormatter));
    }
    //LocalDateTime 2
    public static void localDateFormat2(){
        LocalDateTime now=LocalDateTime.now();
        Locale locale=new Locale("tr","TR");
        //DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH-mm-ss");
        //normal çıktı
        System.out.println("Şu anda zaman : "+now);
        //bizim formatımız
        System.out.println("Şu anda zaman : "+now.format(dateTimeFormatter));
    }




    public static void main(String[] args) {
        localDateFormat();
    }
}
