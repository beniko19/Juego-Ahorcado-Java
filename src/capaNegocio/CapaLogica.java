package capaNegocio;

import java.util.*;

/** El juego del ahorcado posee 3 momentos con comportamientos diferentes:
 *  1) Cuando se conocen y se guardan las configuraciones para iniciar el juego.
 *  (pantalla de presentacion)
 *  
 *  2) el momento en que el usuario esta arriesgando intentos.
 *  el usuario arriesga letras o una palabra, de manera ordenada.
 *  se informa por pantalla como requisito minimo : palabra en juego, intentos restantes 
 *  opcional: nombre del jugador, dificultad, idioma, y modo de juego Actuales.
 *  (pantalla de desarrollo del juego)
 *  
 *  3) el momento en que se gana o se pierde
 *  (pantalla final del juego)
 *  
 *  cuando se gana:
 *  Sale de la primer instancia y se informa la palabra formada, junto con un mensaje de victoria
 *  cuando se pierde:
 *  Sale de la primer instancia y se informa la palabra secreta, junto con un mensaje de derrota
 *  
 *  En ambas, se debe dar la eleccion al usuario de dejar de jugar o volver a jugar(ya sea con las configuraciones
 *  previas o la posibilidad de volver a cargar unas nuevas)
 *  
 *  Luego se vuelve a instanciar un nuevo juego con las nuevas configuraciones elegidas.
 *  */


public class CapaLogica {
	
	private Juego juego;
	private Configuracion configuraciones;
	private ListaJugadores jugadores;
	private NPC npc;
	
	//Se crea un constructor con configuraciones de las variables " por default "
	public CapaLogica()
	{
		configuraciones= new Configuracion();
		jugadores=new ListaJugadores();	
		npc= new NPC();
	}
	
	
	/***INICIALIZACION DEL JUEGO**/ 
	
	/***Funciones que informan idioma y dificultad disponibles en la version actual***/
	public ArrayList<String> getIdiomasDisponibles()
	{
		return configuraciones.getIdiomasDisponibles();	
	}
	
	public ArrayList<String> getDificultadesDisponibles()
	{
		return configuraciones.getDificultadesDisponibles();	
	}
	
	public ArrayList<String> getTiposJugador()
	{
		return configuraciones.getTiposDeJugador();
	}
	
	public ArrayList<String> getJugadores()
	{
		return this.jugadores.getTotalJugadores();
	}
	
	//inicializacion con configuraciones default
	public void iniciarJuego()
	{
		juego=new Juego("DEFAULT",this.configuraciones);		
	}
	
	//inicializacion con las configuraciones elegidas
	
	public void iniciarJuego(String nombreJugador,String idioma, String dificultad, String tipoJugador )
	{
		configuraciones.setIdioma(idioma);
		configuraciones.setDificultad(dificultad);
		configuraciones.setTipoJugador(tipoJugador);
		jugadores.setNombreJugador(nombreJugador);
		
		juego=new Juego(nombreJugador,this.configuraciones);		
	}
		
	
	
	/** DESARROLLO DEL JUEGO */
	//Dispara el algoritmo de juego al momento de recibir el ingreso del usuario
	//parametros: una cadena que representa la letra o palabra arriesgada.
	//Devuelve un boolean que indica si se debe permanecer en esta instancia del juego(se debe volver a arriesgar)
	//Si devuelve False, debe pasar obligatoriamente a una de las dos instancias siguientes "ganado" o "perdido".
	
	
	public boolean arriesgarIntento(String imput)
	{	
		
		if(this.getTipoJugador().equals("Maquina")) 
		{
			return juego.nuevoIntento(npc.simularImput(4,this.getPalabraEnJuego().length()));
		}
		return juego.nuevoIntento(imput);
		
	}
	
		
	/**	FINALES DEL JUEGO 	**/
	//Se fija si el juego se ganó.
	//Devuelve true si ahora se encuentra en esta instancia
	//Se espera que de estar en esta pantalla, el juego vuelva a mostrar la palabra en Juego
	//y envie un mensaje de felicitaciones.
	public boolean juegoGanado()
	{
		if(juego.seGano())
		{
			jugadores.agregarVictoria(juego.getJugador());
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//se fija si el juego se perdió
	//Devuelve true si ahora se encuentra en esta instancia
	//se espera que de estar en esta pantalla, se muestre la palabra oculta
	//y se envie un mensaje informando de la derrota.
	public boolean juegoPerdido()
	{
		if(juego.sePerdio())
		{
			jugadores.agregarDerrota(juego.getJugador());
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	
	/** DATOS EN LA VENTANA DEL JUGADOR*/
	
	
	 //Funciones que informan a la vista los datos actualizados de: intentos, palabra en juego
	// nombre de jugador, configuraciones elegidas, etc. Necesarias para la pantalla de juego.
	 
	
	
	public String getPalabraEnJuego()
	{
		return juego.mostrarPalabraGuiones();
	}
	public String getPalabraSecreta()
	{
		return juego.ConocerPalabraSecreta();
	}
	public int getIntentosRestantes()
	{
		return juego.mostrarIntentosRestantes();
	}
	
	public String getNombreJugador()
	{
		return juego.getJugador();
	}
	
	public String getIdioma()
	{
		return configuraciones.getIdiomaActual();
	}
	
	public String getDificultad()
	{
		return configuraciones.getDificultad();
	}
	public String getTipoJugador()
	{
		return this.configuraciones.getTipoJugadorActual();
	}
	
	
	public String getUltimoIntento()
	{
		return juego.getUltimoIntento();
	}
	
	public boolean caracterValido(String caracter)
	{
		return juego.validarImput(caracter);
	}
	

	
	
	
}
