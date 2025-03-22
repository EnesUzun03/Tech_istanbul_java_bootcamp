package com.enesuzun.tutorials._5_week;

/*

Java 8, bazı hazır fonksiyonel arayüzler** de sunar:
Predicate<T>   → `boolean test(T t)` → Koşul kontrolleri için.
Function<T, R> → `R apply(T t)`      → Bir değeri dönüştürmek için.
Consumer<T>    → `void accept(T t)`  → Parametre alır, bir işlem yapar ama geriye değer döndürmez.
Supplier<T>    → `T get()`           → Parametre almaz, bir değer üretir.
*/

import java.util.Optional;

public class Week5_05_Optional {
    //Birinci problem çözümü
    public String isNotvalidation(String data) {
        return data;
    }

    //2.problem çözümü
    public String validation(String data) {
        if (data.isEmpty() || data== null) {
            return "Unknown";
        } else{
            return data;
        }
    }
    //3. problem çözümü

    public Optional<String> OptionalResault(String data) {
        Optional<String> name = Optional.ofNullable(null);
        return name;
    }


    public static void main(String[] args) {
        //Instance
        Week5_05_Optional week5_05_optional=new Week5_05_Optional();

        //1.Yol
        String resault= week5_05_optional.isNotvalidation("");
        System.out.println(resault);

        //2.yol
        //String resault= week5_05_optional.validation("");
        //System.out.println(resault);

        //3.yol
        Optional<String> resault3= week5_05_optional.OptionalResault("");
        System.out.println(resault3);
    }
}













