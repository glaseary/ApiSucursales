package com.Perfulandia.ApiSucursales.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "COMUNA")
@Data
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comuna")
    private Integer idComuna;

    @Column(name = "nombre_comuna", nullable = false, length = 40)
    private String nombreComuna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVINCIA_id_provincia", nullable = false)
    private Provincia provincia;
}
