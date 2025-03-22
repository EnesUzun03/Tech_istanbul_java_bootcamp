package com.enesuzun.tutorials._5_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*

Java 8, bazı hazır fonksiyonel arayüzler** de sunar:
Predicate<T>   → `boolean test(T t)` → Koşul kontrolleri için.
Function<T, R> → `R apply(T t)`      → Bir değeri dönüştürmek için.
Consumer<T>    → `void accept(T t)`  → Parametre alır, bir işlem yapar ama geriye değer döndürmez.
Supplier<T>    → `T get()`           → Parametre almaz, bir değer üretir.
*/
public class Week5_04_Stream {

    //Normal list ekleme
    public static List<String> streamExam1 () {
        List<String> names = new ArrayList<>();
        names.add("Ali");
        names.add("AHmet");
        names.add("Asım");
        names.add("veli");
        names.add("Ayse");
        names.add("Ayfer");
        names.add("aslı");
        names.add("Fatma");

        for(String name : names) {
            if (name.startsWith("A")) {
                System.out.println(name);
            }
        }
        System.out.println();
        return names;
    }
    public static void streamExam2(){
        //Arrays.asList diziyi listeye çevirme
        List<String> names2 = Arrays.asList("Ali","AHmet","Asım", "Veli", "Ayşe","Ayfer","aslı", "Fatma");
        for(String name : names2) {
            if (name.startsWith("A")) {
                System.out.println(name);
            }
        }
    }

    //### **Java 8 ile (Stream API Kullanımı)**
    public static void streamExam3(){
        //dizi arrayliste dönüştürüldü
        //Filter yapısı içerisinde lambda expression yapılarımızı sı kullanırız
        List<String> names3 = streamExam1().stream().filter(abc->abc.startsWith("A"))
                .collect(Collectors.toList());
        names3.forEach(System.out::print);

    }

    public static void main(String[] args) {
        System.out.println("ilk  yöntem\n");
        streamExam1();

        System.out.println("2.  yöntem\n");
        streamExam2();

        System.out.println("3.  yöntem\n");
        streamExam3();

    }

}
