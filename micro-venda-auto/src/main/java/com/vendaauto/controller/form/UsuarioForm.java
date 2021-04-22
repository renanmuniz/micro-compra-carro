package com.vendaauto.controller.form;

import com.vendaauto.modelo.Usuario;
import com.vendaauto.repositorio.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioForm {
    private String usuario;
    private String senha;

    public UsuarioForm() {
    }

    public UsuarioForm(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converter() {
        String senhaCript = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.usuario,senhaCript);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuario.setNome(this.usuario);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        return usuario;
    }
}
