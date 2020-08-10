package com.wellsfargo.srca.auth.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.srca.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	 Optional<User> findByUserName(String username);
}
