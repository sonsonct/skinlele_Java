package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Models.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	List<Users> findByNameContaining(@Param("nameuser") String nameuser);
	Users findByUsername(String username);
}
