package com.enesuzun.project_step1_file;

//Öğrenci yönetim sistemi

import java.util.ArrayList;

public class Week3_07_Class_Examples_StudentInformationSystem {
    //Ogerncileri eklemek için kullanacağız
    //Field
    //dizi ile nerdeyse aynı sadece boyutu belli degil
    //Daha güvenilir
    private ArrayList<StudentDto> students=new ArrayList<>();//sadece wrapper türleri destekler
    private int studentCounter =0;
    private static final String FILE_NAME="student.txt";

    //static
    static {

    }

    //parametresiz constructer
    public Week3_07_Class_Examples_StudentInformationSystem() {
        //program başlarken ogrenci listesini yüklesin
        loadStudentListFromFile();
    }

    //ogrenci listesini yükle
    private void loadStudentListFromFile() {

    }
    //Ogrenci Ekle

    //ogrenci Listesi
    //Ogrenci güncelle
    //Ogrenci sil
    //Ogrenci ara

    /////////////////////////////////////////////////

    //FileIO creat

    //ogrenci dosyalarını kaydeten metot
    //ogrenci dosyalarını Okuyan metot

    /////////////////////////////////////////////////

    //Toplam ogranci sayısı
    //ogrenci not ortalamasını
    //En yüksek not alan ogrenci
    //ogrenci siralaması(Dogum gunu)
    //Console Seçim (Ogrenci ekle)

}















