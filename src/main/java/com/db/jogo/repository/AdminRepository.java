package com.db.jogo.repository;

import com.db.jogo.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends CrudRepository<Admin , UUID> {
}
