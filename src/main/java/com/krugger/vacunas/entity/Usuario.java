package com.krugger.vacunas.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_username")
    private String userUsername;

    @Column(name = "user_password")
    private String userPassword;

    @OneToOne
    @JoinColumn(name = "user_rol_id", referencedColumnName = "rol_id")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Integer userId, String userUsername, String userPassword, Rol rol) {
        this.userId = userId;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.rol = rol;
    }

}
