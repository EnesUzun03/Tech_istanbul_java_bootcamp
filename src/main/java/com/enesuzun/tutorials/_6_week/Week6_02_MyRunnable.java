package com.enesuzun.tutorials._6_week;

//interface java 8 ile beraber interface de gövdeli metotda bulunmakta

public class Week6_02_MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "-Deger :" + (i) );
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Thread kesintiye ugradı");
            }
        }
    }
}

class RunnableExample {
    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(new Week6_02_MyRunnable());
        Thread thread2 = new Thread(new Week6_02_MyRunnable());
        Thread thread3 = new Thread(new Week6_02_MyRunnable());

        thread1.start();
        thread2.start();
        thread2.join();//Üsteki threadler bitmeden alttakiler başlamaz
        thread3.start();
    }
}