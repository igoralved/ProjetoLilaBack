package com.db.jogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.jogo.model.Admin;

public interface AdminRepository extends JpaRepository<Admin , Long>{
	
	Admin findById(long id);

}
