package com.example.sensorapi.repository;

import com.example.sensorapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
