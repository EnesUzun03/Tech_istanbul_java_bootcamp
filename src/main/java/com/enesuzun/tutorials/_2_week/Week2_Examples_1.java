package com.enesuzun.tutorials._2_week;

import java.util.Scanner;

public class Week2_Examples_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sayi = 0;
        while (true) {
            System.out.println("Lütfen bir sayi giriniz");
            sayi = scanner.nextInt();

            int kalan = sayi % 2;
            if (kalan == 0) {
                System.out.println("Çift");
            } else {
                System.out.println("Tek");
            }

        }

    }
}
