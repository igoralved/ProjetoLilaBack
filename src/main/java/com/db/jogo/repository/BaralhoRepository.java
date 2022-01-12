package com.db.jogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.db.jogo.model.Baralho;

public interface BaralhoRepository extends JpaRepository<Baralho, String> {

}
