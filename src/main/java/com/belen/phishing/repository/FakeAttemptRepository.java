package com.belen.phishing.repository;

import com.belen.phishing.model.FakeAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FakeAttemptRepository extends JpaRepository<FakeAttemptEntity, Integer> {
    @Query("SELECT f FROM FakeAttemptEntity f WHERE f.user.username = :username")
    Optional<FakeAttemptEntity> findByUserUsername(@Param("username") String username);

    void deleteByUserUsername(String username);

}