package com.krugger.vacunas.services;

import com.krugger.vacunas.entity.Empleado;
import com.krugger.vacunas.entity.TipoVacuna;
import com.krugger.vacunas.entity.Usuario;
import com.krugger.vacunas.entity.Vacuna;
import com.krugger.vacunas.modelDto.EmpleadoDto;
import com.krugger.vacunas.modelDto.VacunaDto;
import com.krugger.vacunas.repository.EmpleadoRepository;
import com.krugger.vacunas.repository.RolRepository;
import com.krugger.vacunas.repository.TipoVacunaRepository;
import com.krugger.vacunas.util.UtilsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    TipoVacunaRepository tipoVacunaRepository;

    public Empleado crearEmpleado(EmpleadoDto empleado) throws Exception{

        if (!UtilsApi.validadorDeCedula(empleado.getCorreo())) {
            throw new Exception("Cédula no válida");
        }

        Usuario usuario = new Usuario();
        usuario.setUserUsername(empleado.getCorreo());
        usuario.setUserPassword(passwordEncoder.encode(empleado.getApellido().split(" ")[0] + empleado.getCedula()));
        usuario.setRol(rolRepository.findByRolNombre("USER"));

        Empleado empleadoGuardar = new Empleado();

        empleadoGuardar.setEmpNombre(empleado.getNombre() != null ? empleado.getNombre() : null);
        empleadoGuardar.setEmpApellido(empleado.getApellido() != null ? empleado.getApellido() : null);
        empleadoGuardar.setEmpCedula(empleado.getCedula() != null ? empleado.getCedula() : null);
        empleadoGuardar.setEmpCorreo(empleado.getCorreo() != null ? empleado.getCorreo() : null);

        empleadoGuardar.setUsuario(usuario);
        return empleadoRepository.save(empleadoGuardar);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Empleado actualizarEmpleado(Integer id, EmpleadoDto empleadoDto) throws Exception {

        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);

        if (!empleadoOptional.isPresent()) {
            throw new Exception("El Empleado que se intenta modificar no se encuentra registrado");
        }

        Empleado empleado = empleadoOptional.get();

        if (empleadoDto.getEstadoVacuna() == true && (empleadoDto.getVacunaList() == null || empleadoDto.getVacunaList().isEmpty())) {
            throw new Exception("Debe Ingresar Información sobre sus vacunas");
        }

        if (!empleado.getEmpCedula().equals(empleadoDto.getCedula())) {
                throw new Exception("La cédula es única por usuario, si desea modificarla contacte con un administrador");
        }

        if (empleadoDto.getCorreo() != null && !empleado.getEmpCorreo().equals(empleadoDto.getCorreo())) {
            throw new Exception("El correo es único por usuario, si desea modificarlo contacte con un administrador");
        }

        empleado.setEmpNombre(empleadoDto.getNombre() != null ?  empleadoDto.getNombre() : empleado.getEmpNombre());
        empleado.setEmpApellido(empleadoDto.getApellido() != null ? empleadoDto.getApellido() : empleado.getEmpApellido());
        empleado.setEmpFechaNacimiento(empleadoDto.getFechaNac() != null ? empleadoDto.getFechaNac() : empleado.getEmpFechaNacimiento());
        empleado.setEmpDireccion(empleadoDto.getDireccion() != null ? empleadoDto.getDireccion() : empleado.getEmpDireccion());
        empleado.setEmpTelefono(empleadoDto.getTelefono() != null ? empleadoDto.getTelefono() : empleado.getEmpTelefono());
        empleado.setEmpEstadoVacuna(empleadoDto.getEstadoVacuna() != null ? empleadoDto.getEstadoVacuna() : empleado.getEmpEstadoVacuna());

        for (VacunaDto vacuna : empleadoDto.getVacunaList()) {
            Vacuna vacunaGuardar = new Vacuna();
            vacunaGuardar.setEmpleado(empleado);
            vacunaGuardar.setVacNumeroDosis(vacuna.getVacNumeroDosis());
            vacunaGuardar.setVacFecha(vacuna.getVacFecha());
            Optional<TipoVacuna> tipoVacuna = tipoVacunaRepository.findById(vacuna.getVacTipoVacuna());
            vacunaGuardar.setVacTipoVacuna(tipoVacuna.isPresent() ? tipoVacuna.get() : null);
            empleado.getVacunaList().add(vacunaGuardar);
        }

        empleado = empleadoRepository.save(empleado);
        return empleado;
    }

    public List<Empleado> busquedaFiltros(Date fechaInicial,
                                          Date fechaFinal,
                                          Boolean estadoVacuna,
                                          Integer tipoVacuna) throws Exception {
        List<Empleado> empleadoList;

        if (tipoVacuna != null) {
            empleadoList = empleadoRepository.filtradoBusqueda(fechaInicial, fechaFinal, estadoVacuna, tipoVacuna);
        } else {
            empleadoList = empleadoRepository.filtradoBusquedaSinTipoVacuna(fechaInicial, fechaFinal, estadoVacuna);
        }

        if(empleadoList == null || empleadoList.isEmpty()){
            throw new Exception("No se encontraron empleados con los parámetros ingresados");
        }

        return empleadoList;
    }

    public Empleado busquedaPorId(Integer id) throws Exception {

        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);

        if (!empleadoOptional.isPresent()) {
            throw new Exception("El Empleado que se intenta consultar no se encuentra registrado");
        }

        return empleadoOptional.get();

    }

}
