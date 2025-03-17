package com.enesuzun.tutorials._1_week;

import java.util.Scanner;
/*
1. 1. Dereceden 1 Bilinmeyenli Denklemi Çözme (Scanner kullanlalım)
      Soru:
      Kullanıcıdan ax + b = 0 denklemindeki a ve b değerlerini alıp x'i hesaplayan programı yazınız.
      Çözüm:
 */
public class week1_examples_1 {
    public static void main(String[] args) {
        //bir bilinmeyenli denklemin çözümü ax+b=0
        //scanner ekliyelim
        Scanner scanner=new Scanner(System.in);

        //degiskenler
        double a_deger,b_deger,sonuc;

        System.out.println("Lütfen a degerini giriniz :");
        a_deger=scanner.nextDouble();

        System.out.println("Lütfen b degerini giriniz :");
        b_deger=scanner.nextDouble();

        sonuc=(-1)*(b_deger/a_deger);
        System.out.println("Sonuc(x degeri) :" + sonuc);

        scanner.close();
    }
}
