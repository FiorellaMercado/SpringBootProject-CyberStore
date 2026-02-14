package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Categoria;

public interface ICategoriaService {
	public List<Categoria> listCategorias();
	public void guardar(Categoria categoria);
	public Categoria buscaxId(int id);
	public void eliminar(int id);
		
}
