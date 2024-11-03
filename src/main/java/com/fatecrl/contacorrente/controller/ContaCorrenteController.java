package com.fatecrl.contacorrente.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.contacorrente.model.Conta;
import com.fatecrl.contacorrente.service.ContaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/conta-corrente")
public class ContaCorrenteController {

    @Autowired
    private ContaService contaService;

    //Para executar: http://localhost:8090/api/conta-corrente
    @GetMapping
    public ResponseEntity<List<Conta>> getAll(@RequestParam(required = false) String titular){
        if (titular != null && !titular.isEmpty()){
            List<Conta> contas = contaService.findByTitular(titular).orElse(null);
            if (contas != null && contas.size() > 0){
                return ResponseEntity.ok(contas);
            }
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(contaService.findAll());
        }
        
    }
    
    //Para executar: http://localhost:8090/api/conta-corrente/1 (ok)
    //Para executar: http://localhost:8090/api/conta-corrente/999 (not found)
    @GetMapping("/{id}")
    public ResponseEntity<Conta> get(@PathVariable("id") Long id){
        Conta conta = contaService.find(id).orElse(null);
        if (conta != null){
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }    

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody @NonNull Conta conta){
        contaService.create(conta);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(conta.getId())
                            .toUri();
        return ResponseEntity.created(location).body(conta);
    }

    @PutMapping
    public ResponseEntity<Conta> update(@RequestBody @NonNull Conta conta){
        if (contaService.update(conta)){
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Conta> delete(@PathVariable("id") @NonNull Long id){
        if (contaService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
