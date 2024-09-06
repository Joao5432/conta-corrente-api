package com.fatecrl.contacorrente.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta-corrente")
public class ContaCorrenteController {

    @GetMapping("/obter")
    public String get() {
        return "conteúdo";
    }

}
