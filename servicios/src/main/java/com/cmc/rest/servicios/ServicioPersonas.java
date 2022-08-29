package com.cmc.rest.servicios;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;

public class ServicioPersonas {

	public static Persona actualizar(Persona persona) throws ValidationException {
		if (persona.getNombre().length() <= 3) {
			throw new ValidationException("el nombre es muy corto");
		}
		if (persona.getApellido().length() <= 3) {
			throw new ValidationException("El apellido es muy corto");
		}

		return (new Persona(persona.getCedula(), persona.getNombre().toUpperCase(), persona.getApellido().toUpperCase(),
				persona.getEdad()));
	}

	public static Persona buscarPorCedula(String cedula) {
		ManejadorArchivos archivos = new ManejadorArchivos("miarchivo.txt");
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			personas.addAll(archivos.leer());
		} catch (ArchivoException e) {
			e.printStackTrace();
		}
		for (Persona persona : personas) {
			if (persona != null) {
				if (persona.getCedula().equals(cedula)) {
					return persona;
				}
			}
		}
		return null;
	}

	public static void guardarPersona(Persona persona) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("C:\\apache-tomcat-9.0.65\\bin\\archivoPersonas.txt", true);
			pw = new PrintWriter(fichero);

			if (persona != null) {
				String guardar = persona.getNombre() + "-" + persona.getApellido() + "-" + persona.getCedula() + "-"
						+ persona.getEdad() + "-" + persona.getFechaNacimiento();

				pw.println("" + "\r\n" + guardar);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
