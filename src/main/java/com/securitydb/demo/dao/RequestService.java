package com.securitydb.demo.dao;

import com.securitydb.demo.model.UserEntity;
import com.securitydb.demo.model.UserRequests;
import org.springframework.data.repository.CrudRepository;

public interface RequestService extends CrudRepository<UserRequests, Long> {
}
