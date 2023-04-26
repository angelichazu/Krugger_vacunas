package com.krugger.vacunas.repository;

import com.krugger.vacunas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByuserUsername(String username);
    @Query(value = "SELECT u.* " +
            "FROM T_USUARIO u " +
            "WHERE u.user_username = :user AND u.user_password = :pw", nativeQuery = true)
    Usuario findUser(String user, String pw);
}