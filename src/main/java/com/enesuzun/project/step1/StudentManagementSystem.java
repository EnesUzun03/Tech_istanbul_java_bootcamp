/*
package com.enesuzun.project_step1_file;

//Öğrenci yönetim sistemi

import com.enesuzun.utils.SpecialColor;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StudentManagementSystem {
    //Ogerncileri eklemek için kullanacağız
    //Field
    //dizi ile nerdeyse aynı sadece boyutu belli degil
    //Daha güvenilir
    private ArrayList<StudentDto> studentDtoList =new ArrayList<>();//sadece wrapper türleri destekler
    private int studentCounter =0;
    private static final String FILE_NAME="student.txt";

    //static
    static {

    }

    //parametresiz constructer
    public StudentManagementSystem() {
        //program başlarken ogrenci listesini yüklesin
        loadStudentListFromFile();
    }

    //ogrenci listesini yükle
    private void loadStudentListFromFile() {

    }

    /////////////////////////////////////////////////

    //Login
    //Register

    /////////////////////////////////////////////////
    //Ogrenci Ekle
    public void add(StudentDto dto){
        studentDtoList.add(new StudentDto(++studentCounter,dto.name(),dto.surname(),dto.birthDay(),dto.grade()));
        System.out.println(SpecialColor.BLUE+"Ogrenci eklendi"+SpecialColor.RESET);
        //Ogrenci ekelemenin file'a de yapılması gerekli
        saveToFile();

    }
    //ogrenci Listesi
    public void list(){
        //Ogrenci Listesi boşsa
        if(studentDtoList.isEmpty()){
            System.out.println(SpecialColor.RED+"Listeniz boştur"+SpecialColor.RESET);
            return;
        }else{
            studentDtoList.forEach(System.out::println);//Java 8 ile geldi tek satırda tüm çıktıyı almaya yarıyor

        }

    }
    //Ogrenci ara
    public void search(String name){
        //filter sqldeki where gibi filtrelemeyi sağlar
        studentDtoList.stream()
                //burada studentlerdan oluşan listede aynı türü temsilen temp oluşturduk
                //ve büyük küçük ayrımı olmasın diye ignorladık ve name arandı
                .filter(temp -> temp.name().equalsIgnoreCase(name))
                .forEach(System.out::println);

    }
    //Ogrenci güncelle
    public void update(int id,StudentDto dto){
        for (StudentDto temp:studentDtoList){
            if (temp.id()==id){
                temp.setName(dto.name());
                temp.setSurname(dto.surname());
                temp.setGrade(dto.grade());
                temp.setBirthDay(dto.birthDay());
                System.out.println("Ogrenci güncellendi");

                saveToFile();
                return;
            }
        }
        System.out.println(SpecialColor.RED+"Ogrenci bulunamdaı"+SpecialColor.RESET);

    }
    //Ogrenci sil
    public void delete(int id){
        studentDtoList.removeIf(temp-> temp.id()==id);
        System.out.println(SpecialColor.BLUE+id+"'li Ogrenci silindi"+SpecialColor.RESET);
        saveToFile();
    }


    /////////////////////////////////////////////////


    //FileIO creat
    //File Creat
    private void saveToFile() {
        //File işlemimde mutlaka try catch yazılmalıdır
        try(ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            objectOutputStream.writeObject(studentDtoList);
        }catch (IOException io){
            System.out.println(SpecialColor.RED+"Dosya ekleme hatası"+SpecialColor.RESET);
            io.printStackTrace();
        }
    }
    //File read
    //Ogrenci Listesini yükle (Dosya)
    public void loadStudentsListFromFile(){
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new
                FileInputStream(FILE_NAME))){

            studentDtoList=(ArrayList<StudentDto>) objectInputStream.readObject();
            studentCounter= studentDtoList.size();

        }catch (FileNotFoundException fileNotFoundException){
            System.out.println(SpecialColor.RED+"Ogrenci kayıdı bulunamadı"+SpecialColor.RESET);
            fileNotFoundException.printStackTrace();
        }catch (IOException io){
            System.out.println(SpecialColor.RED+"Dosya ekleme hatası"+SpecialColor.RESET);
            io.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





    /////////////////////////////////////////////////

    //Toplam ogranci sayısı
    //Rastgele ogrenci
    //ogrenci not ortalamasını
    //En yüksek not alan ogrenci
    //ogrenci siralaması(Dogum gunu)

    /////////////////////////////////////////////////

    //Console Seçim (Ogrenci ekle)
    public void chooise(){
        Scanner scanner=new Scanner(System.in);
        StudentManagementSystem studentManagementSystem=new
                StudentManagementSystem();

        //sonsuz While
        while(true){
            System.out.println("\n 1-Ogrenci ekle");
            System.out.println("\n 2-Ogrenci listele");
            System.out.println("\n 3-Ogrenci ara");
            System.out.println("\n 4-Ogrenci Güncelle");
            System.out.println("\n 5-Ogrenci Sil");
            System.out.println("\n 6-Ogrenci toplam ogrenci saysisi");
            System.out.println("\n 7-Ogrenci Rastgele");
            System.out.println("\n 8-Ogrenci ekleNot hesapla");
            System.out.println("\n 9-Ogrenci Em yuksek, En düsük notları göster");
            System.out.println("\n 10-Ogrenci ogrenci sirala (dogum gününe göre)");
            System.out.println("\n 11- Çikis");
            System.out.println("\nLütfen seçiminizi yapımız");

            int choosie=scanner.nextInt();
            scanner.nextLine();//durma yeri olacak
            StudentDto studentDto = new StudentDto();
            String name,surname;
            String birthDay;
            Double grade;


            switch (choosie){
                case 1:
                    System.out.println("Ogrenci adini");
                    name=scanner.nextLine();
                    System.out.println("Ogrenci soyadini");
                    surname=scanner.nextLine();
                    System.out.println("Ogrenci dogum tarihi");
                    birthDay=scanner.nextLine().toString();
                    System.out.println("Ogrenci Puanı");
                    grade=scanner.nextDouble();
                    studentDto.setId(studentCounter);
                    studentDto.setName(name);
                    studentDto.setSurname(surname);
                    studentDto.setCreatedDate(new Date(System.currentTimeMillis()));
                    //studentDto.setBirthDay(birthDay);
                    studentDto.setGrade(grade);
                    studentManagementSystem.add(studentDto);
                    break;
                case 2:
                    studentManagementSystem.list();


            }

        }

    }


}





*/
