package com.krugger.vacunas.repository;

import com.krugger.vacunas.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByRolNombre(String rolNombre);

}