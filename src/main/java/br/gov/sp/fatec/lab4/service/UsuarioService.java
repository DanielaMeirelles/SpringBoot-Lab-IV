package br.gov.sp.fatec.lab4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.lab4.entity.Usuario;
import br.gov.sp.fatec.lab4.repository.UsusarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsusarioRepository repo;

    public List<Usuario> listarTodos() {
        return repo.findAll();
    }
    public Usuario novo(Usuario usuario) {
        if (usuario == null ||
        usuario.getNome() == null ||
        usuario.getNome().isBlank() ||
        usuario.getSenha() == null ||
        usuario.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos!");
        }
        repo.save(usuario);
        return usuario;
    }
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOp = repo.findById(id);
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado!");
        }
        return usuarioOp.get();
    }
}
