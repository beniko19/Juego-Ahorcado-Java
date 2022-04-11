package capaNegocio;

public class PalabraSecreta {

	private String palabraSecreta;
	
	//usamos un stringbuilder ya que la cadena sera constantemente modificada.
	private StringBuilder palabraPantalla;
	
	private Boolean arriesgaronPalabra;
	
	public PalabraSecreta(Configuracion configuracion)
	{
		//Inicializo el Stringbuilder que se mostrara al jugador con 20 espacios ya que es 
		//un largo de letras que no se espera ser superado.
		palabraPantalla = new StringBuilder("                              ") ;
		
		//Inicializo la palabra segun los idiomas disponibles:
		
		if(configuracion.getIdiomaActual().equals("Ingles"))
		{
			palabraSecreta= PalabrasIngles.sortearPalabra().name();
		}
		else
		{
			palabraSecreta = PalabrasEspaniol.sortearPalabra().name();
		}
		
		
			
		//Recorto el string para que coincida con la cantidad de letras de la palabra			
		palabraPantalla.setLength(palabraSecreta.length());	
		
		//Se da como pista la primera letra de la palabra.
		this.palabraPantalla.setCharAt(0, this.palabraSecreta.charAt(0));
		
		this.arriesgaronPalabra=false;
	
	}
	
	public boolean existeLetraEnPal(String caracter){
		return palabraSecreta.contains(caracter);
	}
	
	public void arriesgarPalabra(String palabraArriesgada)
	{	
		this.palabraPantalla= new StringBuilder(palabraArriesgada);
		this.arriesgaronPalabra=true;	
	}
	

	public boolean arriesgarLetra(String caracter)
	{
		//pregunto si la letra se encuentra en la palabra secreta
		//si no esta, se termina el intento y se devuelve false
		if(!this.existeLetraEnPal(caracter)) return false;
		
		//Llamo a una funcion recursiva.
		//Empiezo la comparacion desde el inicio de la palabra (posicion 0)
		//al finalizar la funcion los cambios se habran aplicado en el stringbuilder.		
		return compararYCambiar( caracter, palabraSecreta.indexOf(caracter));
	}
	
	
	

	private Boolean compararYCambiar(String caracter,int posicionComparada)
	{
		//llega al caso base, cuando no encuentra mas el caracter en la palabra.
		if(posicionComparada==-1) return true;
		
		//reemplaza en el stringbuilder el guion por el caracter encontrado
		this.palabraPantalla.setCharAt(posicionComparada, caracter.charAt(0));
		
		//hace una llamada recursiva, preguntando por el caracter, pero a apartir de la posicion proxima a la ya consultada
		return compararYCambiar(caracter,palabraSecreta.indexOf(caracter, posicionComparada+1));		
	}
	
	public String mostrarPalabraPantalla()
	{
		return this.palabraPantalla.toString();
	}
	
	public String mostrarPalabraSecreta()
	{
		return this.palabraSecreta;
	}
		
	public boolean seLogroDescubrirPalabra()
	{
		return (this.palabraSecreta.equals(this.palabraPantalla.toString()));
	}
	
	public boolean palabraFueArriesgada()
	{
		return this.arriesgaronPalabra;
	}
	
	public void modificarPalabraSecreta(String palabraNueva)
	{
		//validar que la palabra de guiones sea puramente de guiones.
		//validar que lo que se paso sea una palabra valida
		this.palabraSecreta=palabraNueva;
	}
}
