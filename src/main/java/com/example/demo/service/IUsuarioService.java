package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Usuario;

public interface IUsuarioService {
    List<Usuario> listUsuarios();
    Usuario buscaxId(int id);
    void guardar(Usuario usuario);
    void eliminar(int id);
}
