package com.enesuzun._2_week;

import java.util.Formatter;

public class _12_1_StringFormat {
    //formatter1
    public static void formatter1(){
        Formatter formatter=new Formatter();
        formatter.format("Merhaba %s Numaran %d TC : %d Fiyat %f","enes",216,1145668997,99.99);
        System.out.println(formatter);
        formatter.close();//belleği serbest bırak
        /* Garbarage collectors
        * Yalnızca ama yalnızca kapalı olamayan dosya (scanner ,formatter gibi) Eğer biz kapatmazsak bu kap
        * kapanmayan dosyayı temizlemeyebilir ancak açık olan dosyayı kapatmazsak cache belleği kullanmaya devam eder
        */
    }

    //formatter 2
    public static void formatter2(){
        //Formatter formatter=new Formatter();
        String formatterString=String.format("Merhaba %s Numaran %d TC : %d Fiyat %f","enes",216,1145668997,99.99);
        System.out.println(formatterString);
    }
    //formatter 3
    public static void formatter3(){

    }

    public static void main(String[] args) {
        //formatter1();
        formatter2();
    }


}
