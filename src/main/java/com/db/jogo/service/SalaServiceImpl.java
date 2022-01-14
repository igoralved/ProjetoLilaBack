package com.db.jogo.service;

import java.util.Optional;

import com.db.jogo.model.Sala;
import com.db.jogo.repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SalaServiceImpl implements SalaService {

    private SalaRepository salaRepository;

    @Autowired
    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }


    @Override
    public Optional<Sala> findSalaByHash(String hash) throws DataAccessException {
        return salaRepository.findSalaByHash(hash);
    }

    @Override
    public Sala saveSala(Sala sala) throws DataAccessException {
        return salaRepository.save(sala);
    }

    /**
     * Este método executa a jogada
     * @param sala - essa sala é o estado inicial do jogo
     * @return retorna um novo estado do jogo após a jogada computada
     * @throws DataAccessException
     */
    @Override
    public Sala jogada(Sala sala) throws DataAccessException {
        /*TODO: AQUI VAI A LÓGICA DO JOGO*/
        return sala;
    }
}