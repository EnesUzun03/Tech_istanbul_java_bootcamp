package com.enesuzun.tutorials._6_week;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//class
class Student {
    //fields
    private String name;
    private int age;

    //Constructer
    public Student() {}

    //prametreli constructer
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //method
    public void study() {
        System.out.println(name + " çalışıyor...");
    }
}//end class

/// ///////////////////////////////////////////////
/// Reflection

public class Week6_03_Reflection {
    //PSVM
    public static void main(String[] args) throws ClassNotFoundException {
        // 1. Yöntem: Class.forName()
        Class<?> studentClass = Class.forName("Student");//class çekerken adını vererek çekiyor

        // 2. Yöntem: .class kullanımı
        Class<?> studentClass2 = Student.class;

        // 3. Yöntem: getClass()
        Student student = new Student();
        Class<?> studentClass3 = student.getClass();

        // Sınıf adını yazdır
        System.out.println("Sınıf Adı: " + studentClass.getName());

        // Constructor bilgilerini al
        Constructor<?>[] constructors = studentClass3.getConstructors();
        System.out.println("\n### Constructor Listesi:");
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        // Metotları listele
        Method[] methods = studentClass3.getDeclaredMethods();
        System.out.println("\n### Metot Listesi:");
        for (Method method : methods) {
            System.out.println(method);
        }

        // Değişkenleri listele
        Field[] fields = studentClass3.getDeclaredFields();
        System.out.println("\n### Değişken Listesi:");
        for (Field field : fields) {
            System.out.println(field);
        }
    }
}