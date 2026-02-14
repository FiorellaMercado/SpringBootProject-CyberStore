package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Mercaderia;

public interface IMercaderiaService {
	public List<Mercaderia> listMercaderias();
	public void guardar(Mercaderia mercaderia);
	public Mercaderia buscaxId(int id);
	public void eliminar(int id);
}
