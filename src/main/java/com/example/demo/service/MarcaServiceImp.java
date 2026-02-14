package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Marca;
import com.example.demo.repository.MarcaRepository;

@Service
public class MarcaServiceImp implements IMarcaService {
    
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public List<Marca> listMarcas() {
		return  (List<Marca>) marcaRepository.findAll();
	}

	@Override
	public void guardar(Marca marca) {
		marcaRepository.save(marca);	
		
	}

	@Override
	public Marca buscaxId(int id) {
		return marcaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		marcaRepository.deleteById(id);
		
	}

}
