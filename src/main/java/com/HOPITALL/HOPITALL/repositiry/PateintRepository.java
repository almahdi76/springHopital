package com.HOPITALL.HOPITALL.repositiry;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HOPITALL.HOPITALL.entities.Patient;

public interface PateintRepository extends JpaRepository<Patient,Long> {
    // Page <Patient>PateintRepository findByMalade(boolean m,Pageable pageable);
    List <Patient> findByMalade(boolean m);
    // List<Patient> findByDateNissanceBetweenAndMaladIsTrueOrNameLike(Date d1,Date d2,String mc);
    // @Query("select p from Patient p where p.dateNissance between :x and :y or nom like :z")
    // List<Patient> chercherPatients(@Param("x")Date d1,@Param("y")Date d2,@Param("z")String nom);
    
    Page <Patient> findByNameContains(String kw,Pageable pageable);
}
