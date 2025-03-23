package com.enesuzun.dto;

import com.enesuzun.utils.ERole;
import com.enesuzun.utils.SpecialColor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Logger;

public class StudentDto extends PersonDto implements Serializable {

    // Serileştirme
    private static final long serialVersionUID = 556364655645656546L;

    // Logger
    private static final Logger logger = Logger.getLogger(StudentDto.class.getName());


    private EStudentType eStudentType;
    private ERole eRole;
    private Double midTerm;
    private Double finalTerm;
    private Double resultTerm;
    private String status;

    // static (Nesne boyunca 1 kere oluşturulur)
    static {
        System.out.println(SpecialColor.BLUE + "✅ static StudentDto Yüklendi" + SpecialColor.RESET);
    }

    // Parametresiz Constructor
    public StudentDto() {
        super();
        this.eStudentType = EStudentType.OTHER;
        this.eRole = ERole.STUDENT;
        this.midTerm = 0.0;
        this.finalTerm = 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    public StudentDto(Integer id, String name, String surname, LocalDate birthDate,
                      Double midTerm, Double finalTerm, EStudentType eStudentType, ERole eRole) {
        super(id, name, surname, birthDate);
        this.eStudentType = (eStudentType != null) ? eStudentType : EStudentType.OTHER;
        this.eRole = (eRole != null) ? eRole : ERole.STUDENT;
        this.midTerm = (midTerm != null) ? midTerm : 0.0;
        this.finalTerm = (finalTerm != null) ? finalTerm : 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    // Parametreli Constructor
    public StudentDto(Integer id, String name, String surname, LocalDate birthDate, EStudentType eStudentType, ERole eRole) {
        this(id, name, surname, birthDate, 0.0, 0.0, eStudentType, eRole);
    }

    private Double calculateResult() {
        if (midTerm == null || finalTerm == null) {
            logger.warning("⚠️ Not hesaplama hatası: Vize veya Final null değer içeriyor!");
            return 0.0;
        }
        return (midTerm * 0.4) + (finalTerm * 0.6);
    }

    // **📌 Status: Geçme / Kalma**
    private String determineStatus() {
        //return (this.resultTerm >= 50.0) ? "Geçti ✅" : "Kaldı ❌";
        return (this.resultTerm >= 50.0) ? "Geçti" : "Kaldı";
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", eStudentType=" + eStudentType +
                ", eRole=" + eRole +
                ", midTerm=" + midTerm +
                ", finalTerm=" + finalTerm +
                ", resultTerm=" + resultTerm +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        logger.info(this.toString());
    }

    // Getter ve Setter Metotları
    public EStudentType getEStudentType() {
        return eStudentType;
    }

    public void setEStudentType(EStudentType eStudentType) {
        this.eStudentType = (eStudentType != null) ? eStudentType : EStudentType.OTHER;
    }

    public ERole getERole() {
        return eRole;
    }

    public void setERole(ERole eRole) {
        this.eRole = (eRole != null) ? eRole : ERole.STUDENT;
    }

    public Double getMidTerm() {
        return midTerm;
    }

    public void setMidTerm(Double midTerm) {
        this.midTerm = (midTerm != null) ? midTerm : 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    public Double getFinalTerm() {
        return finalTerm;
    }

    // Metotlar
    // Vize ve Final Calculate
    // **📌 Sonuç Notu Hesaplama (Vize %40 + Final %60)**
    public void setFinalTerm(Double finalTerm) {
        this.finalTerm = (finalTerm != null) ? finalTerm : 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    public Double getResultTerm() {
        return resultTerm;
    }

    public String getStatus() {
        return status;
    }
}



/*
//kendi yazdığım sınıf

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




public class StudentDto extends PersonDto implements Serializable {

    // Serileştirme
    private static final long serialVersionUID = 556364655645656546L;

    // Logger
    private static final Logger logger = Logger.getLogger(StudentDto.class.getName());


    private EStudentType eStudentType;
    private ERole eRole;
    private Double midTerm;
    private Double finalTerm;
    private Double resultTerm;
    private String status;

    // static (Nesne boyunca 1 kere oluşturulur)
    static {
        System.out.println(SpecialColor.BLUE + "✅ static StudentDto Yüklendi" + SpecialColor.RESET);
    }

    // Parametresiz Constructor
    public StudentDto() {
        super();
        this.eStudentType = EStudentType.OTHER;
        this.eRole = ERole.STUDENT;
        this.midTerm = 0.0;
        this.finalTerm = 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    public StudentDto(Integer id, String name, String surname, LocalDate birthDate,
                      Double midTerm, Double finalTerm, EStudentType eStudentType, ERole eRole) {
        super(id, name, surname, birthDate);
        this.eStudentType = (eStudentType != null) ? eStudentType : EStudentType.OTHER;
        this.eRole = (eRole != null) ? eRole : ERole.STUDENT;
        this.midTerm = (midTerm != null) ? midTerm : 0.0;
        this.finalTerm = (finalTerm != null) ? finalTerm : 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    // Parametreli Constructor
    public StudentDto(Integer id, String name, String surname, LocalDate birthDate, EStudentType eStudentType, ERole eRole) {
        this(id, name, surname, birthDate, 0.0, 0.0, eStudentType, eRole);
    }

    private Double calculateResult() {
        if (midTerm == null || finalTerm == null) {
            logger.warning("⚠️ Not hesaplama hatası: Vize veya Final null değer içeriyor!");
            return 0.0;
        }
        return (midTerm * 0.4) + (finalTerm * 0.6);
    }

    // **📌 Status: Geçme / Kalma**
    private String determineStatus() {
        //return (this.resultTerm >= 50.0) ? "Geçti ✅" : "Kaldı ❌";
        return (this.resultTerm >= 50.0) ? "Geçti" : "Kaldı";
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", eStudentType=" + eStudentType +
                ", eRole=" + eRole +
                ", midTerm=" + midTerm +
                ", finalTerm=" + finalTerm +
                ", resultTerm=" + resultTerm +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        logger.info(this.toString());
    }

    // Getter ve Setter Metotları
    public EStudentType getEStudentType() {
        return eStudentType;
    }

    public void setEStudentType(EStudentType eStudentType) {
        this.eStudentType = (eStudentType != null) ? eStudentType : EStudentType.OTHER;
    }

    public ERole getERole() {
        return eRole;
    }

    public void setERole(ERole eRole) {
        this.eRole = (eRole != null) ? eRole : ERole.STUDENT;
    }

    public Double getMidTerm() {
        return midTerm;
    }

    public void setMidTerm(Double midTerm) {
        this.midTerm = (midTerm != null) ? midTerm : 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    public Double getFinalTerm() {
        return finalTerm;
    }

    // Metotlar
    // Vize ve Final Calculate
    // **📌 Sonuç Notu Hesaplama (Vize %40 + Final %60)**
    public void setFinalTerm(Double finalTerm) {
        this.finalTerm = (finalTerm != null) ? finalTerm : 0.0;
        this.resultTerm = calculateResult();
        this.status = determineStatus();
    }

    public Double getResultTerm() {
        return resultTerm;
    }

    public String getStatus() {
        return status;
    }
}



*/
/*
//Kendi yazdığım kısım

public class StudentDto extends PersonDto implements Serializable {
    //Serileştirme için UID veriyoruz
    public static final Long serialVersionUID=1L;
    //Field

    private EStudentType eStudentType;  //Enum Ogrenci Cinsi
    private Double midTerm;   //Vize notu
    private Double finalTerm; //final notu
    private Double resaultTerm;//vize %40 final  %60 Bu sınav sonucu parametre olarak yollanmayacak





    //Static () nesne boyunca sadece 1 kere olusturulur
    //Static Bir classın olusturma zamanında sadece 1 kez oluyor

    static {
        System.out.println(SpecialColor.BLUE+"Sataic StudentDto yuklendi"+SpecialColor.RESET);

    }
    //parmetresiz constructor
    public StudentDto() {
        super();
        this.midTerm=0.0;
        this.finalTerm=0.0;
        this.resaultTerm=0.0;
    }
    //parametreli constructor

    public StudentDto(Integer id, String name, String surname, LocalDate birthDay,Double midTerm, Double finalTerm, EStudentType estudentType) {
        *//*

*/
/*//*
/*
/Ust atadan gelen (StudentDto)
        super.setId(id);
        super.setName(name);
        super.setSurname(surname);
        super.setBirthDay(birthDay);*//*
*/
/*


        super(id,name,surname,birthDay);
        //Local işlemler için
        this.midTerm = midTerm;
        this.finalTerm = finalTerm;
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
*/

