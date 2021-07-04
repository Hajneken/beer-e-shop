package com.example.user.service.usercart.dao;

import com.example.user.service.usercart.model.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityDao extends CrudRepository<UserEntity, String> {
}
