package com.db.jogo.service;

import com.db.jogo.model.CategoriaDeCarta;
import com.db.jogo.repository.CategoriaRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaServicelmpl implements CategoriaService{

    private CategoriaRepository categoriaRepository;

    public void CategoriaRepository(
            CategoriaRepository categoriaRepository
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
