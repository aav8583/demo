package com.securitydb.demo.repository;

import com.securitydb.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByNameOfUser(String name);
}
