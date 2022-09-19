package com.krugger.vacunas.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "T_ROL")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer rolId;

    @Column(name = "rol_nombre")
    private String rolNombre;

}