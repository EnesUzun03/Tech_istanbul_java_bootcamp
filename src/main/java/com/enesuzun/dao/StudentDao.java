package com.enesuzun.dao;

import com.enesuzun.dto.ERole;
import com.enesuzun.dto.EStudentType;
import com.enesuzun.dto.StudentDto;
import com.enesuzun.exception.StudentNotFoundException;
import com.enesuzun.iofiles.SpecialFileHandler;
import com.enesuzun.utils.SpecialColor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * 📌 Öğrenci Yönetim DAO (Data Access Object)
 * Öğrencilerin veritabanı işlemlerini yöneten sınıftır.
 */
public class StudentDao implements IDaoGenerics<StudentDto> {

    // Logger
    private static final Logger logger = Logger.getLogger(StudentDao.class.getName());

    // Field
    private final List<StudentDto> studentDtoList;

    // **📌 Scanner Nesnesini En Üste Tanımladık**
    private final Scanner scanner = new Scanner(System.in);

    // SpecialFileHandler
    private SpecialFileHandler fileHandler;

    // File dosyasına eklenen en büyük ID alıp yeni eklenecek file için 1 artır
    int maxId=0;

    ///////////////////////////////////////////////////////////////////////
    // static
    static {
        System.out.println(SpecialColor.RED+" Static: StudentDao"+ SpecialColor.RESET);
    }

    // Parametresiz Constructor
    /// Parametresiz Constructor
    public StudentDao() {
        this.fileHandler = new SpecialFileHandler();
        this.fileHandler.setFilePath("students.txt");

        studentDtoList = new ArrayList<>();
        this.fileHandler.createFileIfNotExists();

        List<String> fileLines = this.fileHandler.readFile();
        for (String line : fileLines) {
            StudentDto student = csvToStudent(line);
            if (student != null) {
                studentDtoList.add(student);
            } else {
                System.out.println("⚠️ Hatalı satır atlandı: " + line);
            }
        }

        System.out.println("✅ " + studentDtoList.size() + " öğrenci dosyadan başarıyla yüklendi!");
    }



    /// /////////////////////////////////////////////////////////////
    // 📌 Öğrenci nesnesini CSV formatına çevirme
    // Bu metod, bir StudentDto nesnesini virgülle ayrılmış bir metin (CSV) formatına çevirir.
    // Böylece öğrenci verileri bir dosyada satır bazlı olarak saklanabilir.
    private String studentToCsv(StudentDto student) {
        return student.getId() + "," +
                student.getName() + "," +
                student.getSurname() + "," +
                student.getMidTerm() + "," +
                student.getFinalTerm() + "," +
                student.getResultTerm() + "," +
                student.getStatus() + "," +
                student.getBirthDate() + "," +   // 📌 Doğum tarihi
                student.getEStudentType() + "," +  // 📌 Öğrenci türü (Enum)
                student.getERole();   // 📌 Rol (Enum)
    }


    // 📌 CSV formatındaki satırı StudentDto nesnesine çevirme
    // Bu metod, CSV formatındaki bir satırı parçalayarak bir StudentDto nesnesine dönüştürür.
    // Dosyadan okunan her satır için çağrılır ve veriyi uygun şekilde nesneye aktarır.
    // 📌 CSV formatındaki satırı StudentDto nesnesine çevirme (Dosyadan okurken)
    private StudentDto csvToStudent(String csvLine) {
        try {
            String[] parts = csvLine.split(",");

            if (parts.length < 10) {  // 📌 Eğer eksik veri varsa atla
                System.out.println(SpecialColor.RED + "⚠️ Eksik veri nedeniyle satır atlandı: " + csvLine + SpecialColor.RESET);
                return null;
            }

            Integer id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String surname = parts[2];
            Double midTerm = Double.parseDouble(parts[3]);
            Double finalTerm = Double.parseDouble(parts[4]);
            LocalDate birthDate = LocalDate.parse(parts[7]);

            // 📌 Enum dönüşüm hatalarına karşı önlem
            EStudentType studentType;
            try {
                studentType = EStudentType.valueOf(parts[8]);
            } catch (IllegalArgumentException e) {
                System.out.println(SpecialColor.RED + "⚠️ Hatalı öğrenci türü: " + parts[8] + " Varsayılan atanıyor!" + SpecialColor.RESET);
                studentType = EStudentType.OTHER;
            }

            ERole role;
            try {
                role = ERole.valueOf(parts[9]);
            } catch (IllegalArgumentException e) {
                System.out.println(SpecialColor.RED + "⚠️ Hatalı rol türü: " + parts[9] + " Varsayılan atanıyor!" + SpecialColor.RESET);
                role = ERole.STUDENT;
            }

            return new StudentDto(id, name, surname, birthDate, midTerm, finalTerm, studentType, role);

        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "⚠️ CSV'den öğrenci yükleme hatası: " + e.getMessage() + SpecialColor.RESET);
            return null;
        }
    }



    ///////////////////////////////////////////////////////////////

    /**
     * 📌 Öğrenci Ekleme (CREATE)
     */
    // C-R-U-D
    // Öğrenci Ekle
    // 📌 Öğrenci Ekleme (Create)
    @Override // Bun metotu ezmelisin.
    @Deprecated // Eski bir metot yenisini kullanın
    public Optional<StudentDto> create(StudentDto studentDto) {
        if (studentDto == null || findByName(studentDto.getName()).isPresent()) {
            logger.warning("❌ Geçersiz veya mevcut olan öğrenci eklenemez.");
            return Optional.empty();
        }
        try {
            // 📌 Verilerin doğrulanmasını sağlıyoruz
            validateStudent(studentDto);

            // Öğrenci Listesindeki En büyük ID Al
            maxId = studentDtoList
                    .stream()
                    .mapToInt(StudentDto::getId)
                    .max()
                    .orElse(0); // ;eğer öğrenci yoksa Sıfırdan başlat

            // Yeni Öğrenciyi ID'si En büyük olan ID'nin 1 fazlası
            studentDto.setId(maxId+1);

            // ID'yi artırıp nesneye atıyoruz
            // 📌 **ID artık public static olduğu için her sınıftan erişilebilir!**
            studentDtoList.add(studentDto);
            this.fileHandler.writeFile(studentToCsv(studentDto));

            System.out.println(studentDto+ SpecialColor.GREEN + "✅ Öğrenci başarıyla eklendi!" + SpecialColor.RESET);
            logger.info("✅ Yeni öğrenci eklendi: " + studentDto.getName());
            return Optional.of(studentDto);

        } catch (IllegalArgumentException e) {
            System.out.println(SpecialColor.RED + "⛔ Hata: " + e.getMessage() + SpecialColor.RESET);
            //return null; // Hata durumunda nesne oluşturulmaz
        }
        return Optional.of(studentDto);
    }

    // 📌 Öğrenci Validasyonu (Geçerli Veri Kontrolü)
    private void validateStudent(StudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("Öğrenci nesnesi boş olamaz.");
        }

        if (studentDto.getName() == null || studentDto.getName().trim().isEmpty() ||
                !studentDto.getName().matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) {
            throw new IllegalArgumentException("Ad yalnızca harf içermeli ve boş olamaz.");
        }

        if (studentDto.getSurname() == null || studentDto.getSurname().trim().isEmpty() ||
                !studentDto.getSurname().matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) {
            throw new IllegalArgumentException("Soyad yalnızca harf içermeli ve boş olamaz.");
        }

        if (studentDto.getBirthDate() == null || studentDto.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Doğum tarihi bugünden büyük olamaz.");
        }

        if (studentDto.getMidTerm() == null || studentDto.getMidTerm() < 0 || studentDto.getMidTerm() > 100) {
            throw new IllegalArgumentException("Vize notu 0 ile 100 arasında olmalıdır.");
        }

        if (studentDto.getFinalTerm() == null || studentDto.getFinalTerm() < 0 || studentDto.getFinalTerm() > 100) {
            throw new IllegalArgumentException("Final notu 0 ile 100 arasında olmalıdır.");
        }

        if (studentDto.getEStudentType() == null) {
            throw new IllegalArgumentException("Öğrenci türü boş olamaz.");
        }

        if (studentDto.getERole() == null) {
            throw new IllegalArgumentException("Öğrenci rolü boş olamaz.");
        }
    }


    ///////// LIST //////////
    // Öğrenci Listesi
    /**
     * 📌 Tüm Öğrencileri Listeleme (LIST)
     */
    // Öğrenci Listesi
    @Override
    @SuppressWarnings("unchecked") // Derleyici uyarılarını bastırmak için kullanılır.
    public List<StudentDto> list() {
        if (studentDtoList.isEmpty()) {
            System.out.println(SpecialColor.YELLOW + "⚠️ Öğrenci listesi şu an boş, dosyadan yükleniyor..." + SpecialColor.RESET);

            // 📌 Eğer liste boşsa, dosyadan tekrar oku
            List<String> fileLines = this.fileHandler.readFile();
            for (String line : fileLines) {
                StudentDto student = csvToStudent(line);
                if (student != null) {
                    studentDtoList.add(student);
                }
            }

            // Dosyadan öğrenci yüklenmezse uyarı ver
            if (studentDtoList.isEmpty()) {
                System.out.println(SpecialColor.RED + "⚠️ Dosyada öğrenci verisi bulunamadı!" + SpecialColor.RESET);
            } else {
                System.out.println(SpecialColor.GREEN + "✅ " + studentDtoList.size() + " öğrenci başarıyla yüklendi!" + SpecialColor.RESET);
            }
        }

        // Öğrencileri listele
        studentDtoList.forEach(System.out::println);
        return new ArrayList<>(studentDtoList);
    }


    /**
     * 📌 Öğrenci Adına Göre Bulma (FIND BY NAME)
     */
    @Override
    public Optional<StudentDto> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            //throw new IllegalArgumentException("❌ Geçersiz isim girdiniz.");
            System.out.println(SpecialColor.RED+ "❌ Geçersiz isim girdiniz."+SpecialColor.RESET);
        }
        return studentDtoList.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * 📌 Öğrenci ID'ye Göre Bulma (FIND BY ID)
     */
    @Override
    public Optional<StudentDto> findById(int id) {
        if (id <= 0) {
            //throw new IllegalArgumentException("❌ Geçersiz ID girdiniz.");
            System.out.println(SpecialColor.RED+ "❌ Geçersiz ID girdiniz."+SpecialColor.RESET);
        }
        return studentDtoList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .or(() -> {
                    logger.warning("⚠️ Öğrenci bulunamadı, ID: " + id);
                    return Optional.empty();
                });
    }

    /**
     * 📌 Öğrenci Güncelleme (UPDATE)
     */
    @Override
    public Optional<StudentDto> update(int id, StudentDto studentDto) {
        if (id <= 0 || studentDto == null) {
            // new IllegalArgumentException("❌ Güncelleme için geçerli bir öğrenci bilgisi giriniz.");
            System.out.println(SpecialColor.RED+ "❌ Güncelleme için geçerli bir öğrenci bilgisi giriniz"+SpecialColor.RESET);
        }
        try{
            for (int temp = 0; temp < studentDtoList.size(); temp++) {
                if (studentDtoList.get(temp).getId() == id) {
                    studentDtoList.set(temp, studentDto);
                    logger.info("✅ Öğrenci güncellendi: " + studentDto.getName());
                    // Güncellenmiş Öğrenci Bilgileri
                    System.out.println(SpecialColor.BLUE + temp + " Öğrenci Bilgileri Güncellendi" + SpecialColor.RESET);
                    // Dosyaya kaydet
                    this.fileHandler.writeFile(studentToCsv(studentDto));
                    return Optional.of(studentDto);//burada ofun anlamı burada bir değer var anlamında
                }
            }
            // throw new RegisterNotFoundException("⚠️ Güncellenecek öğrenci bulunamadı, ID: " + id);

        } catch (Exception e){
            e.printStackTrace();
            throw new StudentNotFoundException("Öğrenci bulunamadı.");
        }

        System.out.println(SpecialColor.RED+ "❌  Güncelleme için geçerli bir öğrenci bilgisi giriniz"+SpecialColor.RESET);
        return Optional.empty(); // Boş eleman olabilir 😒
    }

    /**
     * 📌 Öğrenci Silme (DELETE)
     */
    @Override
    public Optional<StudentDto> delete(int id) {
        Optional<StudentDto> studentToDelete = findById(id);
        if (studentToDelete.isPresent()) {//veri varsa
            studentDtoList.remove(studentToDelete.get());//liste içinde remove yapacak
            logger.info("✅ Öğrenci silindi, ID: " + id);
            System.out.println(SpecialColor.BLUE + "Öğrenci Silindi" + SpecialColor.RESET);
            // Silinen Öğrenciyi dosyaya kaydet
            this.fileHandler.writeFile(studentToCsv(studentToDelete.get()));
            return studentToDelete;
        } else {
            logger.warning("⚠️ Silinecek öğrenci bulunamadı, ID: " + id);
            return Optional.empty();
        }
    }


    ///////////////////////////////////////////////////////////////////////
    ///////// STUDENT TYPE //////////
    // Enum Öğrenci Türü Method
    public EStudentType studentTypeMethod() {
        System.out.println("\n"+SpecialColor.GREEN+"Öğrenci türünü seçiniz.\n1-)Lisans\n2-)Yüksek Lisans\n3-)Doktora"+SpecialColor.RESET);
        int typeChooise = scanner.nextInt();
        EStudentType swichCaseStudent = switch (typeChooise) {
            case 1 -> EStudentType.UNDERGRADUATE;
            case 2 -> EStudentType.GRADUATE;
            case 3 -> EStudentType.PHD;
            default -> EStudentType.OTHER;
        };
        return swichCaseStudent;
    }

    /// ///////////////////////////////////////////////////////////////////////
    // Console Seçim (Öğrenci)
    @Override
    public void choose() {
        while (true) {
            try {
                System.out.println("\n"+SpecialColor.BLUE+"===== ÖĞRENCİ YÖNETİM SİSTEMİ ====="+SpecialColor.RESET);
                System.out.println(SpecialColor.YELLOW+"1. Öğrenci Ekle");
                System.out.println("2. Öğrenci Listele");
                System.out.println("3. Öğrenci Ara");
                System.out.println("4. Öğrenci Güncelle");
                System.out.println("5. Öğrenci Sil");
                System.out.println("6. Toplam Öğrenci Sayısı");
                System.out.println("7. Rastgele Öğrenci Seç");
                System.out.println("8. Öğrenci Not Ortalaması Hesapla");
                System.out.println("9. En Yüksek & En Düşük Not Alan Öğrenci");
                System.out.println("10. Öğrencileri Doğum Tarihine Göre Sırala");
                System.out.println("11. Öğrenci Geçti/Kaldı Durumunu göster");
                System.out.println("12. Çıkış"+SpecialColor.RESET);
                System.out.print("\n"+SpecialColor.PURPLE+"Seçiminizi yapınız: "+SpecialColor.RESET);

                int choice = scanner.nextInt();
                scanner.nextLine(); // Boşluğu temizleme

                switch (choice) {
                    case 1 -> chooiseStudentAdd();

                    case 2 -> chooiseStudentList();

                    case 3 -> chooiseStudenSearch();

                    case 4 -> chooiseStudenUpdate();

                    case 5 -> chooiseStudenDelete();

                    case 6 -> chooiseSumCounter();

                    case 7 -> chooiseRandomStudent();

                    case 8 -> chooiseStudentNoteAverage();

                    case 9 -> chooiseStudentNoteMinAndMax();

                    case 10 -> chooiseStudentBirthdaySortedDate();

                    case 11 -> listStudentsWithStatus();

                    case 12 -> chooiseExit();

                    default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                }
            } catch (Exception e) {
                System.out.println(SpecialColor.RED + "⛔ Beklenmeyen bir hata oluştu: " + e.getMessage() + SpecialColor.RESET);
                scanner.nextLine(); // Hata oluştuğunda kullanıcıdan yeni giriş alabilmesi için scanner'ı temizle
            }
        }
    }

    /// ///////////////////////////////////////////////////////////////////////
    /// Student Add
    public void chooiseStudentAdd() {
        while (true) { // Kullanıcı geçerli giriş yapana kadar döngü devam eder
            try {
                // 📌 Kullanıcıdan geçerli bir ad alana kadar döngüde kal
                String name;
                while (true) {
                    System.out.print("Öğrenci Adı: ");
                    name = scanner.nextLine().trim();
                    if (name.matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) break;
                    System.out.println(SpecialColor.RED + "⛔ Geçersiz ad! Sadece harf giriniz." + SpecialColor.RESET);
                }

                // 📌 Kullanıcıdan geçerli bir soyad alana kadar döngüde kal
                String surname;
                while (true) {
                    System.out.print("Öğrenci Soyadı: ");
                    surname = scanner.nextLine().trim();
                    if (surname.matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) break;
                    System.out.println(SpecialColor.RED + "⛔ Geçersiz soyad! Sadece harf giriniz." + SpecialColor.RESET);
                }

                // 📌 Kullanıcıdan geçerli bir doğum tarihi alana kadar döngüde kal
                LocalDate birthDate;
                while (true) {
                    System.out.print("Doğum Tarihi (YYYY-MM-DD): ");
                    String input = scanner.nextLine().trim();
                    try {
                        birthDate = LocalDate.parse(input);
                        if (!birthDate.isAfter(LocalDate.now())) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz doğum tarihi! Gelecekte olamaz." + SpecialColor.RESET);
                    } catch (Exception e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz format! Lütfen YYYY-MM-DD olarak giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Kullanıcıdan geçerli bir vize notu alana kadar döngüde kal
                double midTerm;
                while (true) {
                    System.out.print("Vize Notu (0-100): ");
                    String input = scanner.nextLine().trim();
                    try {
                        midTerm = Double.parseDouble(input);
                        if (midTerm >= 0 && midTerm <= 100) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz vize notu! 0-100 arasında giriniz." + SpecialColor.RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz giriş! Lütfen bir sayı giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Kullanıcıdan geçerli bir final notu alana kadar döngüde kal
                double finalTerm;
                while (true) {
                    System.out.print("Final Notu (0-100): ");
                    String input = scanner.nextLine().trim();
                    try {
                        finalTerm = Double.parseDouble(input);
                        if (finalTerm >= 0 && finalTerm <= 100) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz final notu! 0-100 arasında giriniz." + SpecialColor.RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz giriş! Lütfen bir sayı giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Öğrenci türünü seçme
                EStudentType studentType = studentTypeMethod();

                // 📌 Öğrenci nesnesini oluştur
                // Integer id, String name, String surname, LocalDate birthDate,Double midTerm, Double finalTerm,EStudentType eStudentType
                StudentDto newStudent = new StudentDto(maxId, name, surname,birthDate, midTerm, finalTerm, studentType,ERole.STUDENT);
                Optional<StudentDto> createdStudent = create(newStudent);

                if (createdStudent != null) {
                    break; // 🔹 Başarıyla eklenirse döngüden çık
                } else {
                    System.out.println(SpecialColor.RED + "⛔ Öğrenci eklenirken hata oluştu. Lütfen tekrar deneyin." + SpecialColor.RESET);
                }
            } catch (Exception e) {
                System.out.println(SpecialColor.RED + "⛔ Beklenmeyen hata oluştu: " + e.getMessage() + SpecialColor.RESET);
                scanner.nextLine(); // 🔹 Hata sonrası buffer temizleme
            }
        }
    }

    /// Student List
    /// Student List
    public void chooiseStudentList() {
        try {
            List<StudentDto> students = list(); // Öğrenci listesini al
            if (students.isEmpty()) {
                System.out.println(SpecialColor.RED + "⚠️ Öğrenci listesi boş." + SpecialColor.RESET);
            } else {
                System.out.println(SpecialColor.GREEN + "📜 Öğrenci Listesi:" + SpecialColor.RESET);
                //students.forEach(System.out::println);
            }
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    /// Student Search
    public void chooiseStudenSearch() {
        list();
        System.out.print("Aranacak Öğrenci Adı: ");
        String searchName = scanner.nextLine();
        try {
            System.out.println(findByName(searchName));
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /// Student Update
    public void chooiseStudenUpdate() {
        list();
        System.out.print("Güncellenecek Öğrenci ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Boşluğu temizleme

        System.out.print("Yeni Adı: ");
        String nameUpdate = scanner.nextLine();

        System.out.print("Yeni Soyadı: ");
        String surnameUpdate = scanner.nextLine();

        System.out.print("Doğum Tarihi (YYYY-MM-DD): ");
        LocalDate birthDateUpdate = LocalDate.parse(scanner.nextLine());

        System.out.print("Yeni Vize Notu: ");
        double midTermUpdate = scanner.nextDouble();

        System.out.print("Yeni Final Notu: ");
        double finalTermUpdate = scanner.nextDouble();

        //  // Integer id, String name, String surname, LocalDate birthDate,Double midTerm, Double finalTerm,EStudentType eStudentType
        StudentDto studentUpdate = new StudentDto(id, nameUpdate, surnameUpdate,birthDateUpdate, midTermUpdate, finalTermUpdate, studentTypeMethod(), ERole.STUDENT);
        try {
            update(id, studentUpdate);
            System.out.println("Öğrenci başarıyla güncellendi.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /// Student Delete
    public void chooiseStudenDelete() {
        list();
        System.out.print("Silinecek Öğrenci ID: ");
        int deleteID = scanner.nextInt();
        try {
            delete(deleteID);
            System.out.println("Öğrenci başarıyla silindi.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////
    // Toplam Öğrenci Sayısı

    /// Student Sum Counter
    public void chooiseSumCounter() {
        System.out.println("Toplam Öğrenci Sayısı: " + studentDtoList.size());
    }

    // Rastgele Öğrenci
    /// Student Random
    public void chooiseRandomStudent() {
        if (!studentDtoList.isEmpty()) {
            StudentDto randomStudent = studentDtoList.get((int) (Math.random() * studentDtoList.size()));
            System.out.println("Rastgele Seçilen Öğrenci: " + randomStudent);
        } else {
            System.out.println("Sistemde öğrenci yok.");
        }
    }

    // Öğrenci Not Ortalaması Hesapla
    /// Öğrenci Not Ortalaması Hesapla
    public void chooiseStudentNoteAverage() {
        if (!studentDtoList.isEmpty()) {
            double avg = studentDtoList.stream()
                    .mapToDouble(StudentDto::getResultTerm)
                    .average()
                    .orElse(0.0);
            System.out.println("Öğrenci Not Ortalaması: " + avg);
        } else {
            System.out.println("Öğrenci listesi boş.");
        }
    }

    // En Yüksek veya En Düşük Not Alan Öğrenci

    /// En Yüksek & En Düşük Not Alan Öğrenci
    public void chooiseStudentNoteMinAndMax() {
        if (!studentDtoList.isEmpty()) {
            StudentDto maxStudent = studentDtoList.stream()
                    .max((s1, s2) -> Double.compare(s1.getResultTerm(), s2.getResultTerm()))
                    .orElse(null);

            StudentDto minStudent = studentDtoList.stream()
                    .min((s1, s2) -> Double.compare(s1.getResultTerm(), s2.getResultTerm()))
                    .orElse(null);

            System.out.println("En Yüksek Not Alan Öğrenci: " + maxStudent);
            System.out.println("En Düşük Not Alan Öğrenci: " + minStudent);
        } else {
            System.out.println("Öğrenci listesi boş.");
        }
    }

    // Öğrenci Sıralaması (Doğum günü)

    /// Öğrencileri Doğum Tarihine Göre Sırala
    public void chooiseStudentBirthdaySortedDate() {
        studentDtoList.stream()
                .sorted((s1, s2) -> s1.getBirthDate().compareTo(s2.getBirthDate()))
                .forEach(System.out::println);
    }

    // Geçen Öğrencilere
    private List<StudentDto> listStudentsWithStatus() {
        List<StudentDto> studentDtostatus = list();
        return studentDtostatus;
    }

    // Exit
    public void chooiseExit() {
        System.out.println("Sistemden çıkılıyor...");
        scanner.close();
        return;
    }

    // TEST
    public static void main(String[] args) {
        //StudentDao studentDao= new StudentDao();
        //studentDao.choose();
    }

} // end class



/*
//kendi yazdığım kısım

import com.enesuzun.dto.EStudentType;
import com.enesuzun.dto.StudentDto;
import com.enesuzun.exception.StudentNotFoundException;
import com.enesuzun.utils.SpecialColor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

*/
/**
 * 📌 Öğrenci Yönetim DAO (Data Access Object)
 * Öğrencilerin veritabanı işlemlerini yöneten sınıftır.
 *//*

public class StudentDao implements IDaoGenerics<StudentDto> {

    // Logger
    private static final Logger logger = Logger.getLogger(StudentDao.class.getName());

    // Field
    private final List<StudentDto> studentDtoList;

    // **📌 Scanner Nesnesini En Üste Tanımladık**
    private final Scanner scanner = new Scanner(System.in);

    // SpecialFileHandler
    private SpecialFileHandler fileHandler;

    // File dosyasına eklenen en büyük ID alıp yeni eklenecek file için 1 artır
    int maxId=0;

    ///////////////////////////////////////////////////////////////////////
    // static
    static {
        System.out.println(SpecialColor.RED+" Static: StudentDao"+ SpecialColor.RESET);
    }

    // Parametresiz Constructor
    /// Parametresiz Constructor
    public StudentDao() {
        this.fileHandler = new SpecialFileHandler();
        this.fileHandler.setFilePath("students.txt");

        studentDtoList = new ArrayList<>();
        this.fileHandler.createFileIfNotExists();

        List<String> fileLines = this.fileHandler.readFile();
        for (String line : fileLines) {
            StudentDto student = csvToStudent(line);
            if (student != null) {
                studentDtoList.add(student);
            } else {
                System.out.println("⚠️ Hatalı satır atlandı: " + line);
            }
        }

        System.out.println("✅ " + studentDtoList.size() + " öğrenci dosyadan başarıyla yüklendi!");
    }



    /// /////////////////////////////////////////////////////////////
    // 📌 Öğrenci nesnesini CSV formatına çevirme
    // Bu metod, bir StudentDto nesnesini virgülle ayrılmış bir metin (CSV) formatına çevirir.
    // Böylece öğrenci verileri bir dosyada satır bazlı olarak saklanabilir.
    private String studentToCsv(StudentDto student) {
        return student.getId() + "," +
                student.getName() + "," +
                student.getSurname() + "," +
                student.getMidTerm() + "," +
                student.getFinalTerm() + "," +
                student.getResultTerm() + "," +
                student.getStatus() + "," +
                student.getBirthDate() + "," +   // 📌 Doğum tarihi
                student.getEStudentType() + "," +  // 📌 Öğrenci türü (Enum)
                student.getERole();   // 📌 Rol (Enum)
    }


    // 📌 CSV formatındaki satırı StudentDto nesnesine çevirme
    // Bu metod, CSV formatındaki bir satırı parçalayarak bir StudentDto nesnesine dönüştürür.
    // Dosyadan okunan her satır için çağrılır ve veriyi uygun şekilde nesneye aktarır.
    // 📌 CSV formatındaki satırı StudentDto nesnesine çevirme (Dosyadan okurken)
    private StudentDto csvToStudent(String csvLine) {
        try {
            String[] parts = csvLine.split(",");

            if (parts.length < 10) {  // 📌 Eğer eksik veri varsa atla
                System.out.println(SpecialColor.RED + "⚠️ Eksik veri nedeniyle satır atlandı: " + csvLine + SpecialColor.RESET);
                return null;
            }

            Integer id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String surname = parts[2];
            Double midTerm = Double.parseDouble(parts[3]);
            Double finalTerm = Double.parseDouble(parts[4]);
            LocalDate birthDate = LocalDate.parse(parts[7]);

            // 📌 Enum dönüşüm hatalarına karşı önlem
            EStudentType studentType;
            try {
                studentType = EStudentType.valueOf(parts[8]);
            } catch (IllegalArgumentException e) {
                System.out.println(SpecialColor.RED + "⚠️ Hatalı öğrenci türü: " + parts[8] + " Varsayılan atanıyor!" + SpecialColor.RESET);
                studentType = EStudentType.OTHER;
            }

            ERole role;
            try {
                role = ERole.valueOf(parts[9]);
            } catch (IllegalArgumentException e) {
                System.out.println(SpecialColor.RED + "⚠️ Hatalı rol türü: " + parts[9] + " Varsayılan atanıyor!" + SpecialColor.RESET);
                role = ERole.STUDENT;
            }

            return new StudentDto(id, name, surname, birthDate, midTerm, finalTerm, studentType, role);

        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "⚠️ CSV'den öğrenci yükleme hatası: " + e.getMessage() + SpecialColor.RESET);
            return null;
        }
    }



    ///////////////////////////////////////////////////////////////

    */
/**
     * 📌 Öğrenci Ekleme (CREATE)
     *//*

    // C-R-U-D
    // Öğrenci Ekle
    // 📌 Öğrenci Ekleme (Create)
    @Override // Bun metotu ezmelisin.
    @Deprecated // Eski bir metot yenisini kullanın
    public Optional<StudentDto> create(StudentDto studentDto) {
        if (studentDto == null || findByName(studentDto.getName()).isPresent()) {
            logger.warning("❌ Geçersiz veya mevcut olan öğrenci eklenemez.");
            return Optional.empty();
        }
        try {
            // 📌 Verilerin doğrulanmasını sağlıyoruz
            validateStudent(studentDto);

            // Öğrenci Listesindeki En büyük ID Al
            maxId = studentDtoList
                    .stream()
                    .mapToInt(StudentDto::getId)
                    .max()
                    .orElse(0); // ;eğer öğrenci yoksa Sıfırdan başlat

            // Yeni Öğrenciyi ID'si En büyük olan ID'nin 1 fazlası
            studentDto.setId(maxId+1);

            // ID'yi artırıp nesneye atıyoruz
            // 📌 **ID artık public static olduğu için her sınıftan erişilebilir!**
            studentDtoList.add(studentDto);
            this.fileHandler.writeFile(studentToCsv(studentDto));

            System.out.println(studentDto+ SpecialColor.GREEN + "✅ Öğrenci başarıyla eklendi!" + SpecialColor.RESET);
            logger.info("✅ Yeni öğrenci eklendi: " + studentDto.getName());
            return Optional.of(studentDto);

        } catch (IllegalArgumentException e) {
            System.out.println(SpecialColor.RED + "⛔ Hata: " + e.getMessage() + SpecialColor.RESET);
            //return null; // Hata durumunda nesne oluşturulmaz
        }
        return Optional.of(studentDto);
    }

    // 📌 Öğrenci Validasyonu (Geçerli Veri Kontrolü)
    private void validateStudent(StudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("Öğrenci nesnesi boş olamaz.");
        }

        if (studentDto.getName() == null || studentDto.getName().trim().isEmpty() ||
                !studentDto.getName().matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) {
            throw new IllegalArgumentException("Ad yalnızca harf içermeli ve boş olamaz.");
        }

        if (studentDto.getSurname() == null || studentDto.getSurname().trim().isEmpty() ||
                !studentDto.getSurname().matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) {
            throw new IllegalArgumentException("Soyad yalnızca harf içermeli ve boş olamaz.");
        }

        if (studentDto.getBirthDate() == null || studentDto.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Doğum tarihi bugünden büyük olamaz.");
        }

        if (studentDto.getMidTerm() == null || studentDto.getMidTerm() < 0 || studentDto.getMidTerm() > 100) {
            throw new IllegalArgumentException("Vize notu 0 ile 100 arasında olmalıdır.");
        }

        if (studentDto.getFinalTerm() == null || studentDto.getFinalTerm() < 0 || studentDto.getFinalTerm() > 100) {
            throw new IllegalArgumentException("Final notu 0 ile 100 arasında olmalıdır.");
        }

        if (studentDto.getEStudentType() == null) {
            throw new IllegalArgumentException("Öğrenci türü boş olamaz.");
        }

        if (studentDto.getERole() == null) {
            throw new IllegalArgumentException("Öğrenci rolü boş olamaz.");
        }
    }


    ///////// LIST //////////
    // Öğrenci Listesi
    */
/**
     * 📌 Tüm Öğrencileri Listeleme (LIST)
     *//*

    // Öğrenci Listesi
    @Override
    @SuppressWarnings("unchecked") // Derleyici uyarılarını bastırmak için kullanılır.
    public List<StudentDto> list() {
        if (studentDtoList.isEmpty()) {
            System.out.println(SpecialColor.YELLOW + "⚠️ Öğrenci listesi şu an boş, dosyadan yükleniyor..." + SpecialColor.RESET);

            // 📌 Eğer liste boşsa, dosyadan tekrar oku
            List<String> fileLines = this.fileHandler.readFile();
            for (String line : fileLines) {
                StudentDto student = csvToStudent(line);
                if (student != null) {
                    studentDtoList.add(student);
                }
            }

            // Dosyadan öğrenci yüklenmezse uyarı ver
            if (studentDtoList.isEmpty()) {
                System.out.println(SpecialColor.RED + "⚠️ Dosyada öğrenci verisi bulunamadı!" + SpecialColor.RESET);
            } else {
                System.out.println(SpecialColor.GREEN + "✅ " + studentDtoList.size() + " öğrenci başarıyla yüklendi!" + SpecialColor.RESET);
            }
        }

        // Öğrencileri listele
        studentDtoList.forEach(System.out::println);
        return new ArrayList<>(studentDtoList);
    }


    */
/**
     * 📌 Öğrenci Adına Göre Bulma (FIND BY NAME)
     *//*

    @Override
    public Optional<StudentDto> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            //throw new IllegalArgumentException("❌ Geçersiz isim girdiniz.");
            System.out.println(SpecialColor.RED+ "❌ Geçersiz isim girdiniz."+SpecialColor.RESET);
        }
        return studentDtoList.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    */
/**
     * 📌 Öğrenci ID'ye Göre Bulma (FIND BY ID)
     *//*

    @Override
    public Optional<StudentDto> findById(int id) {
        if (id <= 0) {
            //throw new IllegalArgumentException("❌ Geçersiz ID girdiniz.");
            System.out.println(SpecialColor.RED+ "❌ Geçersiz ID girdiniz."+SpecialColor.RESET);
        }
        return studentDtoList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .or(() -> {
                    logger.warning("⚠️ Öğrenci bulunamadı, ID: " + id);
                    return Optional.empty();
                });
    }

    */
/**
     * 📌 Öğrenci Güncelleme (UPDATE)
     *//*

    @Override
    public Optional<StudentDto> update(int id, StudentDto studentDto) {
        if (id <= 0 || studentDto == null) {
            // new IllegalArgumentException("❌ Güncelleme için geçerli bir öğrenci bilgisi giriniz.");
            System.out.println(SpecialColor.RED+ "❌ Güncelleme için geçerli bir öğrenci bilgisi giriniz"+SpecialColor.RESET);
        }
        try{
            for (int temp = 0; temp < studentDtoList.size(); temp++) {
                if (studentDtoList.get(temp).getId() == id) {
                    studentDtoList.set(temp, studentDto);
                    logger.info("✅ Öğrenci güncellendi: " + studentDto.getName());
                    // Güncellenmiş Öğrenci Bilgileri
                    System.out.println(SpecialColor.BLUE + temp + " Öğrenci Bilgileri Güncellendi" + SpecialColor.RESET);
                    // Dosyaya kaydet
                    this.fileHandler.writeFile(studentToCsv(studentDto));
                    return Optional.of(studentDto);
                }
            }
            // throw new RegisterNotFoundException("⚠️ Güncellenecek öğrenci bulunamadı, ID: " + id);

        } catch (Exception e){
            e.printStackTrace();
            throw new StudentNotFoundException("Öğrenci bulunamadı.");
        }

        System.out.println(SpecialColor.RED+ "❌  Güncelleme için geçerli bir öğrenci bilgisi giriniz"+SpecialColor.RESET);
        return Optional.empty(); // Boş eleman olabilir 😒
    }

    */
/**
     * 📌 Öğrenci Silme (DELETE)
     *//*

    @Override
    public Optional<StudentDto> delete(int id) {
        Optional<StudentDto> studentToDelete = findById(id);
        if (studentToDelete.isPresent()) {
            studentDtoList.remove(studentToDelete.get());
            logger.info("✅ Öğrenci silindi, ID: " + id);
            System.out.println(SpecialColor.BLUE + "Öğrenci Silindi" + SpecialColor.RESET);
            // Silinen Öğrenciyi dosyaya kaydet
            this.fileHandler.writeFile(studentToCsv(studentToDelete.get()));
            return studentToDelete;
        } else {
            logger.warning("⚠️ Silinecek öğrenci bulunamadı, ID: " + id);
            return Optional.empty();
        }
    }


    ///////////////////////////////////////////////////////////////////////
    ///////// STUDENT TYPE //////////
    // Enum Öğrenci Türü Method
    public EStudentType studentTypeMethod() {
        System.out.println("\n"+SpecialColor.GREEN+"Öğrenci türünü seçiniz.\n1-)Lisans\n2-)Yüksek Lisans\n3-)Doktora"+SpecialColor.RESET);
        int typeChooise = scanner.nextInt();
        EStudentType swichCaseStudent = switch (typeChooise) {
            case 1 -> EStudentType.UNDERGRADUATE;
            case 2 -> EStudentType.GRADUATE;
            case 3 -> EStudentType.PHD;
            default -> EStudentType.OTHER;
        };
        return swichCaseStudent;
    }

    /// ///////////////////////////////////////////////////////////////////////
    // Console Seçim (Öğrenci)
    @Override
    public void choose() {
        while (true) {
            try {
                System.out.println("\n"+SpecialColor.BLUE+"===== ÖĞRENCİ YÖNETİM SİSTEMİ ====="+SpecialColor.RESET);
                System.out.println(SpecialColor.YELLOW+"1. Öğrenci Ekle");
                System.out.println("2. Öğrenci Listele");
                System.out.println("3. Öğrenci Ara");
                System.out.println("4. Öğrenci Güncelle");
                System.out.println("5. Öğrenci Sil");
                System.out.println("6. Toplam Öğrenci Sayısı");
                System.out.println("7. Rastgele Öğrenci Seç");
                System.out.println("8. Öğrenci Not Ortalaması Hesapla");
                System.out.println("9. En Yüksek & En Düşük Not Alan Öğrenci");
                System.out.println("10. Öğrencileri Doğum Tarihine Göre Sırala");
                System.out.println("11. Öğrenci Geçti/Kaldı Durumunu göster");
                System.out.println("12. Çıkış"+SpecialColor.RESET);
                System.out.print("\n"+SpecialColor.PURPLE+"Seçiminizi yapınız: "+SpecialColor.RESET);

                int choice = scanner.nextInt();
                scanner.nextLine(); // Boşluğu temizleme

                switch (choice) {
                    case 1 -> chooiseStudentAdd();

                    case 2 -> chooiseStudentList();

                    case 3 -> chooiseStudenSearch();

                    case 4 -> chooiseStudenUpdate();

                    case 5 -> chooiseStudenDelete();

                    case 6 -> chooiseSumCounter();

                    case 7 -> chooiseRandomStudent();

                    case 8 -> chooiseStudentNoteAverage();

                    case 9 -> chooiseStudentNoteMinAndMax();

                    case 10 -> chooiseStudentBirthdaySortedDate();

                    case 11 -> listStudentsWithStatus();

                    case 12 -> chooiseExit();

                    default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                }
            } catch (Exception e) {
                System.out.println(SpecialColor.RED + "⛔ Beklenmeyen bir hata oluştu: " + e.getMessage() + SpecialColor.RESET);
                scanner.nextLine(); // Hata oluştuğunda kullanıcıdan yeni giriş alabilmesi için scanner'ı temizle
            }
        }
    }

    /// ///////////////////////////////////////////////////////////////////////
    /// Student Add
    public void chooiseStudentAdd() {
        while (true) { // Kullanıcı geçerli giriş yapana kadar döngü devam eder
            try {
                // 📌 Kullanıcıdan geçerli bir ad alana kadar döngüde kal
                String name;
                while (true) {
                    System.out.print("Öğrenci Adı: ");
                    name = scanner.nextLine().trim();
                    if (name.matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) break;
                    System.out.println(SpecialColor.RED + "⛔ Geçersiz ad! Sadece harf giriniz." + SpecialColor.RESET);
                }

                // 📌 Kullanıcıdan geçerli bir soyad alana kadar döngüde kal
                String surname;
                while (true) {
                    System.out.print("Öğrenci Soyadı: ");
                    surname = scanner.nextLine().trim();
                    if (surname.matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) break;
                    System.out.println(SpecialColor.RED + "⛔ Geçersiz soyad! Sadece harf giriniz." + SpecialColor.RESET);
                }

                // 📌 Kullanıcıdan geçerli bir doğum tarihi alana kadar döngüde kal
                LocalDate birthDate;
                while (true) {
                    System.out.print("Doğum Tarihi (YYYY-MM-DD): ");
                    String input = scanner.nextLine().trim();
                    try {
                        birthDate = LocalDate.parse(input);
                        if (!birthDate.isAfter(LocalDate.now())) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz doğum tarihi! Gelecekte olamaz." + SpecialColor.RESET);
                    } catch (Exception e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz format! Lütfen YYYY-MM-DD olarak giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Kullanıcıdan geçerli bir vize notu alana kadar döngüde kal
                double midTerm;
                while (true) {
                    System.out.print("Vize Notu (0-100): ");
                    String input = scanner.nextLine().trim();
                    try {
                        midTerm = Double.parseDouble(input);
                        if (midTerm >= 0 && midTerm <= 100) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz vize notu! 0-100 arasında giriniz." + SpecialColor.RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz giriş! Lütfen bir sayı giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Kullanıcıdan geçerli bir final notu alana kadar döngüde kal
                double finalTerm;
                while (true) {
                    System.out.print("Final Notu (0-100): ");
                    String input = scanner.nextLine().trim();
                    try {
                        finalTerm = Double.parseDouble(input);
                        if (finalTerm >= 0 && finalTerm <= 100) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz final notu! 0-100 arasında giriniz." + SpecialColor.RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz giriş! Lütfen bir sayı giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Öğrenci türünü seçme
                EStudentType studentType = studentTypeMethod();

                // 📌 Öğrenci nesnesini oluştur
                // Integer id, String name, String surname, LocalDate birthDate,Double midTerm, Double finalTerm,EStudentType eStudentType
                StudentDto newStudent = new StudentDto(maxId, name, surname,birthDate, midTerm, finalTerm, studentType,ERole.STUDENT);
                Optional<StudentDto> createdStudent = create(newStudent);

                if (createdStudent != null) {
                    break; // 🔹 Başarıyla eklenirse döngüden çık
                } else {
                    System.out.println(SpecialColor.RED + "⛔ Öğrenci eklenirken hata oluştu. Lütfen tekrar deneyin." + SpecialColor.RESET);
                }
            } catch (Exception e) {
                System.out.println(SpecialColor.RED + "⛔ Beklenmeyen hata oluştu: " + e.getMessage() + SpecialColor.RESET);
                scanner.nextLine(); // 🔹 Hata sonrası buffer temizleme
            }
        }
    }

    /// Student List
    /// Student List
    public void chooiseStudentList() {
        try {
            List<StudentDto> students = list(); // Öğrenci listesini al
            if (students.isEmpty()) {
                System.out.println(SpecialColor.RED + "⚠️ Öğrenci listesi boş." + SpecialColor.RESET);
            } else {
                System.out.println(SpecialColor.GREEN + "📜 Öğrenci Listesi:" + SpecialColor.RESET);
                //students.forEach(System.out::println);
            }
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    /// Student Search
    public void chooiseStudenSearch() {
        list();
        System.out.print("Aranacak Öğrenci Adı: ");
        String searchName = scanner.nextLine();
        try {
            System.out.println(findByName(searchName));
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /// Student Update
    public void chooiseStudenUpdate() {
        list();
        System.out.print("Güncellenecek Öğrenci ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Boşluğu temizleme

        System.out.print("Yeni Adı: ");
        String nameUpdate = scanner.nextLine();

        System.out.print("Yeni Soyadı: ");
        String surnameUpdate = scanner.nextLine();

        System.out.print("Doğum Tarihi (YYYY-MM-DD): ");
        LocalDate birthDateUpdate = LocalDate.parse(scanner.nextLine());

        System.out.print("Yeni Vize Notu: ");
        double midTermUpdate = scanner.nextDouble();

        System.out.print("Yeni Final Notu: ");
        double finalTermUpdate = scanner.nextDouble();

        //  // Integer id, String name, String surname, LocalDate birthDate,Double midTerm, Double finalTerm,EStudentType eStudentType
        StudentDto studentUpdate = new StudentDto(id, nameUpdate, surnameUpdate,birthDateUpdate, midTermUpdate, finalTermUpdate, studentTypeMethod(), ERole.STUDENT);
        try {
            update(id, studentUpdate);
            System.out.println("Öğrenci başarıyla güncellendi.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /// Student Delete
    public void chooiseStudenDelete() {
        list();
        System.out.print("Silinecek Öğrenci ID: ");
        int deleteID = scanner.nextInt();
        try {
            delete(deleteID);
            System.out.println("Öğrenci başarıyla silindi.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////
    // Toplam Öğrenci Sayısı

    /// Student Sum Counter
    public void chooiseSumCounter() {
        System.out.println("Toplam Öğrenci Sayısı: " + studentDtoList.size());
    }

    // Rastgele Öğrenci
    /// Student Random
    public void chooiseRandomStudent() {
        if (!studentDtoList.isEmpty()) {
            StudentDto randomStudent = studentDtoList.get((int) (Math.random() * studentDtoList.size()));
            System.out.println("Rastgele Seçilen Öğrenci: " + randomStudent);
        } else {
            System.out.println("Sistemde öğrenci yok.");
        }
    }

    // Öğrenci Not Ortalaması Hesapla
    /// Öğrenci Not Ortalaması Hesapla
    public void chooiseStudentNoteAverage() {
        if (!studentDtoList.isEmpty()) {
            double avg = studentDtoList.stream()
                    .mapToDouble(StudentDto::getResultTerm)
                    .average()
                    .orElse(0.0);
            System.out.println("Öğrenci Not Ortalaması: " + avg);
        } else {
            System.out.println("Öğrenci listesi boş.");
        }
    }

    // En Yüksek veya En Düşük Not Alan Öğrenci

    /// En Yüksek & En Düşük Not Alan Öğrenci
    public void chooiseStudentNoteMinAndMax() {
        if (!studentDtoList.isEmpty()) {
            StudentDto maxStudent = studentDtoList.stream()
                    .max((s1, s2) -> Double.compare(s1.getResultTerm(), s2.getResultTerm()))
                    .orElse(null);

            StudentDto minStudent = studentDtoList.stream()
                    .min((s1, s2) -> Double.compare(s1.getResultTerm(), s2.getResultTerm()))
                    .orElse(null);

            System.out.println("En Yüksek Not Alan Öğrenci: " + maxStudent);
            System.out.println("En Düşük Not Alan Öğrenci: " + minStudent);
        } else {
            System.out.println("Öğrenci listesi boş.");
        }
    }

    // Öğrenci Sıralaması (Doğum günü)

    /// Öğrencileri Doğum Tarihine Göre Sırala
    public void chooiseStudentBirthdaySortedDate() {
        studentDtoList.stream()
                .sorted((s1, s2) -> s1.getBirthDate().compareTo(s2.getBirthDate()))
                .forEach(System.out::println);
    }

    // Geçen Öğrencilere
    private List<StudentDto> listStudentsWithStatus() {
        List<StudentDto> studentDtostatus = list();
        return studentDtostatus;
    }

    // Exit
    public void chooiseExit() {
        System.out.println("Sistemden çıkılıyor...");
        scanner.close();
        return;
    }

    // TEST
    public static void main(String[] args) {
        //StudentDao studentDao= new StudentDao();
        //studentDao.choose();
    }

} // end class


*/
/*


//Öğrenci yönetim sistemi
//Not Enum kısmını kontrol eteceğim değişmiyor



import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

*//*

*/
/**
 * TeacherDao generics Yapıda
 * Lambda Expression yapısı kullanılacak
 *
 *//*
*/
/*



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
                //Integer id, String name, String surname, LocalDate birthDay,Double midTerm, Double finalTerm, EStudentType estudentType
                new StudentDto(++studentCounter,studentDto.name(),studentDto.surname(), studentDto.birthDay(),studentDto.midTerm(),
                        studentDto.finalTerm(),studentDto.eStudentType()));
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

        *//*

*/
/*//*
/*
/filter sqldeki where gibi filtrelemeyi sağlar
        studentDtoList.stream()
                //burada studentlerdan oluşan listede aynı türü temsilen temp oluşturduk
                //ve büyük küçük ayrımı olmasın diye ignorladık ve name arandı
                .filter(temp -> temp.name().equalsIgnoreCase(name))
                .forEach(System.out::println);

         *//*
*/
/*

    }
    //FIND BY ID
    @Override
    public StudentDto findbyId(int id) {
        return null;
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
    //Student add
    public void chooiseStudentAdd(){
        Scanner scanner=new Scanner(System.in);
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


    }
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
                    chooiseStudentAdd();
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





*/

