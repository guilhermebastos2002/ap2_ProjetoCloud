package com.example.projetoCloud_ap2.controller;

import java.util.List;

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
import org.springframework.web.service.annotation.GetExchange;

import com.example.projetoCloud_ap2.model.Usuario;
import com.example.projetoCloud_ap2.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        return new ResponseEntity<>(service.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") int id) {
        Usuario response = service.getItem(id);

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
        Usuario response = service.createUsuario(usuario);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateTodo(@PathVariable("id") int id, @Valid @RequestBody Usuario novosDados) {

        Usuario usuarioASerAtualizado = service.getItem(id);

        if (usuarioASerAtualizado == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Usuario response = service.updateUsuario(id, usuarioASerAtualizado);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") int id) {
        if (service.getItem(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.deleteUsuario(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
