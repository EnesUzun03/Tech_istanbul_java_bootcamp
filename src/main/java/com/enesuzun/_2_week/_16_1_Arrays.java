package com.enesuzun._2_week;

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

    public static void arrayMethod2(){
        String [] city=arrayMethod();
        //for each yapısı
        for(String temp:city){
            System.out.println(_15_4_SpecialColor.BLUE+temp+_15_4_SpecialColor.RESET);
        }
    }

    public static void main(String[] args) {
        //arrayMethod();
        arrayMethod2();
    }
}
