package com.enesuzun.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // Çalışma zamanında erişilebilir
//@Target(ElementType.METHOD,ElementType.CONSTRUCTOR) // Metotlara ve Constructerlarda uygulanabilir
@Target({ElementType.METHOD}) // Yalnızca metotlara uygulanabilir


public @interface SpecialAnnotation {


}
class Test {
    private String name;
    //LogExecutionTime //buraya bu annotation eklenemez çünkü sadece metotlarda calışacağını belirttik
    public Test(String name) {
        this.name = name;
    }

    @SpecialAnnotation
    public static void process() {
        System.out.println("Bu metot loglanacak.");
    }

    //PSVM
    public static void main(String[] args) {
        Test.process();
    }
}