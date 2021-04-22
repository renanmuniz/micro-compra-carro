package com.vendaauto.controller.form;

import com.vendaauto.modelo.Cliente;
import com.vendaauto.repositorio.ClienteRepository;

public class ClienteForm {
    private String nome;
    private Long cpfCnpj;
    private String email;

    public ClienteForm() {
    }

    public ClienteForm(String nome, Long cpfCnpj, String email) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
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

    public Cliente converter() {
        return new Cliente(this.nome,this.cpfCnpj,this.email);
    }

    public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
        Cliente cliente = clienteRepository.getOne(id);
        cliente.setNome(this.nome);
        cliente.setCpfCnpj(this.cpfCnpj);
        cliente.setEmail(this.email);
        return cliente;
    }
}
