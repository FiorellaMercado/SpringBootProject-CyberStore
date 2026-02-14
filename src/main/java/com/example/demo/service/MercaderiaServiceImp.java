package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Mercaderia;
import com.example.demo.repository.MercaderiaRepository;

@Service
public class MercaderiaServiceImp implements IMercaderiaService{
  
	@Autowired
    MercaderiaRepository mercaderiaRepository;
	
	@Override
	public List<Mercaderia> listMercaderias() {
		return  (List<Mercaderia>) mercaderiaRepository.findAll();
	}

	
	@Override
	public Mercaderia buscaxId(int id) {
		return mercaderiaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		mercaderiaRepository.deleteById(id);
		
	}


	@Override
	public void guardar(Mercaderia mercaderia) {
		mercaderiaRepository.save(mercaderia);
		
	}

}
