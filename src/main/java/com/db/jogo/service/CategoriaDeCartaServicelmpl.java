package com.db.jogo.service;

import com.db.jogo.model.CategoriaDeCarta;
import com.db.jogo.repository.CategoriaDeCartaRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaDeCartaServicelmpl implements CategoriaDeCartaService {

    private CategoriaDeCartaRepository categoriaRepository;

    public void CategoriaRepository(
            CategoriaDeCartaRepository categoriaRepository
    ){
        this.categoriaRepository = categoriaRepository;
  }

    @Override
    public CategoriaDeCarta findById(UUID id) throws DataAccessException {
       Optional<CategoriaDeCarta> categoria = this.categoriaRepository.findById(id);
       if(categoria == null) {
           return null;
       }
       return categoria.get();
    }

    @Override
    public CategoriaDeCarta saveCategoria(CategoriaDeCarta categoria) throws DataAccessException  {
        return categoriaRepository.save(categoria);

    }

    public Iterable<CategoriaDeCarta> findAll() throws DataAccessException{
        return categoriaRepository.findAll();
    }

}
