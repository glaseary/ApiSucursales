package com.Perfulandia.ApiSucursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Perfulandia.ApiSucursales.models.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{

}
