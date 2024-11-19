package com.example.projetoCloud_ap2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDate dataTransacao;

    @Column
    private Double valor;

    @Column
    public String comerciante;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

}
 