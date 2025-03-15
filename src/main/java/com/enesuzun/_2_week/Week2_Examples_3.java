package com.enesuzun._2_week;
/*Kullanıcıdan alınan int degerin faktoriyelini hesaplama itterative ve recursive yoşlla*/


import java.util.Scanner;

public class Week2_Examples_3 {

    public static int fakHesapla(int a){
        if(a==0){
            return 1;
        }
        return a*fakHesapla(a-1);
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int sayi,fak=1;

        while (true) {

            System.out.println("Lütfen sayi giriniz");
            sayi = scanner.nextInt();

            while (sayi < 0) {
                System.out.println("Yanlis sayi girdiniz tekrar deneyin");
                sayi = scanner.nextInt();
            }


/*
        //iterative yolla
        for (int i=1;i<=sayi;i++){
            fak=fak*i;
        }
        System.out.printf("Sonuc : %d",fak);
*/
            fak = fakHesapla(sayi);
            System.out.printf("%d sayısının faktoriyal sonucu : %d", sayi, fak);
            System.out.println("");
        }
    }
}
