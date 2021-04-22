package com.vendaauto.controller.dto;

import com.vendaauto.modelo.Cliente;
import org.springframework.data.domain.Page;

public class ClienteDto {
    private Long id;
    private String nome;
    private Long cpfCnpj;
    private String email;

    public ClienteDto(Cliente cliente) {
        this.id = cliente.id;
        this.nome = cliente.nome;
        this.cpfCnpj = cliente.cpfCnpj;
        this.email = cliente.email;
    }

    public static Page<ClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Long cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
