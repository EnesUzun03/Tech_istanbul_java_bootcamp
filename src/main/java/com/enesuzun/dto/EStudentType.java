package com.enesuzun.dto;



/**
 * 📌 Öğrenci Türleri Enum
 */
public enum EStudentType {
    UNDERGRADUATE("Lisans"),
    GRADUATE("Yüksek Lisans"),
    PHD("Doktora"),
    OTHER("Diğer");

    // Field
    private final String description;

    // Parametresiz Constructor
    EStudentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}





//kendi yazdığım
/*
//Ogrenci türleri   Lisans , yükseklisans , doktora
public enum EStudentType {
    UNDERGRADUATE,
    GRADUATE ,
    PHD,
    OTHER
}
*/
