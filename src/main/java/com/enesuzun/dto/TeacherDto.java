package com.enesuzun.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * //@param personDto
 * @param subject
 * @param yearsOfExperience
 * @param isTenured
 * @param salary
 * TeacherDto bir Record olarak tanımlanmıştır.
 * Record'lar Javada Immutable(değiştirilemez)  veri taşıma nesneleridir.
 * Inheritance (Desteklemez)  ancak Composition yöntemiyle PersonDto kullanabiliriz
 */

/*
Dikkat:
1-) Record => public record Deneme(PARAMETRELER){}
2-) Constructor public Deneme {}
*/

// Record : TeacherDto
public record TeacherDto(
        Integer id,
        String name,
        String surname,
        LocalDate birthDate,
        ETeacherSubject subject,
        int yearsOfExperience,
        boolean isTenured,
        double salary
) implements Serializable {

    // Logger
    private static final Logger logger = Logger.getLogger(TeacherDto.class.getName());

    public TeacherDto {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("❌ ID negatif olamaz!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("❌ İsim boş olamaz!");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("❌ Soyisim boş olamaz!");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("❌ Doğum tarihi boş olamaz!");
        }
        if (subject == null) {
            throw new IllegalArgumentException("❌ Uzmanlık alanı boş olamaz!");
        }
        if (yearsOfExperience < 0) {
            throw new IllegalArgumentException("❌ Deneyim yılı negatif olamaz!");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("❌ Maaş negatif olamaz!");
        }
    }
    // Method
    public String fullName() {
        return id + " - " + name + " " + surname + " (" + subject + ")";
    }

    public String experienceLevel() {
        if (yearsOfExperience >= 15) {
            return "Kıdemli Öğretmen 🏅";
        } else if (yearsOfExperience >= 5) {
            return "Deneyimli Öğretmen 🎓";
        } else {
            return "Yeni Öğretmen 🆕";
        }
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", subject=" + subject +
                ", yearsOfExperience=" + yearsOfExperience +
                ", isTenured=" + isTenured +
                ", salary=" + salary +
                ", experienceLevel=" + experienceLevel() +
                '}';
    }
}


/*
//kendi yazdığım sınıf

import java.io.Serializable;
import java.time.LocalDate;

//Enum
//Normalde bu tercih edilmiyor fakat boyle de yazabiliriz
//ogretmen bransi
enum EteacherDtoSubject{
    MATHEMATICS,
    CHEMISTRY,
    BIOLOGY,
    HISTORY,
    COMPUTER_SCIENCE

}
*/
/**
 * //@param personDto
 * @param subject
 * @param yearsOfExperience
 * @param isTenured
 * @param salary
 * TeacherDto bir Record olarak tanımlanmıştır.
 * Record'lar Javada Immutable(değiştirilemez)  veri taşıma nesneleridir.
 * Inheritance (Desteklemez)  ancak Composition yöntemiyle PersonDto kullanabiliriz
 *//*

public record TeacherDto (
        //Fields
        //PersonDto personDto,    //(Composition)(ilişkisel yapılarda) PersonDto içindeki ortak alanları kullanır
        Integer id,
        String name,
        String surname,
        LocalDate birthDate,
        //String subject,         //Ogretmenin uzmalık alanı
        EteacherDtoSubject subject,         //Ogretmenin uzmalık alanı
        int yearsOfExperience, //ogretmen deneyim yılı
        boolean isTenured,      //Kadrolu mu değil mi
        double salary          //Maası


) implements Serializable  {
    //Varsayılan Constructerlar ile Veri doğrulama
    public TeacherDto{
        if(personDto==null) throw  new IllegalArgumentException("Teacher nesnesinde " +
                "Person bilgisi boş olamaz");
        if (subject==null || subject.isBlank()) throw new IllegalArgumentException("Ogretmenin uzmanlığını boş geçtiniz");
        if(yearsOfExperience<0) throw new IllegalArgumentException("Dneyim yılınız 0 dan kucuk olunmaz");
        if(salary<0)throw new IllegalArgumentException("Maas negatif olamaz");
    }
    //Method
    public String fullName() {
        return personDto.id+" "+personDto.name+" "+personDto.surname;
    }

    public String experienceLevel() {
        if (yearsOfExperience >= 15) {
            return "Kıdemli Öğretmen 🏅";
        } else if (yearsOfExperience >= 5) {
            return "Deneyimli Öğretmen 🎓";
        } else {
            return "Yeni Öğretmen 🆕";
        }
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", subject=" + subject +
                ", yearsOfExperience=" + yearsOfExperience +
                ", isTenured=" + isTenured +
                ", salary=" + salary +
                ", experienceLevel=" + experienceLevel() +
                '}';
    }

}
*/
