package com.krugger.vacunas.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "T_VACUNA")
public class Vacuna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vac_id")
    private Integer vacId;

    @OneToOne
    @JoinColumn(name = "vac_tipo_vacuna_id", referencedColumnName = "tipo_vac_id")
    private TipoVacuna vacTipoVacuna;

    @Column(name = "vac_fecha")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vacFecha;

    @Column(name = "vac_numero_dosis")
    private int vacNumeroDosis;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Empleado.class)
    @JoinColumn(name = "vac_emp_id", referencedColumnName = "emp_id")
    private Empleado empleado;

}