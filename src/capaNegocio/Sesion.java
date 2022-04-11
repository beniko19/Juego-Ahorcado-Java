package capaNegocio;

import java.util.ArrayList;
import java.util.HashMap;

public class Sesion {
	private static int sesionID = 0;
	private Usuario usuario;
	private String palabraActual;
	private StringBuilder palProvisoria = new StringBuilder();
	private int intentosRestantes;
	private String dificultad;
	private String estado;
	private boolean gano;
	private boolean perdio;
	
	public Sesion(String pal, Usuario usuario, String dificultad, String estado, int intentos) {
		this.estado = estado;
		this.palabraActual = pal;
		this.usuario = usuario;
		this.dificultad = dificultad;
		this.intentosRestantes = intentos;
		this.palProvisoria.setLength(this.palabraActual.length());
		Sesion.sesionID++;
		
	}
	
	public void agregarCaracEnPalProvi(String caracter, ArrayList<Integer> posDeCaracter) {
		for(int i = 0; i < posDeCaracter.size(); i++) {
			this.palProvisoria.setCharAt(posDeCaracter.get(i).intValue(), caracter.charAt(0));
		}
	}
	
	public Integer obtenerIntentos() {
		return this.intentosRestantes;
	}

	public int restarIntento() {
		return this.intentosRestantes--;
	}
	
	public String obtenerPalProvi() {
		return this.palProvisoria.toString();
	}

	public String getDificultad() {
		return this.dificultad;
	}
	
	public void gano() {
		this.gano = true;
	}
	
	public void perdio() {
		this.perdio = false;
	}
	
	public HashMap<Object, Object> obtenerDatos() {
		HashMap<Object, Object> datos = new HashMap<>();
		datos.put("ID", Sesion.sesionID);
		datos.put("Nombre", this.usuario.conocerNombre());
		datos.put("Palabra", this.palabraActual);
		datos.put("Intentos", this.intentosRestantes);
		datos.put("Dificultad", this.dificultad);
		datos.put("Estado", this.estado);
		datos.put("Gano", this.gano);
		datos.put("Perdio", this.perdio);
		return datos;
	}	
}
