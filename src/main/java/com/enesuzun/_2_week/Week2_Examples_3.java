package com.enesuzun._2_week;
/*Kullanıcıdan alınan int degerin faktoriyelini hesaplama itterative ve recursive yoşlla*/


import com.google.protobuf.DescriptorProtos;

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
        int sayi,fak=1,Hak=0;

        while (true) {
            System.out.println("Lütfen sayi giriniz");
            if (scanner.hasNextInt()){
                sayi = scanner.nextInt();
                if(sayi<0){
                    System.out.println("güle güle");
                    break;              //1. yol sadece döngü ya da switch bloğunun etkiler
                    //System.exit(0);//2.yol Jvm i kapatarak tamamen çıkış sağlar
                }
                fak = fakHesapla(sayi);
                System.out.printf("%d sayısının faktoriyal sonucu : %d", sayi, fak);
                System.out.println("");
            } else if(scanner.hasNextDouble()){//Bende bu çalışmıyor
                //double rastgele=scanner.nextDouble();
                Hak++;
                System.out.println("ondalıklı sayi girdiniz.Kalan hak"+(3-Hak));
                if(Hak==3){
                    System.exit(0);
                }
            } else {
                System.out.println("harf girdiniz");
                break;
            }


/*
        //iterative yolla
        for (int i=1;i<=sayi;i++){
            fak=fak*i;
        }
        System.out.printf("Sonuc : %d",fak);
*/

        }
    }
}
