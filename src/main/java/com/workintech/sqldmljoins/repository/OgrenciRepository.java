package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT *\n" +
            "FROM ogrenci o\n" +
            "LEFT JOIN islem i\n" +
            "ON o.ogrno = i.ogrno";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT *\n" +
            "FROM ogrenci o\n" +
            "LEFT JOIN islem i\n" +
            "ON o.ogrno = i.ogrno\n" +
            "WHERE i.ogrno IS NULL;";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT o.sinif, count(i.kitapno) \n" +
            "FROM ogrenci o \n" +
            "LEFT JOIN islem i\n" +
            "ON o.ogrno = i.ogrno\n" +
            "WHERE o.sinif IN ('10A', '10B')\n" +
            "GROUP BY o.sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT count(o.ogrno) \n" +
            "FROM ogrenci o";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT count(distinct o.ad) \n" +
            "FROM ogrenci o ";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT o.ad, count(o.ad) \n" +
            "FROM ogrenci o \n" +
            "GROUP BY o.ad\n";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    // Her sınıftaki öğrenci sayısını bulunuz.
    String QUESTION_8 = "SELECT o.sinif, count(o.ogrno) \n" +
            "FROM ogrenci o \n" +
            "GROUP BY o.sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    // Her öğrencinin ad soyad karşılığında okuduğu kitabı getiriniz.
    String QUESTION_9 = "SELECT o.ad, o.soyad, count(i.kitapno)\n" +
            "from ogrenci o\n" +
            "inner join islem i\n" +
            "on i.ogrno = o.ogrno\n" +
            "group by o.ad, o.soyad;\n";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
