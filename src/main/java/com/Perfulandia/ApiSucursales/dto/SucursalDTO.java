package com.Perfulandia.ApiSucursales.dto;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class SucursalDTO extends RepresentationModel{
    private int idSucursal;
    private String nombreSucursal;
    private int telefono;
    private String email;
    private LocalDate horarioApertura;
    private LocalDate horarioCierre;
    private LocalDate fechaApertura;
    private ComunaDTO comuna;

}
