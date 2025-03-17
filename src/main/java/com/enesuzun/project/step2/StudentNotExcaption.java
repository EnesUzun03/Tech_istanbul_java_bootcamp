package com.enesuzun.project.step2;

//Ogrenci bulunmazsa fırlayacak ozel excaption
public class StudentNotExcaption extends RuntimeException {
    public StudentNotExcaption(String message) {
        super(message);
    }
}
