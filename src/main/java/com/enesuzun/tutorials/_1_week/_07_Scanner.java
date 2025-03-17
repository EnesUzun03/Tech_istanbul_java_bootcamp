package com.enesuzun.tutorials._1_week;

public class _07_Scanner {

    public static void main(String[] args) {
        //Hızlı işlme yapmak için Math kütüphanesini kullan
        System.out.println("En kücük : " + Math.min(3,6));
        System.out.println("Mutlak : " + Math.abs(-7));//mutlak
        System.out.println("KaraKök : " + Math.sqrt(25));//mutlak
        System.out.println("KaraKök : " + Math.sqrt(-25));//Nan Not A number
        System.out.println("KaraKök : " + Math.sqrt(Math.abs(-25)));
        System.out.println("Uslu sayı"+ Math.pow(2,5));//üs
        System.out.println("alta yuvarla "+ Math.floor(2.9));
        System.out.println("Yukarı yuvarla "+ Math.ceil(2.1));
        System.out.println("Yuvarla "+ Math.round(2.5));//2.x x>=5 ise yukarı
        System.out.println("yuvarla "+ Math.round(2.4));

        //trigo


    }
}