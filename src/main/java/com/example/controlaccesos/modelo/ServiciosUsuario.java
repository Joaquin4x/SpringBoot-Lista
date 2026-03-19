package com.example.controlaccesos.modelo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
;


public class ServiciosUsuario {
    private List<Usuario> usuarios;
    
    private static ServiciosUsuario instancia;
    private ServiciosUsuario() {
        this.usuarios = new ArrayList<>();
    }   
    public static ServiciosUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ServiciosUsuario();
            instancia.cargarDatosIniciales();

        }
        return instancia;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    private void cargarDatosIniciales() {
		List<Usuario> usuarios = new ArrayList<>();
		Random random = new Random();
		
		// Fecha base: 1 de enero de 2025
		LocalDateTime fechaBase = LocalDateTime.of(2025, 1, 1, 8, 0); // 8:00 AM
		
		// Datos para usuarios
		String[][] datosUsuarios = {
			{"jperez", "Juan", "Pérez"},
			{"mgarcia", "María", "García"},
			{"crodriguez", "Carlos", "Rodríguez"},
			{"lfernandez", "Laura", "Fernández"},
			{"dmartinez", "Diego", "Martínez"},
			{"slopez", "Sofía", "López"},
			{"rgonzalez", "Roberto", "González"},
			{"aperez", "Ana", "Pérez"},
			{"jmorales", "Jorge", "Morales"},
			{"vsilva", "Valentina", "Silva"}
		};
		
		for (int i = 0; i < datosUsuarios.length; i++) {
			Usuario usuario = new Usuario();
			usuario.setUserName(datosUsuarios[i][0]);
			usuario.setNombre(datosUsuarios[i][1]);
			usuario.setApellido(datosUsuarios[i][2]);
			usuario.setHabilitado(random.nextBoolean());
			
			// Crear accesos para cada usuario (entre 2 y 5 accesos)
			List<Acceso> accesos = new ArrayList<>();
			int numeroAccesos = 2 + random.nextInt(4); // Entre 2 y 5 accesos
			
			for (int j = 0; j < numeroAccesos; j++) {
				Acceso acceso = new Acceso();
				
				// Crear horarios más variados a lo largo del día
				int horaBase;
				if (j == 0) {
					// Primera entrada: entre 7:00 AM y 9:00 AM
					horaBase = 7 + random.nextInt(3);
				} else if (j == 1) {
					// Segunda entrada: entre 10:00 AM y 12:00 PM
					horaBase = 10 + random.nextInt(3);
				} else if (j == 2) {
					// Tercera entrada: entre 1:00 PM y 3:00 PM
					horaBase = 13 + random.nextInt(3);
				} else {
					// Cuarta o quinta entrada: entre 4:00 PM y 6:00 PM
					horaBase = 16 + random.nextInt(3);
				}
				
				int minutoEntrada = random.nextInt(60);
				LocalDateTime entrada = fechaBase.withHour(horaBase).withMinute(minutoEntrada).withSecond(0).withNano(0);
				
				// Diferencia entre entrada y salida: entre 1 y 10 minutos
				int duracionMinutos = 1 + random.nextInt(10);
				LocalDateTime salida = entrada.plusMinutes(duracionMinutos);
				
				// Convertir LocalDateTime a Timestamp
				acceso.setEntrada(Timestamp.valueOf(entrada));
				acceso.setSalida(Timestamp.valueOf(salida));
				
				accesos.add(acceso);
			}
			
			usuario.setAccesos(accesos);
			usuarios.add(usuario);
		}
		
		this.usuarios = usuarios;
		
		// Mostrar información de los datos cargados
		System.out.println("=== DATOS DE PRUEBA CARGADOS ===");
		System.out.println("Total de usuarios: " + usuarios.size());
		
		for (Usuario usuario : usuarios) {
			System.out.println("\nUsuario: " + usuario.getNombre() + " " + usuario.getApellido() + 
							   " (" + usuario.getUserName() + ")");
			System.out.println("Habilitado: " + usuario.isHabilitado());
			System.out.println("Número de accesos: " + usuario.getAccesos().size());
			
			for (int i = 0; i < usuario.getAccesos().size(); i++) {
				Acceso acceso = usuario.getAccesos().get(i);
				System.out.println("  Acceso " + (i + 1) + ":");
				System.out.println("    Entrada: " + acceso.getEntrada());
				System.out.println("    Salida:  " + acceso.getSalida());
				
				// Calcular duración
				long duracion = acceso.getSalida().getTime() - acceso.getEntrada().getTime();
				long minutos = duracion / (60 * 1000);
				System.out.println("    Duración: " + minutos + " minutos");
			}
		}
		System.out.println("================================");
	}
}
