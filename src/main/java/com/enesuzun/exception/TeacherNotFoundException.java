package com.enesuzun.exception;

public class TeacherNotFoundException extends RuntimeException {

    // Parametresiz Constructor
    public TeacherNotFoundException() {
        super("Kayıt bulunamadı");
    }

    // Parametreli Constructor
    public TeacherNotFoundException(String message) {
        super(message);
    }
}

/*
public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
*/
