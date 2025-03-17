package com.enesuzun.tutorials._2_week;

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
    public static void localDateFormatSetMethod2() {
        Locale locale = new Locale("tr", "TR");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss", locale);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newData= now
                .withDayOfMonth(11) //
                .withMonth(12)  //
                .withYear(2029)
                .withHour(14)
                .withMinute(44)
                .withSecond(23);
        //System.out.println(now);
        System.out.println("Normal Tarih: "+now);
        System.out.println("Değiştirilmiş Tarih: "+newData.format(formatter));
    }
    public static void main(String[] args) {
        //localDateFormat();
        localDateFormatSetMethod2();
    }
}
