package com.example.controlaccesos.controlador;


import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlaccesos.modelo.Acceso;
import com.example.controlaccesos.modelo.ServiciosUsuario;
import com.example.controlaccesos.modelo.Usuario;

@RestController
public class ControladorAccesos {

    ServiciosUsuario serviciosUsuario = ServiciosUsuario.getInstancia();
    
    @GetMapping("/todos")
    public List<Usuario> todosLosUsuarios() {
        return serviciosUsuario.getUsuarios();

    }

    @GetMapping("/lista")
    public List<Usuario> usuariosHabilitadosConAccesoMayorA(@RequestParam int minutos) {
        
        List<Usuario> resultado = new ArrayList<>();

        for(Usuario usuario : serviciosUsuario.getUsuarios()){
            if(usuario.isHabilitado()) {
                for (Acceso acceso : usuario.getAccesos()) {
                    long duracion = (acceso.getSalida().getTime() - acceso.getEntrada().getTime()) / (60 * 1000);
                    if (duracion > minutos) {
                        resultado.add(usuario);
                        break;
                    }
                }
            }
        }
        return resultado; // Implementación pendiente
    }

}
