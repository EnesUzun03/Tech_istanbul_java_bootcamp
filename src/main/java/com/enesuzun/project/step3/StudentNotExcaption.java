package com.enesuzun.project.step3;

//Ogrenci bulunmazsa fırlayacak ozel excaption
public class StudentNotExcaption extends RuntimeException {
    public StudentNotExcaption(String message) {
        super(message);
    }
}
