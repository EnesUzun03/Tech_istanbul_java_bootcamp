package com.enesuzun.tutorials._5_week;


import java.util.function.Consumer;
/*

Java 8, bazı hazır fonksiyonel arayüzler** de sunar:
Predicate<T>   → `boolean test(T t)` → Koşul kontrolleri için.
Function<T, R> → `R apply(T t)`      → Bir değeri dönüştürmek için.
Consumer<T>    → `void accept(T t)`  → Parametre alır, bir işlem yapar ama geriye değer döndürmez.
Supplier<T>    → `T get()`           → Parametre almaz, bir değer üretir.
*/

class Printer {
    static void printMessage(String message) {
        System.out.println(message);
    }
}

public class Week5_03_MethodReferences {

    //Consumer<T>    → `void accept(T t)`  → Parametre alır, bir işlem yapar ama geriye değer döndürmez.
    public static void main(String[] args) {
        //1. yol(instence le çağrılıyoor
        Printer printer=new Printer();
        printer.printMessage("MERHABA (OLMAYAN METOT REFERANS) ESKİ YÖNTEM!!!");

        //2. yol
        //direkt çağırmak içim kulanılıyor

        Consumer<String> printer2 = Printer::printMessage;
        printer2.accept("Hello, Method Reference!");
    }
}




