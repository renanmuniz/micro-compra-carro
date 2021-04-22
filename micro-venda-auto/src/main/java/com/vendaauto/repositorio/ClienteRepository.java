package com.vendaauto.repositorio;

import com.vendaauto.modelo.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNome(String nome);
    Page<Cliente> findByNome(String nome, Pageable paginacao);

    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
    Page<Cliente> findByCpfCnpj(String cpfCnpj, Pageable paginacao);

    Optional<Cliente> findByEmail(String email);
    Page<Cliente> findByEmail(String email, Pageable paginacao);

}
