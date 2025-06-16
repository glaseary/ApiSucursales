package com.Perfulandia.ApiSucursales.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROVINCIA")
@Data
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia")
    private Integer idProvincia;

    @Column(name = "nombre_provincia", nullable = false, length = 30)
    private String nombreProvincia;

}
