package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Marca;

public interface IMarcaService {
	public List<Marca> listMarcas();
	public void guardar(Marca marca);
	public Marca buscaxId(int id);
	public void eliminar(int id);
}
