package com.krugger.vacunas.controller;

import com.krugger.vacunas.entity.Usuario;
import com.krugger.vacunas.repository.UsuarioRepository;
import com.krugger.vacunas.util.EstadoEnum;
import com.krugger.vacunas.util.GenericResponse;
import com.krugger.vacunas.util.UtilsApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoint de Aunteticacion")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Operation(
            summary = "Login",
            description = "Api para Login"
    )
    @GetMapping(value = "/login")
    public ResponseEntity<GenericResponse> login(@RequestParam("user") String user, @RequestParam("pw") String pw) {
        GenericResponse<Usuario> loginReturn = new GenericResponse<>();
        try {
            Usuario usuario = usuarioRepository.findUser(user,pw);
            if (usuario == null) {
                throw new Exception("Usuario no valido");
            }
            loginReturn.setObject(usuario);
            loginReturn.setEstado(EstadoEnum.OK);
        } catch (Exception e) {
            loginReturn.setObject(null);
            loginReturn.setEstado(EstadoEnum.ERROR);
            loginReturn.guardarMensaje(UtilsApi.getProcessExceptionMessage(e, 500));
        }
        return new ResponseEntity<>(loginReturn, HttpStatus.OK);
    }
    
}
