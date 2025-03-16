package com.enesuzun.project_step1_file;

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
    private LocalDate birthDay;
    private Date createdDate;
    private Double grade;//basari puani

    //Static () nesne boyunca sadece 1 kere olusturulur
    static {

    }
    //parmetresiz constructor
    public StudentDto() {
    }
    //parametreli constructor

    public StudentDto(Integer id, String name, String surname, LocalDate birthDay, Double grade) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.grade = grade;
    }
    //Metotlar
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

    public Double grade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}//end
