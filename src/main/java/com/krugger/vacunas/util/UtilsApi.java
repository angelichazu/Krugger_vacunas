package com.krugger.vacunas.util;

import org.hibernate.PropertyValueException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilsApi {

    private UtilsApi() {
    }

    public static List<String> getProcessExceptionMessage(Exception ex, int size) {
        List<String> listMessage = new ArrayList<>();
        try {
            String msg = "";
            String msgError = "";
            if (Objects.isNull(ex)) {
                return listMessage;
            } else {
                if (!Objects.isNull(ex.getCause())) {
                    if (!Objects.isNull(ex.getCause().getCause())) {
                        String text = ex.getCause().getCause().getMessage();
                        if (text.contains("Detail")) {
                            text = text.substring(text.indexOf("Detail"), text.length() - 2);
                        }
                        msg += text + "\n";
                    } else if (!Objects.isNull(ex.getCause())) {
                        PropertyValueException pp = (PropertyValueException) ex.getCause();
                        String text = "";
                        //controlar mensajes de not null
                        if (pp.getMessage().contains("not-null")) {
                            text = pp.getMessage().substring(0, pp.getMessage().indexOf("com.")) + " " + pp.getPropertyName();
                        }
                        msg += text + "\n";
                    }
                } else if (!isNullEmpty(ex.getMessage())) {
                    msg = ex.getMessage();
                } else if (isNotNullEmpty(ex.getLocalizedMessage())) {
                    msg += "\n" + ex.getLocalizedMessage();
                }
                try {
                    if (!Objects.isNull(ex.getCause())) {
                        msgError += getCauseException(ex);
                    } else {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        ex.printStackTrace(pw);
                        String sStackTrace = sw.toString(); // stack trace as a string
                        msgError += sStackTrace + "\n";
                    }
                } catch (Exception e) {
                    Logger.getLogger(UtilsApi.class.getName()).log(Level.SEVERE,
                            "Error al generar msg de getprocessExceptionMessage", e);
                }
            }
            if (isNullEmpty(msg)) {
                msg = "ERROR NO IDENTIFICADO";
            }
            listMessage.add(msg.substring(0, msg.length() < size ? msg.length() : size));
            listMessage.add(msgError.substring(0, msgError.length() < size ? msgError.length() : size));
        } catch (Exception e) {
            listMessage.add("ERROR UNICO DE PARSEO");
            listMessage.add("Error no identificado");
        }

        return listMessage;
    }

    public static boolean isNullEmpty(Object object) {
        if (Objects.isNull(object))
            return true;
        else if (object.toString().isEmpty())
            return true;
        else
            return false;

    }

    public static boolean isNotNullEmpty(Object object) {
        return !isNullEmpty(object);
    }

    public static String getCauseException(Throwable ex) {
        String msg = (ex.getCause() == null ? "" : ex.getCause().getMessage());
        if (ex.getCause().getCause() != null) {
            msg += (isNullEmpty(ex.getCause().getCause().getMessage()) ? "" : ex.getCause().getCause().getLocalizedMessage()) + "\n";
        }
        return msg;
    }

    public static boolean validadorDeCedula(String cedula) throws Exception{
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            throw new Exception("Una excepcion ocurrio en el proceso de validadcion");
        }
        return cedulaCorrecta;
    }
}