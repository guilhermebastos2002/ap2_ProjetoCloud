package com.example.projetoCloud_ap2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projetoCloud_ap2.model.Usuario;

@Service
public class UsuarioService {
    private static List<Usuario> Usuarios = new ArrayList();

    public List<Usuario> getAllItems() {
        return UsuarioService.Usuarios;
    }

    public Usuario getItem(int id) {
        return findUsuario(id);
    }

    public Usuario findUsuario(int id) {
        Usuario response = null;

        for (Usuario usuario : Usuarios) {
            if (usuario.getId() == id) {
                response = usuario;
                break;
            }
        }
        return response;
    }

    public Usuario createUsuario(Usuario item) {

        UsuarioService.Usuarios.add(item);
        return item;
    }

    public Usuario updateUsuario(int id, Usuario novoUsuario) {
        Usuario usuarioASerAtualizado = findUsuario(id);

        if (usuarioASerAtualizado == null)
            return null;

        Usuarios.remove(usuarioASerAtualizado);

        usuarioASerAtualizado.setNome(novoUsuario.getNome());
        usuarioASerAtualizado.setCpf(novoUsuario.getCpf());
        usuarioASerAtualizado.setDataNascimento(novoUsuario.getDataNascimento());
        usuarioASerAtualizado.setEmail(novoUsuario.getEmail());
        usuarioASerAtualizado.setEndereco(novoUsuario.getEndereco());
        usuarioASerAtualizado.setTelefone(novoUsuario.getTelefone());

        Usuarios.add(usuarioASerAtualizado);

        return usuarioASerAtualizado;
    }

    public void deleteUsuario(int id) {
        Usuario usuarioASerExcluido = findUsuario(id);

        if(usuarioASerExcluido == null)
        return;

        Usuarios.remove(usuarioASerExcluido);
    }

}
