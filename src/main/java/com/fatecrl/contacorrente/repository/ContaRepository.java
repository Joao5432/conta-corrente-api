package com.fatecrl.contacorrente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecrl.contacorrente.model.Conta;

@Repository
public interface ContaRepository 
         extends JpaRepository<Conta, Long>{

    public Optional<List<Conta>> findByTitular(String titular);
}
