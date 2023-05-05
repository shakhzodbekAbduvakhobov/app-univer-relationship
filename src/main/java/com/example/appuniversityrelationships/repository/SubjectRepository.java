package com.example.appuniversityrelationships.repository;

import com.example.appuniversityrelationships.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
