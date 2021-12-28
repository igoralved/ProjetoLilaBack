package com.db.jogo.service;

import com.db.jogo.model.Categoria;
import com.db.jogo.repository.CategoriaRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaServicelmpl implements CategoriaService{

    private CategoriaRepository categoriaRepository;

    public void CategoriaRepository(
            CategoriaRepository categoriaRepository
    ){
        this.categoriaRepository = categoriaRepository;
  }

    @Override
    public Categoria findById(long id) throws DataAccessException {
       Optional<Categoria> categoria = this.categoriaRepository.findById(id);
       if(categoria == null) {
           return null;
       }
       return categoria.get();
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) throws DataAccessException  {
        return categoriaRepository.save(categoria);

    }

    public Iterable<Categoria> findAll() throws DataAccessException{
        return categoriaRepository.findAll();
    }

}
