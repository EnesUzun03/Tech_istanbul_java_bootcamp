package com.enesuzun._2_week;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class _16_1_Arrays {
    public static String[] arrayMethod() throws ArrayIndexOutOfBoundsException{
        String [] city=new String[6];//String dizisi
        city[0]="İstanbul";
        city[1]="elazıg";
        city[2]="Bingöl";//4 yazmadık
        city[3]="Bartın";
        city[5]="Mus";

        /*System.out.println(city);
        System.out.println(city[0]);
        System.out.println("Eleman sayısı"+city.length);*/
        System.out.println("son eleman" + city[city.length-1]);
        return city;
    }
    public static String[] arrayMethod2() throws ArrayIndexOutOfBoundsException{
        //String [] city={"İstanbul","elazıg",null,"Bingöl","Bartın","Mus"};
        String [] city={"İstanbul","elazıg","Bingöl","Bartın","Mus"};
        //System.out.println("son eleman" + city[city.length-1]);
        return city;
    }
    //iterative for döngüsü
    public static void arrayMethod3(){
        String [] city=arrayMethod2();
        //for each yapısı
        for (int i=0;i<city.length;i++){
            System.out.println(_15_4_SpecialColor.BLUE+city[i]+_15_4_SpecialColor.RESET);
        }
    }
    public static void arrayMethod4(){
        String [] city=arrayMethod2();
        //for each yapısı
        for(String temp:city){
            System.out.println(_15_4_SpecialColor.YELLOW+temp+_15_4_SpecialColor.RESET);
        }
    }

    public static void arrayMethod5(){
        String [] city=arrayMethod2();
        Arrays.sort(city);//Kücükten buyuğe sıaralama
        for(String temp:city){
            System.out.println(_15_4_SpecialColor.YELLOW+temp+_15_4_SpecialColor.RESET);
        }
    }
    //
    public static void arrayMethod6(){
        String [] city=arrayMethod2();
        Arrays.sort(city, Collections.reverseOrder());//Büyükten küçüğe
        for(String temp:city){
            System.out.println(_15_4_SpecialColor.YELLOW+temp+_15_4_SpecialColor.RESET);
        }
    }
    //Random Number
    public static int randomNumber(){
        Random random=new Random();
        int rndInt=random.nextInt(9)+1;
        return rndInt;
    }

    //fill metotu tek bir degeri tum elemanlara atamak için kullanılır
    public static void arrayMethod7(){
        int [] number=new int[7];
        //fill metotu tek bir degeri tum elemanlara atamak için kullanılır
        Arrays.fill(number,randomNumber());//fill doldurmak için kullanılıyor

        //iterative for ile her defasında farklı bir sayı üretsin
        for(int i=0;i<number.length;i++){
            //number[i]=randomNumber();//birinci yol
            Arrays.setAll(number,data ->randomNumber());

        }

        for(int temp : number){
            System.out.println(_15_4_SpecialColor.YELLOW+temp+_15_4_SpecialColor.RESET);
        }
    }



    public static void main(String[] args) {
        //arrayMethod();
        //arrayMethod2();
        //arrayMethod3();
        //arrayMethod4();
        //arrayMethod5();
        //arrayMethod6();
        arrayMethod7();
    }
}
