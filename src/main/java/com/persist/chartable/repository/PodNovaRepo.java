package com.persist.chartable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.persist.chartable.entity.PodNovaSignUpEntity;

public interface PodNovaRepo  extends JpaRepository<PodNovaSignUpEntity, String> {

    @Query("SELECT u FROM PodNovaSignUpEntity u WHERE (u.email = :email AND u.password = :password) OR u.email = :email OR u.password = :password")
    List<PodNovaSignUpEntity> findByEmailANDPassword(String email, String password);

}
