package com.Perfulandia.ApiSucursales.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Perfulandia.ApiSucursales.dto.ComunaDTO;
import com.Perfulandia.ApiSucursales.models.Comuna;
import com.Perfulandia.ApiSucursales.repository.ComunaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    // Inyectamos ProvinciaService para que nos ayude con la lógica de Provincia
    @Autowired
    private ProvinciaService provinciaService;

    public List<ComunaDTO> listarComunas() {
        return comunaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ComunaDTO obtenerComunaPorId(Integer id) {
        Comuna comuna = findComunaById(id);
        return toDTO(comuna);
    }

    // NOTA: Tampoco se incluyen métodos para gestionar el CRUD de comunas,
    // pero se podrían añadir siguiendo el mismo patrón.

    // --- Métodos de Ayuda (públicos para que otros servicios los usen) ---

    public Comuna findComunaById(Integer id) {
        return comunaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comuna no encontrada con ID: " + id));
    }

    public ComunaDTO toDTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        // Usamos el servicio de provincia para convertir la provincia a su DTO
        dto.setProvincia(provinciaService.toDTO(comuna.getProvincia()));
        return dto;
    }
}