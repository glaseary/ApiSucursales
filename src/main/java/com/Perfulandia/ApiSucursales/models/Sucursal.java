package com.Perfulandia.ApiSucursales.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sucursal")
@Data
public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    @Column(nullable = false, length = 40)
    private String nombreSucursal;

    @Column(nullable = false,length = 9)
    private int telefono;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, name = "horario_apertura")
    private LocalDate horarioApertura;

    @Column(nullable = false, name = "horario_cierre")
    private LocalDate horarioCierre;

    @Column(nullable = false, name = "fecha_apertura")
    private LocalDate fechaApertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMUNA_id_comuna", nullable = false)
    private Comuna comuna;
}
