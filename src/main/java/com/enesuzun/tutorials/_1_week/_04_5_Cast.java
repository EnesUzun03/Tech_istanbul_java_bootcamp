package com.enesuzun.tutorials._1_week;

public class _04_5_Cast {
    public static void main(String[] args) {
        //Widening Cast - İmplicit Cast(Kapalı)
        byte cast1Byte=100;
        int cas1Int=cast1Byte;
        System.out.println(cas1Int);
        //Kücük olan veriyi buyuk olanın içerisine ekledim burada veri kaybı soz konusu deil

        //Narrowing Cast -Explicit Cast(Açık)
        int a=1928391283;
        byte b=(byte) a;
        System.out.println(b);
        //burada bğyğk olanı kucuk olana yerleştiriyoruz bu yuzden veri kaybı vardır

        //Char => Int
        char harf='A';
        int harfInt=harf;
        System.out.println(harfInt);

        // 4-) String => int
        String cast4String="10";
        int cast4Int1=Integer.valueOf(cast4String);
        int cast4Int2=Integer.parseInt(cast4String);
        System.out.println(cast4String+20);
        System.out.println(cast4Int1+20);
        System.out.println(cast4Int2+20);

        // 5-) int => String
        int cast5Int=55;
        String cast5String1=String.valueOf(cast5Int);
        System.out.println(cast5String1);

    }
}
