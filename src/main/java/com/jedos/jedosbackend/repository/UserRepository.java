package com.jedos.jedosbackend.repository;

import com.jedos.jedosbackend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByMail(String mail);

    @Query("SELECT u.id FROM UserEntity u WHERE u.username = :username")
    Optional<Integer> findIdByUsername(String username);

    @Query("SELECT u.id FROM UserEntity u WHERE u.mail = ?1")
    Optional<Integer> findIdByMail(String mail);

    Boolean existsByUsername(String username);


}