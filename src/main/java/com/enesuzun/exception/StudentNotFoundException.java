package com.enesuzun.exception;

// Öğrenci bulunamazsa Fırlatılacak Özel Excepiton
public class StudentNotFoundException extends RuntimeException {

    // Parametresiz Constructor
    public StudentNotFoundException() {
        super("Kayıt bulunamadı");
    }

    // Parametreli Constructor
    public StudentNotFoundException(String message) {
        super(message);
    }
}

/*//Ogrenci bulunmazsa fırlayacak ozel excaption
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}*/
