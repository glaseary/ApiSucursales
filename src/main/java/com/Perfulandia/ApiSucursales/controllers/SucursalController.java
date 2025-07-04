package com.Perfulandia.ApiSucursales.controllers;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Perfulandia.ApiSucursales.dto.SucursalDTO;
import com.Perfulandia.ApiSucursales.services.SucursalService;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("/")
    public ResponseEntity<List<SucursalDTO>> listar() {
        return ResponseEntity.ok(sucursalService.listarSucursales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(sucursalService.obtenerSucursalPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crear(@RequestBody SucursalDTO dto) {
        return new ResponseEntity<>(sucursalService.guardarSucursal(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizar(@PathVariable Integer id, @RequestBody SucursalDTO dto) {
        try {
            return ResponseEntity.ok(sucursalService.actualizarSucursal(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            sucursalService.eliminarSucursal(id);
            return ResponseEntity.noContent().build(); // HTTP 204
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // ======================================================
    // MÉTODOS HATEOAS
    // ======================================================

    /**
     * Obtiene una sucursal por su ID y le añade enlaces HATEOAS.
     * Los enlaces apuntan a las rutas del API Gateway.
     */
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<SucursalDTO> obtenerHATEOAS(@PathVariable Integer id) {
        try {
            SucursalDTO dto = sucursalService.obtenerSucursalPorId(id);

            // URL base del Gateway para las sucursales
            String gatewayUrl = "http://localhost:8888/api/proxy/sucursales";

            // --- Enlaces apuntando exclusivamente al API Gateway ---

            // Link a sí mismo (self) - Asume que el DTO tiene un método getIdSucursal()
            dto.add(Link.of(gatewayUrl + "/hateoas/" + dto.getIdSucursal()).withSelfRel());

            // Link a la lista de todas las sucursales
            dto.add(Link.of(gatewayUrl + "/hateoas").withRel("todas-las-sucursales"));

            // Link para actualizar (PUT)
            dto.add(Link.of(gatewayUrl + "/" + dto.getIdSucursal()).withRel("actualizar").withType("PUT"));

            // Link para eliminar (DELETE)
            dto.add(Link.of(gatewayUrl + "/" + dto.getIdSucursal()).withRel("eliminar").withType("DELETE"));

            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene todas las sucursales y añade enlaces HATEOAS a cada una.
     */
    @GetMapping("/hateoas")
    public ResponseEntity<List<SucursalDTO>> listarHATEOAS() {
        List<SucursalDTO> sucursales = sucursalService.listarSucursales();
        String gatewayUrl = "http://localhost:8888/api/proxy/sucursales";

        for (SucursalDTO dto : sucursales) {
            // Link a los detalles de esta sucursal (self)
            dto.add(Link.of(gatewayUrl + "/hateoas/" + dto.getIdSucursal()).withSelfRel());

            // Link para crear una nueva sucursal (POST)
            dto.add(Link.of(gatewayUrl).withRel("crear-sucursal").withType("POST"));
        }

        return ResponseEntity.ok(sucursales);
    }
}