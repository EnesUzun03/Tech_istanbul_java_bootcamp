package com.enesuzun.dto;

import com.enesuzun.utils.SpecialColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//LOMBOK
@AllArgsConstructor
@Builder
@ToString

public class StudentDto implements Serializable {
    //Serileştirme için UID veriyoruz
    public static final Long serialVersionUID=1L;
    //Field
    private Integer id;
    private String name;
    private String surname;
    private EStudentType eStudentType;  //Enum Ogrenci Cinsi
    private Double midTerm;   //Vize notu
    private Double finalTerm; //final notu
    private Double resaultTerm;//vize %40 final  %60 Bu sınav sonucu parametre olarak yollanmayacak

    private LocalDate birthDay;//Dogum gunu
    private Date createdDate;//Sistem otomatik tarihi


    //Static () nesne boyunca sadece 1 kere olusturulur
    //Static Bir classın olusturma zamanında sadece 1 kez oluyor

    static {
        System.out.println(SpecialColor.BLUE+"Sataic StudentDto yuklendi"+SpecialColor.RESET);

    }
    //parmetresiz constructor
    public StudentDto() {
    }
    //parametreli constructor

    public StudentDto(Integer id, String name, String surname, Double midTerm, Double finalTerm, LocalDate birthDay, EStudentType estudentType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.midTerm = midTerm;
        this.finalTerm = finalTerm;
        this.birthDay = birthDay;
        this.createdDate=new Date(System.currentTimeMillis());
        this.resaultTerm=calculateResault();
        this.eStudentType=estudentType;

        }

    //Metotlar
    private Double calculateResault() {
        if(midTerm==null || finalTerm==null){
            return 0.0;
        }else{
            return (0.4 * midTerm ) + ( 0.6 * finalTerm );
        }
    }

    //getter setter
    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String surname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate birthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Date createdDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double midTerm() {
        return midTerm;
    }

    public void setMidTerm(Double midTerm) {
        this.midTerm = midTerm;
        this.resaultTerm=calculateResault();

    }

    public Double finalTerm() {
        return finalTerm;
    }

    public void setFinalTerm(Double finalTerm) {
        this.finalTerm = finalTerm;
        this.resaultTerm=calculateResault();
    }

    public Double resaultTerm() {
        return resaultTerm;
    }

    public void setResaultTerm(Double resaultTerm) {
        this.resaultTerm = resaultTerm;
    }

    public EStudentType eStudentType() {
        return eStudentType;
    }

    public void seteStudentType(EStudentType eStudentType) {
        this.eStudentType = eStudentType;
    }
}//end
