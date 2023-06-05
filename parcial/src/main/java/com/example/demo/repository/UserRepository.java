package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
	User findByUsernameOrEmail(String username, String email);
	User findByCode(UUID code);
}