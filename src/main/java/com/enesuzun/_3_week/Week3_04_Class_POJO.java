package com.enesuzun._3_week;


//POJO : plain old java object
//sadece fields + getter setter

import com.enesuzun.utils.SpecialColor;

public class Week3_04_Class_POJO {
    //field
    private String name;
    private String surname;

    //Constructor(paramtresiz)
    //Constructor(paramtreli)
    //method
    //getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;//this global nesneyi belirtir
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname.toUpperCase();
    }

    public static void main(String[] args) {
        Week3_04_Class_POJO pojo=new Week3_04_Class_POJO();
        pojo.setName("Enes");
        pojo.setSurname("Uzun");

        String nameAndS1urname=pojo.getName().toString()+" "+pojo.getSurname().toString();

        System.out.println(SpecialColor.BLUE+nameAndS1urname+SpecialColor.RESET);
    }

}
