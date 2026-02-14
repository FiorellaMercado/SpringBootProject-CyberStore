package com.example.demo.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal total=BigDecimal.ZERO;

    private LocalDate fecha; 

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        if (detalleFacturas != null) {
            total = detalleFacturas.stream()
                                   .map(detalle -> detalle.getSubtotal() != null ? detalle.getSubtotal() : BigDecimal.ZERO)
                                   .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return total;
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getDetalleFacturas() {
        return detalleFacturas;
    }

    public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
        this.detalleFacturas = detalleFacturas;
    }

    // Método para agregar un detalle a la factura
    public void addDetalle(DetalleFactura detalle) {
        detalleFacturas.add(detalle);
        detalle.setFactura(this); 
    }
    
    @Override
    public String toString() {
        return "Factura{" +
               "id=" + id +
               ", total=" + total +
               ", fecha=" + fecha +
               ", cliente=" + (cliente != null ? cliente.getNombre() : "null") +
               '}';
    }
}
