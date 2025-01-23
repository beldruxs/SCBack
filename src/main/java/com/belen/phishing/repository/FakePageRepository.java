package com.belen.phishing.repository;

import com.belen.phishing.model.FakePageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FakePageRepository extends JpaRepository<FakePageEntity, Integer> {
    FakePageEntity findByNombre(String nombre);
}