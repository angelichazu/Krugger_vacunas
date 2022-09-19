package com.krugger.vacunas.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "T_TIPO_VACUNA")

public class TipoVacuna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_vac_id")
    private Integer tpVacId;

    @Column(name = "tipo_vac_nombre")
    private String tpVacNombre;
}
