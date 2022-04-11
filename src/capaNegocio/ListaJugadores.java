package capaNegocio;

import java.util.*;

public class ListaJugadores {
	
	private HashMap<String, ArrayList<Integer>> jugadores;
	
	public ListaJugadores()
	{
		jugadores= new HashMap<String,ArrayList<Integer>>();
	}
	
	public void setNombreJugador(String nombre)
	{
		this.validarNombre(nombre);
		if(!this.jugadores.containsKey(nombre))
		{
			ArrayList<Integer> victYderrotas=new ArrayList<Integer>(List.of(0,0));
			this.jugadores.put(nombre, victYderrotas);			
		}	
	}
	
	private void validarNombre(String nombre)
	{
		if(nombre==null)throw new NullPointerException();
		if(nombre.length()<=2)throw new IllegalArgumentException("El nombre de jugador debe poseer al menos 3 letras");
		char c;	
		for (int x = 0; x < nombre.length(); x++) 
		{
            c = nombre.charAt(x);         
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) throw new IllegalArgumentException("Solo se admiten letras de la A a la Z en el nombre");                     
        }
	}
	
	
	public void agregarVictoria(String nombre) 
	{
		this.validarNombre(nombre);
		ArrayList<Integer> victYderrotas;
		if(!this.jugadores.containsKey(nombre))
		{
			victYderrotas=new ArrayList<Integer>(List.of(1,0));
			this.jugadores.put(nombre, victYderrotas);			
		}	
		else
		{
			victYderrotas=this.jugadores.get(nombre);
			int victorias=victYderrotas.get(0);
			victorias=victorias+1;
			victYderrotas.set(0,victorias);
			
			this.jugadores.put(nombre, victYderrotas);		
		}
		
	}
	
	public void agregarDerrota(String nombre) 
	{
		this.validarNombre(nombre);
		ArrayList<Integer> victYderrotas;
		if(!this.jugadores.containsKey(nombre))
		{
			victYderrotas=new ArrayList<Integer>(List.of(0,1));
			this.jugadores.put(nombre, victYderrotas);			
		}	
		else
		{
			victYderrotas=this.jugadores.get(nombre);
			int derrotas=victYderrotas.get(1);
			derrotas=derrotas+1;
			victYderrotas.set(1,derrotas);
			
			this.jugadores.put(nombre, victYderrotas);		
		}
	}
	
	public ArrayList<String> getTotalJugadores()
	{
		if(this.jugadores==null)throw new NullPointerException();
		ArrayList<String> total=new ArrayList<String>();
		
		for (String key : this.jugadores.keySet()){
			total.add(key);
		}
		
		return total;
	}


}
