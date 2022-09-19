package com.krugger.vacunas.services;

import com.krugger.vacunas.entity.Empleado;
import com.krugger.vacunas.entity.Usuario;
import com.krugger.vacunas.modelDto.EmpleadoDto;
import com.krugger.vacunas.repository.EmpleadoRepository;
import com.krugger.vacunas.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolRepository rolRepository;

    public Empleado crearEmpleado(EmpleadoDto empleado) throws Exception {

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
}
