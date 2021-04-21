package com.cadcliente.controller;

import com.cadcliente.controller.dto.UsuarioDto;
import com.cadcliente.controller.form.UsuarioForm;
import com.cadcliente.modelo.Usuario;
import com.cadcliente.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<UsuarioDto> listar(@RequestParam(required = false) String nomeUsuario,
                                   @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        Page<Usuario> usuarios;
        if (nomeUsuario == null) {
            usuarios = usuarioRepository.findAll(paginacao);
        } else {
            usuarios = usuarioRepository.findByNome(nomeUsuario, paginacao);
        }
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form,
                                                UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            return ResponseEntity.ok(new UsuarioDto(usuarioOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            Usuario usuario = form.atualizar(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDto(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}