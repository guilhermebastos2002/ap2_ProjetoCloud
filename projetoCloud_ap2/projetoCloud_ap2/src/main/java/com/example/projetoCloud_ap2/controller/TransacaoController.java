package com.example.projetoCloud_ap2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired TransacaoService service;

    @GetMapping
    public ResponseEntity<List<Todo>> getTransacao() {
        return new ResponseEntity(service.getAllItems(), HttpsStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable("id") int id) {
        Transacao response = service.getItem(id);

        if (response == null)
            return new ResponseEntity<>(HttpsStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpsStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Transacao> updateTransacao(@PathVariable("id") int id, @Valid @RequestBody Transacao novosDados) {
        Transacao transacaoASerAtualizada = service.getItem(id);

        if (transacaoASerAtualizada == null)
            return new ResponseEntity<>(HttpsStatus.NOT_FOUND);

        Transacao response = service.updateTransacao(id, transacaoASerAtualizada);

        return new ResponseEntity<>(response, HttpsStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Transacao> deleteTransacao(@PathVariable("id") int id) {
        if (service.getItem(id) == null)
            return new ResponseEntity<>(HttpsStatus.NOT_FOUND);

        service.deleteTransacao(id);

        return new ResponseEntity<>(HttpsStatus.NO_CONTENT);
    }

    
    
}
