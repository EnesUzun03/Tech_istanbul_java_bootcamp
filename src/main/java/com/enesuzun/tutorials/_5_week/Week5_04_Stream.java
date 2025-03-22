package com.enesuzun.tutorials._5_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week5_04_Stream {

    //Normal list ekleme
    public static void streamExam1 () {
        List<String> names = new ArrayList<>();
        names.add("Ali");
        names.add("veli");
        names.add("Ayse");
        names.add("Fatma");

        for(String name : names) {
            if (name.startsWith("A")) {
                System.out.println(name);
            }
        }
    }
    public static void streamExam2(){
        //### **Java 8 ile (Stream API Kullanımı)**
        //Arrays.asList diziyi listeye çevirme
        List<String> names2 = Arrays.asList("Ali", "Veli", "Ayşe", "Fatma");
        for(String name : names2) {
            if (name.startsWith("A")) {
                System.out.println(name);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("ilk  yöntem\n");
        streamExam1();

        System.out.println("2.  yöntem\n");
        streamExam2();

    }

}
