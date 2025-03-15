package com.enesuzun._1_week;

public class _08_1_System {

    //eger system.exit kullanırsam
    /*
    * "-Jvm durur
    * 2-çalışan tün threadler durur
    * 3-programın işletim sistemindeki processler de durur
    * */
    public static void processCloseJvmStop(){
        System.out.println("program basladı-System.exit(0)");
        System.exit(0);
        System.out.println("Burası çalışmaz");
    }

    public static void processContinueJvmContinue(){
        for (int i=0;i<=10;i++){
            if(i==5){
                System.out.println("sadece döngüden çıkılıyor");
                break;
            }
            System.out.println(i+". sıra");
        }
        System.out.println("bu satır çalışıyor mu?");

    }

    public static void main(String[] args) {
        //processCloseJvmStop();
        processContinueJvmContinue();
    }
}
