package capaNegocio;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {
	
	private String nombre;
	private int victorias;
	private int derrotas;
	private ArrayList<HashMap<Object, Object>> sesiones;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.sesiones = new ArrayList<>();
	}
	
	//public conocerHistorial() {
		
	//}
	
	public void agregarVictoria() {
		this.victorias++;
	}
	
	public void agregarDerrota() {
		this.derrotas++;
	}
	
	public String conocerNombre() {
		return this.nombre;
	}
	
	public void agregarSesion(HashMap<Object, Object> sesion) {
		this.sesiones.add(sesion);
	}

	public ArrayList<HashMap<Object, Object>> obtenerHistorial() {
		return this.sesiones;
	}
}
