package com.mobileApp.mobileApp.repository;

import com.mobileApp.mobileApp.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity,Long> {
    List<AddressEntity> findByUserId(Long userId);
}
