package com.krugger.vacunas.repository;

import com.krugger.vacunas.entity.TipoVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVacunaRepository extends JpaRepository<TipoVacuna, Integer> {
}
