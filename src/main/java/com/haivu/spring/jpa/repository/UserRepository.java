package com.haivu.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haivu.spring.jpa.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
