package com.enesuzun.tutorials._5_week;

@FunctionalInterface
interface MyFynctionalInterface{
    void showMessage(String message);
}

public class Week5_02_Lambda {
    public static void main(String[] args) {
        MyFynctionalInterface messagePrinter=(message)-> System.out.println("Mesaj :" + message);
        messagePrinter.showMessage("Merhaba lambda");
    }
}
