package com.krugger.vacunas.modelDto;

import lombok.Data;
import java.util.Date;

@Data
public class VacunaDto {

    private int vacTipoVacuna;
    private Date vacFecha;
    private int vacNumeroDosis;
}