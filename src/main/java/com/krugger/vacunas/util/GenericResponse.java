package com.krugger.vacunas.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
public class GenericResponse<T> implements Serializable {

    private static final long serialVersionUID = 4189541161407214791L;
    private EstadoEnum estado;
    private String mensaje;
    private String mensajeError;
    private T object;

    public void guardarMensaje(List<String> mensajeList){
        if(mensajeList.size()>1){
            mensaje= mensajeList.get(0);
            mensajeError= mensajeList.get(1);
        }else{
            mensaje= mensajeList.get(0);
        }
    }

}
