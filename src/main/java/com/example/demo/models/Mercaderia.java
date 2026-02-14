package com.example.demo.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mercaderias")
public class Mercaderia  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descripcion;
	private int precio_cmp;
	private int precio_vta;
	private int cantidad;
	
	@Lob
    private byte[] imagen;

    
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "idmarca")
	private Marca marca;

	public Mercaderia() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio_cmp() {
		return precio_cmp;
	}

	public void setPrecio_cmp(int precio_cmp) {
		this.precio_cmp = precio_cmp;
	}

	public int getPrecio_vta() {
		return precio_vta;
	}

	public void setPrecio_vta(int precio_vta) {
		this.precio_vta = precio_vta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
