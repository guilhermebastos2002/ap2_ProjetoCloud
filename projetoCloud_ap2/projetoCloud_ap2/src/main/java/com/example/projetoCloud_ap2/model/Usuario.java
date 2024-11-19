package com.example.projetoCloud_ap2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;



@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "O campo Nome é obrigatório.")
    private String nome;

    @Column
    @NotBlank(message = "O campo CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "O CPF deve seguir o formato XXX.XXX.XXX-XX.")
    private String cpf;

    @Column
    @NotNull(message = "O campo Data de Nascimento é obrigatório.")
    private LocalDate dataNascimento;

    @Column
    @NotBlank(message = "O campo E-mail é obrigatório.")
    @Email(message = "Campo E-mail não está em formato adequado.")
    private String email;

    @Column
    @NotBlank(message = "Campo Endereço é obrigatório.")
    private String endereco;

    @Column
    @NotBlank(message = "Campo Telefone é obrigatório.")
    private String telefone;

    @OneToMany(mappedBy = "usuario")
    private List<Cartao> cartoes;

    public void associarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }
}
