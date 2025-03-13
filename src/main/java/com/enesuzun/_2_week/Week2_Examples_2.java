package com.enesuzun._2_week;

import java.util.Scanner;

public class Week2_Examples_2 {
    //kullanıcının verdiği sayiya kadar toplama işlemi yapan algoritma
    //şartlar
    //sayi>100 ise 100'e kadar topla
    //Kullanıcı verdiği sayılar içinde eğer 47 varsa bunu toplamaya dahil etmesin.
    //sonuç tek mi çift mi?
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int toplam=0,sayi,i=0;
        System.out.println("Lütfen sayi giriniz");
        sayi=scanner.nextInt();
        if(sayi>100){
            sayi=100;
        }
        while (i<=sayi){
            if(i==47){
                i++;
                continue;
            }
            toplam=toplam+i;
            i++;
        }
        if (toplam%2==0){
            System.out.println("çift");
        }else{
            System.out.println("tek10");
        }
        System.out.println("Toplam "+toplam);
        scanner.close();
    }

}
