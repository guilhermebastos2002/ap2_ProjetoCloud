package com.example.projetoCloud_ap2.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Campo Número é obrigatório.")
    private String numero;

    @Column
    @NotBlank(message = "Campo Nome no Cartão é obrigatório.")
    private String nomeNoCartao;

    @Column
    @NotNull(message = "Campo CVV é obrigatório.")
    private int cvv;

    @Column
    @NotNull(message = "Campo Data de Validade é obrigatório.")
    private LocalDate validade;

    @Column
    private Double limite = 1000.00;

    @Column
    private int numCartoes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "cartao")
    private List<Transacao> transacoes;

    

}