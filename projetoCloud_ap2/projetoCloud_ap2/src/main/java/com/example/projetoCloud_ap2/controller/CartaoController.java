package com.example.projetoCloud_ap2.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoCloud_ap2.model.Cartao;
import com.example.projetoCloud_ap2.service.CartaoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService service;
    
    @PostMapping
    public ResponseEntity<Cartao> saveCartao(@RequestBody Cartao cartao) throws Exception {
        Cartao response = service.createCartao(cartao);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cartao>> getCartao() {
        return new ResponseEntity(service.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cartao> getCartaoById(@PathVariable("id") int id) {
        Cartao response = service.getItem(id);

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cartao> updateCartao(@PathVariable("id") int id, @Valid @RequestBody Cartao novosDados) {
        
        Cartao cartaoASerAtualizado = service.getItem(id);

        if (cartaoASerAtualizado == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        Cartao response = service.updateCartao(id, cartaoASerAtualizado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cartao> deleteCartao(@PathVariable("id") int id) {

        if (service.getItem(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.deleteCartao(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
