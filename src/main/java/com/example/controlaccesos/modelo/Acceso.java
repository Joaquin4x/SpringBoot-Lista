package com.example.controlaccesos.modelo;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Acceso {
    private Timestamp entrada;
    private Timestamp salida;
}
