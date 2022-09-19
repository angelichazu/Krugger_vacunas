package com.krugger.vacunas.modelDto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class EmpleadoDto {
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaNac;
    private String direccion;
    private String telefono;
    private Boolean estadoVacuna;
    private List<VacunaDto> vacunaList;
}