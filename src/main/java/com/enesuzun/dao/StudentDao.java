package com.enesuzun.dao;

//Öğrenci yönetim sistemi
//Not Enum kısmını kontrol eteceğim değişmiyor

import com.enesuzun.dto.EStudentType;
import com.enesuzun.dto.StudentDto;
import com.enesuzun.exception.StudentNotExcaption;
import com.enesuzun.utils.SpecialColor;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDao implements IDaoGenerics<StudentDto>{
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
    public StudentDao() {
        //program başlarken ogrenci listesini yüklesin
        loadStudentListFromFile();
    }

    //ogrenci listesini yükle
    private void loadStudentListFromFile() {
        creatFileIfNotExists();
    }

    /////////////////////////////////////////////////

    //Login
    //Register

    /////////////////////////////////////////////////
    //Ogrenci Ekle
    @Override
    public StudentDto creat(StudentDto studentDto) {
        studentDtoList.add(
                new StudentDto(++studentCounter,studentDto.name(),studentDto.surname(),studentDto.midTerm(),
                        studentDto.finalTerm(),studentDto.birthDay(),studentDto.eStudentType()));
        System.out.println(SpecialColor.BLUE+"Ogrenci eklendi"+SpecialColor.RESET);
        //Ogrenci ekelemenin file'a de yapılması gerekli
        saveToFile();
        return studentDto;
    }
    //ogrenci Listesi
    @Override
    public ArrayList<StudentDto> list() {
        //Ogrenci Listesi boşsa
        if(studentDtoList.isEmpty()){
            System.out.println(SpecialColor.RED+"Listeniz boştur"+SpecialColor.RESET);
            throw new StudentNotExcaption("Ogrenci Yok");
        }else{
            System.out.println(SpecialColor.BLUE+ " Ogrenci Listesi " +SpecialColor.RESET);
            studentDtoList.forEach(System.out::println);//Java 8 ile geldi tek satırda tüm çıktıyı almaya yarıyor

        }
        return studentDtoList;
    }
    //Ogrenci ara
    @Override
    public StudentDto findbyNama(String name) {
        boolean found=studentDtoList
                .stream()
                .filter(temp -> temp.name().equalsIgnoreCase(name))
                .findAny()//herhangi bir eşleşen ogrenci var mı yok mu kontrol et
                .isPresent();
        //Ogrenci yoksa hata fırlatsaın
        if(!found){
            throw new StudentNotExcaption(name+ "adlı Ogrenci bulunamadı");
        }
        return null;

        /*//filter sqldeki where gibi filtrelemeyi sağlar
        studentDtoList.stream()
                //burada studentlerdan oluşan listede aynı türü temsilen temp oluşturduk
                //ve büyük küçük ayrımı olmasın diye ignorladık ve name arandı
                .filter(temp -> temp.name().equalsIgnoreCase(name))
                .forEach(System.out::println);

         */
    }
    //Ogrenci güncelle
    @Override
    public StudentDto update(int id, StudentDto studentDto) {
        for (StudentDto temp:studentDtoList){
            if (temp.id()==id){
                temp.setName(studentDto.name());
                temp.setSurname(studentDto.surname());
                temp.setMidTerm(studentDto.midTerm());
                temp.setFinalTerm(studentDto.finalTerm());
                temp.setBirthDay(studentDto.birthDay());
                temp.seteStudentType(studentDto.eStudentType());
                System.out.println(SpecialColor.BLUE+ temp +"Ogrenci Bilgileri güncellendi güncellendi"+SpecialColor.RESET);

                //Dosyaya Kaydet
                saveToFile();
            }
        }
        System.out.println(SpecialColor.RED+"Ogrenci bulunamdaı"+SpecialColor.RESET);
        return studentDto;
    }
    //Ogrenci sil
    @Override
    public StudentDto delete(int id) {
        //studentDtoList.removeIf(temp-> temp.id()==id);
        boolean removed = studentDtoList.removeIf(temp->temp.id()==id);
        if(removed){
            System.out.println(SpecialColor.BLUE+" Ogrenci silindi "+SpecialColor.RESET);
            saveToFile();
        }else{
            System.out.println(SpecialColor.RED+"Ogrenci silinmedi "+SpecialColor.RESET);
        }
        return null;
    }

    /////////////////////////////////////////////////
    //FileIO
    //File if not exist (Eğer student.txt yapısı yoksa olustur)
    //Bu dosya oluşmama hatasını çözmek için ama bende boyle bir hata olamadı hoca ekledi
    private void creatFileIfNotExists(){
        File file=new File(FILE_NAME);
        if(!file.exists()){
            try {
                file.createNewFile();//dosya yoksa olustur
                System.out.println(SpecialColor.BLUE+ FILE_NAME + "olusturuldu  " +SpecialColor.RESET);

            }catch (IOException ioException){
                System.out.println(SpecialColor.RED + "Dosya olusuturlurken hata oldu " +SpecialColor.RESET);
                ioException.printStackTrace();
            }
        }

    }
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
            System.out.println(SpecialColor.BLUE+"Dosyadan yuklenen ogrenci sayisi"+studentCounter+SpecialColor.RESET);

        }catch (FileNotFoundException fileNotFoundException){
            System.out.println(SpecialColor.RED+"Dosyadan yuklenen ogrenci kayıdı bulunamadı"+SpecialColor.RESET);
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
    //Enum ogrenci turu metodu
    private EStudentType studentTypeMethod(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Ogrenci türünü seciniz\n1-Lisans \n2- Yuksek lisan \n3- Doktora");
        int typeChooise=scanner.nextInt();
        EStudentType switchCaseStudent=switch (typeChooise){
            case 1 -> EStudentType.GRADUATE;
            case 2 -> EStudentType.UNDERGRADUATE;
            case 3 -> EStudentType.PHD;
            default -> EStudentType.OTHER;
        };
        return switchCaseStudent;
    }



    /////////////////////////////////////////////////

    //Console Seçim (Ogrenci ekle)
    @Override
    public void chooise(){
        Scanner scanner=new Scanner(System.in);
        StudentDao studentDao =new
                StudentDao();

        //sonsuz While
        while(true){
            studentDao.list();

            System.out.println(SpecialColor.YELLOW+"\n 1-Ogrenci ekle");
            System.out.println("2-Ogrenci listele");
            System.out.println(" 3-Ogrenci ara");
            System.out.println(" 4-Ogrenci Güncelle");
            System.out.println(" 5-Ogrenci Sil");
            System.out.println(" 6-Ogrenci toplam ogrenci saysisi");
            System.out.println(" 8-Ogrenci ekleNot hesapla");
            System.out.println(" 7-Ogrenci Rastgele");
            System.out.println(" 9-Ogrenci Em yuksek, En düsük notları göster");
            System.out.println(" 10-Ogrenci ogrenci sirala (dogum gününe göre)");
            System.out.println(" 11- Çikis"+SpecialColor.RESET);
            System.out.println(SpecialColor.CYAN+"Lütfen seçiminizi yapımız"+SpecialColor.RESET);

            //Secim yapacak
            int choosie=scanner.nextInt();
            scanner.nextLine();//durma yeri olacak

            //karar
            switch (choosie){
                case 1://Ogrenci ekle
                    System.out.println("Ogrenci adini");
                    String name=scanner.nextLine();
                    System.out.println("Ogrenci soyadini");
                    String surname=scanner.nextLine();
                    System.out.println("Ogrenci dogum tarihi");
                    System.out.println("tarih şoyle olamalı YYYY MM DD");
                    LocalDate birthDay=LocalDate.parse(scanner.nextLine());

                    System.out.println("Vize Puanı");
                    double midTerm=scanner.nextDouble();

                    System.out.println("Final Puanı");
                    double finalTerm=scanner.nextDouble();

                    studentDao.creat(new StudentDto(++studentCounter,name,surname,midTerm,finalTerm,birthDay,studentTypeMethod()));
                    break;
                case 2://Ogrenci Listelemek
                    studentDao.list();
                    break;
                case 3://Ogrenci ara
                    studentDao.list();
                    System.out.println(SpecialColor.BLUE+" Aranacak ogrenci ismini yazınız "+SpecialColor.RESET);
                    String searchNmae=scanner.nextLine();
                    studentDao.findbyNama(searchNmae);
                    break;
                case 4://Ogrenci Güncelle
                    studentDao.list();
                    System.out.println(" Güncelleme Yapılacak kisinin ıd sini yaz");
                    int id =scanner.nextInt();

                    scanner.nextLine();//Eger ınt sonrasi String gelecekse bunu yazmalıyız


                    System.out.println("Yeni Ogrenci adini");
                    String nameUpdate=scanner.nextLine();

                    System.out.println("Yeni Ogrenci soyadini");
                    String surnameUpdate=scanner.nextLine();

                    System.out.println("yeni Ogrenci dogum tarihi");
                    LocalDate birthDayUpdate=LocalDate.parse(scanner.nextLine());

                    System.out.println("yani Vize Puanı");
                    double midTermUpdate=scanner.nextDouble();

                    System.out.println("Yeni Final Puanı");
                    double finalTermUpdate=scanner.nextDouble();

                    StudentDto studentDtoUpdate = StudentDto.builder()
                            .name(nameUpdate)
                            .surname(surnameUpdate)
                            .midTerm(midTermUpdate)
                            .finalTerm(finalTermUpdate)
                            .birthDay(birthDayUpdate)
                            .eStudentType(studentTypeMethod())
                            .build();
                    try{
                        studentDao.update(id,studentDtoUpdate);
                    }catch (StudentNotExcaption e){
                        System.out.println(SpecialColor.RED+e.getMessage()+SpecialColor.RESET);
                    }
                    break;
                case 5://Ogrenci sil
                    studentDao.list();
                    System.out.println(SpecialColor.BLUE+" Silinecek ogrenci idsi "+SpecialColor.RESET);
                    int deleteId=scanner.nextInt();
                    studentDao.delete(deleteId);
                    break;
                case 6:
                    System.out.println("Case 6");
                    break;
                case 7:
                    System.out.println("Case");
                    break;
                case 8:
                    System.out.println("Case");
                    break;
                case 9:
                    System.out.println("Case");
                    break;
                case 10:
                    System.out.println("Case");
                    break;
                case 11:
                    System.out.println("sistemden çikis yapılıyopr");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Gecersiz seçim yaptınız tekrar deneyiniz");
                    break;

            }

        }

    }




}





