package capaNegocio;

public enum PalabrasEspaniol{
	
	BIBLIOTECA,
	LABORATORIO,
	COMPUTADORA,
	APUNTES,
	PROGRAMACION,
	CUADERNILLO,
	INFLACION,
	PROMOCIONAR,
	RECURSAR,
	MODULO,
	UNIVERSIDAD,
	MATERIA,
	PROFESOR;
	
	 public static PalabrasEspaniol sortearPalabra() {
        return values()[(int) (Math.random() * values().length)];
    }	
} 