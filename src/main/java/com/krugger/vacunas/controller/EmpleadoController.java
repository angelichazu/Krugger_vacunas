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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        GenericResponse<List<Empleado>> empleadoGR = new GenericResponse<>();
        try {
            empleadoGR.setObject(empleadoService.listarEmpleados());
            empleadoGR.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            empleadoGR.setObject(null);
            empleadoGR.setEstado(EstadoEnum.ERROR);
            empleadoGR.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(empleadoGR, HttpStatus.OK);
    }

}
