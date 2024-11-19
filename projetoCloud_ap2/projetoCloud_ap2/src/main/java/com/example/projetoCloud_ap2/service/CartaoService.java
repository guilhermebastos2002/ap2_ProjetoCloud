package com.example.projetoCloud_ap2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projetoCloud_ap2.model.Cartao;

@Service
public class CartaoService {
    public static List<Cartao> Cartoes = new ArrayList<>();

    public List<Cartao> getAllItems() {
        return CartaoService.Cartoes;
    }

    public Cartao getItem(int id) {
        return findCartao(id);
    }

    public Cartao findCartao(int id) {
        Cartao response = null;

        for (Cartao cartao : Cartoes) {
            if (cartao.getId() == id) {
                response = cartao;
                break;
            }
        }
        return response;
    }

    public Cartao createCartao(Cartao item) throws Exception {
        
        String nomeNoCartao = item.getNomeNoCartao();
        int numCartoes = 0;
        for (Cartao cartao : Cartoes) {
            if (cartao.getNomeNoCartao() == nomeNoCartao) {
                numCartoes++;
            }
        }

        if (numCartoes >= 5) {
            throw new Exception("Número de cartões excedidas.");
        }

        CartaoService.Cartoes.add(item);
        return item;
    }

    public Cartao updateCartao(int id, Cartao novoCartao) {
        Cartao cartaoASerAtualizado = findCartao(id);

        if (cartaoASerAtualizado == null) 
            return null;

        Cartoes.remove(cartaoASerAtualizado);

        cartaoASerAtualizado.setNumero(novoCartao.getNumero());
        cartaoASerAtualizado.setNomeNoCartao(novoCartao.getNomeNoCartao());
        cartaoASerAtualizado.setCvv(novoCartao.getCvv());
        cartaoASerAtualizado.setValidade(novoCartao.getValidade());

        Cartoes.add(cartaoASerAtualizado);

        return cartaoASerAtualizado;
    }

    public void deleteCartao(int id) {
        Cartao cartaoASerExcluido = findCartao(id);

        if (cartaoASerExcluido == null)
        return;

        Cartoes.remove(cartaoASerExcluido);
    }

    
}

