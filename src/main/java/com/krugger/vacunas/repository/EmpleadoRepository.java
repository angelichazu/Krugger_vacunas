package com.krugger.vacunas.repository;

import com.krugger.vacunas.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

        @Query(value = "SELECT e " +
                "FROM Empleado e " +
                "INNER JOIN Vacuna v ON v.empleado=e " +
                "WHERE e.empEstadoVacuna=:estadoVacuna AND v.vacTipoVacuna.tpVacId=:tipoVacuna " +
                "AND v.vacFecha BETWEEN :fechaInicial AND :fechaFinal")
        List<Empleado> filtradoBusqueda(Date fechaInicial,
                                        Date fechaFinal,
                                        Boolean estadoVacuna,
                                        Integer tipoVacuna);

        @Query(value = "SELECT e " +
                "FROM Empleado e " +
                "INNER JOIN Vacuna v ON v.empleado=e " +
                "WHERE e.empEstadoVacuna=:estadoVacuna " +
                "AND v.vacFecha BETWEEN :fechaInicial AND :fechaFinal")
        List<Empleado> filtradoBusquedaSinTipoVacuna(Date fechaInicial,
                                        Date fechaFinal,
                                        Boolean estadoVacuna);
}