package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.entities.Token;
import com.example.demo.entities.User;

public interface TokenRepository 
extends ListCrudRepository<Token, UUID>{ 

List<Token> findByUserAndActive(User user, Boolean active);

}
