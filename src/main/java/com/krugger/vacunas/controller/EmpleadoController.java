package com.krugger.vacunas.controller;

import com.krugger.vacunas.entity.Empleado;
import com.krugger.vacunas.modelDto.EmpleadoDto;
import com.krugger.vacunas.services.EmpleadoService;
import com.krugger.vacunas.util.EstadoEnum;
import com.krugger.vacunas.util.GenericResponse;
import com.krugger.vacunas.util.UtilsApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/empleado")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Empleado", description = "Endpoint de Empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @Operation(
            summary = "Crear Empleado",
            description = "Api para crear un empleado"
    )
    @PostMapping(value = "/crearEmpleado")
    public ResponseEntity<GenericResponse> crearEmpleado(@RequestBody EmpleadoDto empleado) {
        GenericResponse<Empleado> empleadoReturn = new GenericResponse<>();
        try {
            empleadoReturn.setObject(empleadoService.crearEmpleado(empleado));
            empleadoReturn.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            empleadoReturn.setObject(null);
            empleadoReturn.setEstado(EstadoEnum.ERROR);
            empleadoReturn.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(empleadoReturn, HttpStatus.OK);
    }

    @Operation(
            summary = "Listar todos los Empleados",
            description = "Api para listar todos los empleados registrados"
    )
    @GetMapping(value = "/listarEmpleados")
    public ResponseEntity<GenericResponse> listarEmpleados() {
        GenericResponse<List<Empleado>> empleadoReturn = new GenericResponse<>();
        try {
            empleadoReturn.setObject(empleadoService.listarEmpleados());
            empleadoReturn.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            empleadoReturn.setObject(null);
            empleadoReturn.setEstado(EstadoEnum.ERROR);
            empleadoReturn.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(empleadoReturn, HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar la informacion del empleado",
            description = "Api para actualizar la informacion de un empleado"
    )
    @PutMapping("/actualizarEmpleado/{id}")
    public ResponseEntity<GenericResponse> actualizarEmpleado(@PathVariable(value = "id") Integer id,
                                                              @RequestBody EmpleadoDto empleado) {
        GenericResponse<Empleado> empleadoReturn = new GenericResponse<>();
        try {
            empleadoReturn.setObject(empleadoService.actualizarEmpleado(id, empleado));
            empleadoReturn.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            empleadoReturn.setObject(null);
            empleadoReturn.setEstado(EstadoEnum.ERROR);
            empleadoReturn.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(empleadoReturn, HttpStatus.OK);
    }

    @Operation(
            summary = "Encontrar Empleado por parametro de busqueda",
            description = "Se puede filtrar el empleado por tipo de vacuna, si se encuentra vacunado o por rango de fecha de la vacunas"
    )
    @PostMapping("/filtrarEmpleados")
    public ResponseEntity<GenericResponse> filtrarEmpleados(
            @RequestParam(value = "fechaInicial") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaInicial,
            @RequestParam(value = "fechaFinal") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaFinal,
            @RequestParam(value = "estadoVacuna", defaultValue = "true", required = false) Boolean estadoVacuna,
            @RequestParam(value = "tipoVacuna", required = false) Integer tipoVacuna) {
        GenericResponse<List<Empleado>> empleadoReturn = new GenericResponse<>();
        try {
            empleadoReturn.setObject(empleadoService.busquedaFiltros(fechaInicial, fechaFinal, estadoVacuna, tipoVacuna));
            empleadoReturn.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            empleadoReturn.setObject(null);
            empleadoReturn.setEstado(EstadoEnum.ERROR);
            empleadoReturn.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(empleadoReturn, HttpStatus.OK);
    }

    @Operation(
            summary = "Encontrar la informacion de un empleado por id",
            description = "Se puede filtrar el empleado por id"
    )
    @GetMapping("/infoEmpleado/{id}")
    public ResponseEntity<GenericResponse> filtrarEmpleadoById(@PathVariable(value = "id") Integer id) {
        GenericResponse<Empleado> empleadoReturn = new GenericResponse<>();
        try {
            empleadoReturn.setObject(empleadoService.busquedaPorId(id));
            empleadoReturn.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            empleadoReturn.setObject(null);
            empleadoReturn.setEstado(EstadoEnum.ERROR);
            empleadoReturn.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(empleadoReturn, HttpStatus.OK);
    }

}
