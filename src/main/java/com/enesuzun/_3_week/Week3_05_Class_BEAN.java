package com.enesuzun._3_week;

/*1. Kullanıcının Soyisminin ilk üç harfini büyük yazınız ve soyisimi eğer 3 harften fazlaysa geri kalan harflerinin yerine yıldız (*)
 Hamit MIZRAK , Hamit MIZ***(Maskeleme)
 Tip(loop, conditional)*/
//2. İsim birlikte ayarlanırken, isim baş harfi büyük geri kalan küçük olacak şekilde ayarlanabilir mi?
//3. İsim birlikte dönen bir metod oluşturulabilir mi? Hamit Mızrak
//4. Soyisimde noktalama işaretleri olup olmadığını kontrol eden bir doğrulama ekleyebilir miyiz?
//5. İsim veya soyisim boş girildiğinde varsayılan bir değer atanabilir mi?
//6. İsim ve soyisimde sadece harfler olup olmadığını kontrol edebilir miyiz?
//7. Kullanıcıdan isim ve soyismini girerken karakter sınırı koyabilir miyiz?

import com.enesuzun.utils.SpecialColor;

import java.util.Date;
import java.util.Objects;

public class Week3_05_Class_BEAN {
    //field
    private long id;
    private String name;
    private String surname;
    private Date createdDate;

    //Constructor(paramtresiz) Yapıcı metot //bir classın newini oluşturduğumuz zaman Constructorlara bakar
    public Week3_05_Class_BEAN(){
        id=0L;
        this.name="İsminizi yazmadınız";
        surname="Soy adınızı yazmadınız";
        this.createdDate=new Date(System.currentTimeMillis());//
    }

    //Constructor(paramtreli)

    public Week3_05_Class_BEAN(long id, String name, String surname, Date createdDate) {//Overloading yapıyoruz burada
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.createdDate = createdDate;
    }

    public Week3_05_Class_BEAN(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.createdDate=new Date(System.currentTimeMillis());
    }

    //method
    public String fullName(){
        return  this.id + " " + name.toString()+" "+this.surname + " " +this.createdDate;
    }

    //toString

    @Override
    public String toString() {
        return "Week3_05_Class_BEAN{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    //Equals and HashCode//Api kısmında kullanılır her clasın kendine ait hash kodu vardır
    //Bullandığımız class bu mu demenin cevabı Equals'dır
    //kullandığımız class ın parmak izi dediğimiz kısım hash kodudur
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Week3_05_Class_BEAN that = (Week3_05_Class_BEAN) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, createdDate);
    }


    //getter setter


    public long id() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    //4. İsimde noktalama işaretleri olup olmadığını kontrol etsin varsa sonrasını silsin

    //2. İsim birlikte ayarlanırken, isim baş harfi büyük geri kalan küçük olacak şekilde ayarlanabilir mi?

    public void setName(String name) throws IllegalAccessException {
        if(name.matches(".*;,:<>!'#%&.*")){
            //throw new IllegalAccessException("isimde noktalama işareti var");
            System.err.println("isimde noktalama işareti var");
        }
        if(name!=null) {
            this.name=name.substring(0,1).toUpperCase()+name.substring(1,name.length()).toLowerCase();
        }else{
            this.name="bla";
        }
    }

    //surname



    /*1.
    Kullanıcının Soyisminin ilk üç harfini büyük yazınız ve soyisimi eğer 3 harften fazlaysa geri kalan harflerinin yerine yıldız (*)
    Hamit MIZRAK , Hamit MIZ***(Maskeleme)
    Tip(loop, conditional)*/

    public String surname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname!=null && surname.length()>=3){
            this.surname=surname.substring(0,3).toUpperCase()+"*".repeat(surname.length()-3);//repeat tekrar etmektir;
        } else if (surname!=null) {
            this.surname=surname.toUpperCase();
        }else{
            this.surname="";
        }
    }

    public Date createdDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static void main(String[] args) {
        Week3_05_Class_BEAN bean=new Week3_05_Class_BEAN();
        bean.setName("ENES,Eren");
        bean.setId(12L);
        bean.setSurname("UZUN");

        System.out.println(bean);

        System.out.println("############################################");

        /*

        Week3_05_Class_BEAN bean2=new Week3_05_Class_BEAN("Enes","UZUN");
        System.out.println(bean2);

        */


        //System.out.println("############################################");





    }
}
