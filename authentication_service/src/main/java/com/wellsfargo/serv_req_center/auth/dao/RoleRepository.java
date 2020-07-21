package com.wellsfargo.serv_req_center.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.serv_req_center.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
