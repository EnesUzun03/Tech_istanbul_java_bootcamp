package com.enesuzun._2_week;

import java.util.Arrays;

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

    public static void main(String[] args) {
        //arrayMethod();
        //arrayMethod2();
        //arrayMethod3();
        //arrayMethod4();
        arrayMethod5();
    }
}
