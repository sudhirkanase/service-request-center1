package com.wellsfargo.serv_req_center.auth.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.serv_req_center.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	 Optional<User> findByUserName(String username);
}
