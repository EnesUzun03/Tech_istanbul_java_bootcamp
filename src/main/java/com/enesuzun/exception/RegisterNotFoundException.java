package com.enesuzun.exception;

public class RegisterNotFoundException extends RuntimeException {

    // Parametresiz Constructor
    public RegisterNotFoundException() {
        super("Kayıt bulunamadı");
    }

    public RegisterNotFoundException(String message) {
        super(message);
    }
}
