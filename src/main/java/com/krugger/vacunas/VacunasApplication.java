package com.krugger.vacunas;

import com.krugger.vacunas.entity.Usuario;
import com.krugger.vacunas.repository.RolRepository;
import com.krugger.vacunas.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SecurityScheme(name = "basicAuth",
		type = SecuritySchemeType.HTTP, scheme = "basic", in = SecuritySchemeIn.HEADER)
@SpringBootApplication
public class VacunasApplication implements ApplicationRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Usuario usuario = new Usuario();
		if (usuarioRepository.findByuserUsername("ADMIN") != null) {
			System.out.println("El Usuario Administrador ya existe");
		} else {
			usuario.setUserUsername("ADMIN");
			usuario.setUserPassword(passwordEncoder.encode("Patito.123@"));
			usuario.setRol(rolRepository.findByRolNombre("ADMIN"));
			usuarioRepository.save(usuario);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(VacunasApplication.class, args);
	}

}
