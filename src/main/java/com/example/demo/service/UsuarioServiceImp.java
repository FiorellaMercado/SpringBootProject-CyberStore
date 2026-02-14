package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
   
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<Usuario> listUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario buscaxId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {       
    	usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(int id) {
        usuarioRepository.deleteById(id);
    }
}
