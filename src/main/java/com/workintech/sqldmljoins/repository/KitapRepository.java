package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitapRepository extends JpaRepository<Kitap, Long> {

    //Dram ve Hikaye türündeki kitapları listeleyin. JOIN kullanmadan yapın.
    String QUESTION_1 = "SELECT *\n" +
            "FROM kitap, tur\n" +
            "WHERE kitap.turno = tur.turno\n" +
            "AND tur.ad IN ('Dram', 'Hikaye')";
    @Query(value = QUESTION_1, nativeQuery = true)
    List<Kitap> findBooks();


    String QUESTION_10 = "SELECT AVG(kitap.puan)\n" +
            "FROM kitap";
    @Query(value = QUESTION_10, nativeQuery = true)
    Double findAvgPointOfBooks();


}
