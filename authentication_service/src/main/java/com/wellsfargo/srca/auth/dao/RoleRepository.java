package com.wellsfargo.srca.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.srca.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
