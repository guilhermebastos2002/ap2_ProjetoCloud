package com.example.projetoCloud_ap2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projetoCloud_ap2.model.Cartao;
import com.example.projetoCloud_ap2.model.Transacao;

@Service
public class TransacaoService {
    private static List<Transacao> Transacoes = new ArrayList<>();

    public List<Transacao> getAllItems() {
        return TransacaoService.Transacoes;
    }

    public Transacao getItem(int id) {
        return findTransacao(id);
    }

    public Transacao findTransacao(int id) {
        Transacao response = null;

        for (Transacao transacao : Transacoes) {
            if (transacao.getId() == id) {
                response = transacao;
                break;
            }
        }
        return response;
    }

    public Transacao createTransacao(Transacao novaTransacao) throws Exception {

        Cartao cartaoAssociado = novaTransacao.getCartao();

        if (!TransacaoService.Transacoes.isEmpty()) {
            Transacao ultimaTransacao = TransacaoService.Transacoes.get(TransacaoService.Transacoes.size() - 1);


            if (transacoesSaoIguais(ultimaTransacao, novaTransacao)) {
                throw new Exception("Não é permitido fazer duas transações iguais seguidas.");
            }
        }

        TransacaoService.Transacoes.add(novaTransacao);
        return novaTransacao;

            if (cartaoAssociado == null) {
                throw new Exception("A transação deve estar associada a um cartão válido.");
            } 
            else if (novaTransacao.getValor() > cartaoAssociado.getLimite()) {
            throw new Exception("O valor da transação não pode exceder o limite disponível: limite insuficiente.");
        }





    }

    private boolean transacoesSaoIguais(Transacao t1, Transacao t2) {
        return t1.getDataTransacao().equals(t2.getDataTransacao()) &&
               t1.getValor().equals(t2.getValor()) &&
               t1.getComerciante().equals(t2.getComerciante()) &&
               t1.getCartao().equals(t2.getCartao());
    }

    public Transacao updateTransacao(int id, Transacao newTransacao) {
        Transacao transacaoASerAtualizada = findTransacao(id);

        if (transacaoASerAtualizada == null)
            return null;

        Transacoes.remove(transacaoASerAtualizada);

        transacaoASerAtualizada.setDataTransacao((newTransacao.getDataTransacao()));
        transacaoASerAtualizada.setValor(newTransacao.getValor());
        transacaoASerAtualizada.setComerciante(newTransacao.getComerciante());

        Transacoes.add(transacaoASerAtualizada);

        return transacaoASerAtualizada;
    }

    public void deleteTransacao(int id) {
        Transacao transacaoASerExcluida = findTransacao(id);

        if (transacaoASerExcluida == null)
            return;

        Transacoes.remove(transacaoASerExcluida);
    }

    


}
