package com.datta.microservices.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.datta.microservices.userservice.model.User;

@Repository
@RestResource
public interface UserRepository extends JpaRepository<User, Long> {

}