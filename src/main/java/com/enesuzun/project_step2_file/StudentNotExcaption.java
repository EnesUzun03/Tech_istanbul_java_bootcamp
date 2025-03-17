package com.enesuzun.project_step2_file;

//Ogrenci bulunmazsa fırlayacak ozel excaption
public class StudentNotExcaption extends RuntimeException {
    public StudentNotExcaption(String message) {
        super(message);
    }
}
