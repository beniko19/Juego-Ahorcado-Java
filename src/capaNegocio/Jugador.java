package capaNegocio;
import java.util.HashMap;

public class Jugador {
	
	private String nombre;
	private int victorias;
	private int derrotas;
	
	public Jugador() 
	{
		this.nombre = "";
		this.victorias=0;
		this.derrotas=0;
		
	}
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.victorias=0;
		this.derrotas=0;
		
		
	}
	
	public void setNombreJugador(String nombre)
	{
		this.nombre=nombre;
	}
	
	public void agregarVictoria() {
		this.victorias++;
	}
	
	public void agregarDerrota() {
		this.derrotas++;
	}

	public String conocerNombre() {
		return this.nombre;
	}
}
