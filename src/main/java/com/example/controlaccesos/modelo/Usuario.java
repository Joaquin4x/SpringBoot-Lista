package com.example.controlaccesos.modelo;

import java.util.List;
import lombok.Data;

@Data
public class Usuario {
    private String userName;
    private String nombre;
    private String apellido;
    private boolean habilitado;
    private List<Acceso> accesos;
}
