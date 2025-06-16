package com.Perfulandia.ApiSucursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Perfulandia.ApiSucursales.models.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>{

}
