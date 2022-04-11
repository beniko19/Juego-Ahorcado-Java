package capaNegocio;

import java.util.*;


public class Configuracion {
	
	
	private String idioma;
	private String dificultad;
	private String tipoJugador;
	private String reglas;
	private int intentos;
	private ArrayList<String> idiomasDisponibles;
	private ArrayList<String> dificultadesDisponibles;
	private ArrayList<String> tiposJugadorDisponibles;
	
	enum Dificultad
	{
		Facil(18),
		Normal(12),
		Dificil(6);
		
		private int intentos;
		Dificultad(int intentos)
		{
			this.intentos=intentos;
		}	
		
		public int getIntentosPorDificultad() 
		{ 
			return intentos; 
		}
			
	}


	public Configuracion()
	{
		this.idioma="espaniol";
		this.reglas="";
		this.intentos=Dificultad.Normal.getIntentosPorDificultad();	
		this.idiomasDisponibles= new ArrayList<String>(List.of("Espaniol","Ingles"));
		this.dificultadesDisponibles= new ArrayList<String>(List.of("Facil","Normal","Dificil"));
		this.tiposJugadorDisponibles= new ArrayList<String>(List.of("Persona","Maquina"));
	}
	
	public Configuracion(String idioma,String tipoJugador,String dificultad)
	{
		
		//validar idioma
		this.idioma=idioma;
		this.reglas="";
		this.intentos=Dificultad.valueOf(dificultad).intentos;
		this.tipoJugador=tipoJugador;
	}
	
	public ArrayList<String> getIdiomasDisponibles()
	{
		return this.idiomasDisponibles;
		
	}
	
	public ArrayList<String> getDificultadesDisponibles()
	{
		return this.dificultadesDisponibles;
		
	}
	
	public int getIntentosIniciales()
	{
		return this.intentos;
	}
	
	
	public void setDificultad(String dificultad)
	{
		this.dificultad=dificultad;
		intentos= Dificultad.valueOf(dificultad).intentos;
	}
	
	public String getDificultad()
	{
		return this.dificultad;
	}

	public String getReglas() {
		return reglas;
	}

	public void setReglas(String reglas) {
		this.reglas = reglas;
	}

	public String getIdiomaActual() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setTipoJugador(String tipoJugador) {
		this.tipoJugador=tipoJugador;
	}
	
	public ArrayList<String> getTiposDeJugador()
	{
		return this.tiposJugadorDisponibles;
	}
	
	public String getTipoJugadorActual()
	{
		return this.tipoJugador;
	}
	
	
	
}
