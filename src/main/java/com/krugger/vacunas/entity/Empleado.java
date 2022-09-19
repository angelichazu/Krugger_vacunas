package com.krugger.vacunas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "T_EMPLEADO")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @NotNull(message = "El ingreso de la cedula es obligatorio")
    @Column(name = "emp_cedula", length = 10, unique = true)
    @Pattern(regexp = "^[0-9]{10}$", message = "La cedula ingresada no es correcta")
    private String empCedula;

    @NotNull(message = "El ingreso del nombre es obligatorio")
    @Column(name = "emp_nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre debe contener solo letras")
    private String empNombre;

    @NotNull(message = "El ingreso del apellido es obligatorio")
    @Column(name = "emp_apellido")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El apellido debe contener solo letras")
    private String empApellido;

    @NotNull(message = "El ingreso del correo es obligatorio")
    @Column(name = "emp_correo")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El correo ingresado no es correcto")
    private String empCorreo;

    @Column(name = "emp_fecha_nac")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empFechaNacimiento;

    @Column(name = "emp_direccion")
    private String empDireccion;

    @Column(name = "emp_telefono")
    @Pattern(regexp = "^[0-9]{10}$", message = "El telefono ingresado no es correcto")
    private String empTelefono;


    @Column(name = "emp_estado_vacuna")
    private Boolean empEstadoVacuna;

    @JsonManagedReference
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Vacuna> vacunaList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_user_id", referencedColumnName = "user_id")
    private Usuario usuario;

    public Empleado() {
    }

}
