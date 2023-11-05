package com.example.vaccinationbookingsystem.repository;

import com.example.vaccinationbookingsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query(value = "select * from doctor where age > :age", nativeQuery = true)
    List<Doctor> getByAgeGreaterThan(@Param("age") int age);
}
