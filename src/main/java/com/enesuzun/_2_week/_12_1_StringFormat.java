package com.enesuzun._2_week;

import java.util.Formatter;

public class _12_1_StringFormat {
    //formatter1
    public static void formatter1(){
        Formatter formatter=new Formatter();
        formatter.format("Merhaba %s Numaran %d TC : %d Fiyat %f","enes",216,1145668997,99.99);
        System.out.println(formatter);
    }

    public static void main(String[] args) {
        formatter1();
    }
}
