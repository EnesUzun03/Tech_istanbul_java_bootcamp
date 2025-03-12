package com.enesuzun._2_week;

public class _10_1_Loop_For {
    public static void main(String[] args) {
        int i = 1;
        for (; i <= 10; i++) {
            System.out.print(i + " - ");
        }


        // for döngüsü sonsuzluk
        // for(;;){}
        int number = 1;
        for (; ; ) {
            System.out.print(number + " ");
            number++;
            if (number >= 50)
                break;
        }
    }
}
