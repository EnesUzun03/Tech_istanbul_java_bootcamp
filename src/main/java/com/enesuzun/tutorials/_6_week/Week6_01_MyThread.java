package com.enesuzun.tutorials._6_week;

//extends Thread
public class Week6_01_MyThread extends Thread {
    public void run() {
        for (int i = 1; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "-Deger :" + i);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Thread kesintiye ugradı");
            }
        }
    }
}
//extends Thread main
class Main {
    public static void main(String[] args) {
        Week6_01_MyThread thread1 = new Week6_01_MyThread();
        Week6_01_MyThread thread2 = new Week6_01_MyThread();

        thread1.start();
        thread2.start();
    }
}
