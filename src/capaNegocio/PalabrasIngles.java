package capaNegocio;

public enum PalabrasIngles{
	
	SWAP,
	LIBRARY,
	COMPUTER,
	NOTES,
	PROGRAMING,
	BOOK,
	NAME,
	POST,
	WEEKEND,
	UNIVERSITY,
	HOSPITAL,
	TEACHER,
	LESSON;
	
	 public static PalabrasIngles sortearPalabra() {
        return values()[(int) (Math.random() * values().length)];
    }	
} 