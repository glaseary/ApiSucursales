package com.Perfulandia.ApiSucursales.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Perfulandia.ApiSucursales.dto.ProvinciaDTO;
import com.Perfulandia.ApiSucursales.models.Provincia;
import com.Perfulandia.ApiSucursales.repository.ProvinciaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<ProvinciaDTO> listarProvincias() {
        return provinciaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProvinciaDTO obtenerProvinciaPorId(Integer id) {
        Provincia provincia = findProvinciaById(id);
        return toDTO(provincia);
    }
    
    // NOTA: No se incluyen métodos para crear, actualizar o eliminar provincias
    // ya que se asume que son datos maestros que no cambian frecuentemente.

    // --- Métodos de Ayuda 
    
    public Provincia findProvinciaById(Integer id) {
        return provinciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provincia no encontrada con ID: " + id));
    }
    
    public ProvinciaDTO toDTO(Provincia provincia) {
        ProvinciaDTO dto = new ProvinciaDTO();
        dto.setIdProvincia(provincia.getIdProvincia());
        dto.setNombreProvincia(provincia.getNombreProvincia());
        return dto;
    }
}