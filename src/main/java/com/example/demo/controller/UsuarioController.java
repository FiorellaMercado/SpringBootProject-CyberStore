package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.models.Usuario;
import com.example.demo.service.IUsuarioService;

@Controller
@RequestMapping("/pages/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/")
    public String listaUsuarios(Model model) {
        List<Usuario> listarUsuarios = usuarioService.listUsuarios();
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuarios", listarUsuarios);
        return "/pages/usuarios/usuarios_lst";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("usuario", usuario);
        return "/pages/usuarios/usuarios_add";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/pages/usuarios/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioService.buscaxId(id);
        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("usuario", usuario);
        return "/pages/usuarios/usuarios_add";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id) {
        usuarioService.eliminar(id);
        return "redirect:/pages/usuarios/";
    }
}
