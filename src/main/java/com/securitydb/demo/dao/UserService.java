package com.securitydb.demo.dao;

import com.securitydb.demo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudRepository<UserEntity, Long>{

}
