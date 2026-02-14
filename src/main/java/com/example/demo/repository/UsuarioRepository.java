package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
