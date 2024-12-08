package com.klu.homestay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klu.homestay.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT U FROM User U WHERE U.email = ?1 AND U.password = ?2")
	public User checkUserLogin(String email, String password);
	Optional<User> findByEmail(String email);
	Optional<User> findByPhno(String phno);
}