package com.Perfulandia.ApiSucursales.dto;

import java.time.LocalDate; 

import lombok.Data;

@Data
public class SucursalDTO {
    private int idSucursal;
    private String nombreSucursal;
    private int telefono;
    private String email;
    private LocalDate horarioApertura;
    private LocalDate horarioCierre;
    private LocalDate fechaApertura;
    private ComunaDTO comuna;

}
