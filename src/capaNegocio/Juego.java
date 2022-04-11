package capaNegocio;

public class Juego {
	
	private PalabraSecreta palabra;
	private int intentosRestantes;
	private String nombreJugador;
	private Configuracion configuracion;
	
	private String ultimoIntento;
	
	public Juego(String jugador,Configuracion configuracion)
	{	
		this.configuracion=configuracion;
		palabra= new PalabraSecreta(configuracion);	
		this.nombreJugador=jugador;
		this.intentosRestantes=configuracion.getIntentosIniciales();
			
	}
	
	
	public boolean validarImput(String imput)
	{
		if(imput==null) throw new NullPointerException();				
		char c;	
		for (int x = 0; x < imput.length(); x++) 
		{
            c = imput.charAt(x);         
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) throw new IllegalArgumentException("Solo se admiten letras de la A a la Z en el imput");                     
        }
		return true;			
	}
	

	public boolean nuevoIntento(String imput)
	{	
		
		
		this.validarImput(imput);
		imput=imput.toUpperCase();
		
		this.ultimoIntento=imput;
		if(imput.length()>2)
		{
			this.palabra.arriesgarPalabra(imput);
			return false;
		}
		
		boolean resultadoDeIntento= this.palabra.arriesgarLetra(imput);
		
		//si la letra no estaba en la palabra
		//resto un intento
		//si ya no quedan intentos, devuelvo false porque ya no se permite intentar otra vez
		if(!resultadoDeIntento)
		{
			this.intentosRestantes--;
			if(this.intentosRestantes<=0) return false;		
		}
		
		//si la palabra se completo, devuelvo false.	
		if(palabra.seLogroDescubrirPalabra()) return false;
		
		//si no se gano ni se perdio, se sigue intentando.
		return true;
	}
	
	
		
	public boolean seGano()
	{
		return palabra.seLogroDescubrirPalabra();		
	}
	
	public boolean sePerdio()
	{
		boolean perdioPorArriesgar= palabra.palabraFueArriesgada()&& !palabra.seLogroDescubrirPalabra();	
		return   this.intentosRestantes<=0 || perdioPorArriesgar;	
	}
	
	public String ConocerPalabraSecreta()
	{
		return palabra.mostrarPalabraSecreta();
	}
	
	public String mostrarPalabraGuiones()
	{
		return this.palabra.mostrarPalabraPantalla();
	}
	
	public int mostrarIntentosRestantes()
	{
		return this.intentosRestantes;
	}
	
	public void elegirPalabraSecreta(String imput)
	{
		palabra.modificarPalabraSecreta(imput);
	}
	
	public String getUltimoIntento()
	{
		return this.ultimoIntento;
	}
	public String getJugador()
	{
		return this.nombreJugador;
	}
	
	
}
