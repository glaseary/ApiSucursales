package com.Perfulandia.ApiSucursales.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Perfulandia.ApiSucursales.dto.SucursalDTO;
import com.Perfulandia.ApiSucursales.models.Comuna;
import com.Perfulandia.ApiSucursales.models.Sucursal;
import com.Perfulandia.ApiSucursales.repository.SucursalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    // Inyectamos ComunaService para manejar la lógica de la comuna.
    @Autowired
    private ComunaService comunaService;

    public List<SucursalDTO> listarSucursales() {
        return sucursalRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public SucursalDTO obtenerSucursalPorId(Integer id) {
        Sucursal sucursal = findSucursalById(id);
        return toDTO(sucursal);
    }

    public SucursalDTO guardarSucursal(SucursalDTO dto) {
        // Usamos ComunaService para validar y obtener la entidad Comuna
        Comuna comuna = comunaService.findComunaById(dto.getComuna().getIdComuna());

        Sucursal sucursal = new Sucursal();
        sucursal.setNombreSucursal(dto.getNombreSucursal());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setEmail(dto.getEmail());
        sucursal.setHorarioApertura(dto.getHorarioApertura());
        sucursal.setHorarioCierre(dto.getHorarioCierre());
        sucursal.setFechaApertura(dto.getFechaApertura());
        sucursal.setComuna(comuna); // Asignamos la entidad encontrada

        Sucursal sucursalGuardada = sucursalRepository.save(sucursal);
        return toDTO(sucursalGuardada);
    }

    public SucursalDTO actualizarSucursal(Integer id, SucursalDTO dto) {
        Sucursal sucursalExistente = findSucursalById(id);
        Comuna comuna = comunaService.findComunaById(dto.getComuna().getIdComuna());

        sucursalExistente.setNombreSucursal(dto.getNombreSucursal());
        sucursalExistente.setTelefono(dto.getTelefono());
        sucursalExistente.setEmail(dto.getEmail());
        sucursalExistente.setHorarioApertura(dto.getHorarioApertura());
        sucursalExistente.setHorarioCierre(dto.getHorarioCierre());
        sucursalExistente.setFechaApertura(dto.getFechaApertura());
        sucursalExistente.setComuna(comuna);

        Sucursal sucursalActualizada = sucursalRepository.save(sucursalExistente);
        return toDTO(sucursalActualizada);
    }

    public void eliminarSucursal(Integer id) {
        if (!sucursalRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Sucursal no encontrada con ID: " + id);
        }
        sucursalRepository.deleteById(id);
    }

    // --- Métodos de Ayuda ---

    private Sucursal findSucursalById(Integer id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con ID: " + id));
    }

    private SucursalDTO toDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(sucursal.getIdSucursal());
        dto.setNombreSucursal(sucursal.getNombreSucursal());
        dto.setTelefono(sucursal.getTelefono());
        dto.setEmail(sucursal.getEmail());
        dto.setHorarioApertura(sucursal.getHorarioApertura());
        dto.setHorarioCierre(sucursal.getHorarioCierre());
        dto.setFechaApertura(sucursal.getFechaApertura());
        // Usamos el servicio de comuna para convertir la comuna a su DTO
        dto.setComuna(comunaService.toDTO(sucursal.getComuna()));
        return dto;
    }
}