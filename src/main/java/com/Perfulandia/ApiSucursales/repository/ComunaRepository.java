package com.Perfulandia.ApiSucursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Perfulandia.ApiSucursales.models.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

}
