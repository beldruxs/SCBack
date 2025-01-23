package com.belen.phishing.repository;

import com.belen.phishing.model.FakePageEntity;
import com.belen.phishing.model.FakeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FakeUserRepository extends JpaRepository<FakeUserEntity, Integer> {

    List<FakeUserEntity> findByFakePage(FakePageEntity fakePage);
}