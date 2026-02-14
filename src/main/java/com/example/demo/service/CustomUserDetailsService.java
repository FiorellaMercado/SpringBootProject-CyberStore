
package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("Intentando cargar el usuario con nombre de usuario: " + username);
    	Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    	if (usuario == null) {
            System.out.println("Usuario no encontrado: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    	
    	 
        return org.springframework.security.core.userdetails.User.withUsername(usuario.getNombreUsuario())
                .password(usuario.getContrasena())  
                .roles("USER")
                .build();
    }
}

