package capaNegocio;

import java.util.*;

public class NPC {
	
	 private String intentoCalculado;
	 private ArrayList<flagLetra> abecedarioProbabilistico;
	 private int cantDudaLetra;
	
	 
	 public NPC()
	 {
		 this.abecedarioProbabilistico=new ArrayList<flagLetra>();
		 this.inicializarAbecedario(); 
		 this.cantDudaLetra=3;
	 }
	 
	 private void inicializarAbecedario()
	 {
		 flagLetra letra=new flagLetra('e');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('a');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('o');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('i');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('s');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('r');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('n');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('d');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('l');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('u');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('c');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('t');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('b');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('m');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('p');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('f');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('g');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('v');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('h');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('q');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('j');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('y');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('k');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('z');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('w');
		 this.abecedarioProbabilistico.add(letra);
		 letra=new flagLetra('x');
		 this.abecedarioProbabilistico.add(letra);	 
	 }
	 
	 private String pensarUnaLetra()
	 {
		 flagLetra letraEvaluada;
		 //acumulador que revisa el total de letras recorridas. 
		 int letrasRevisadas=0;
		 
		 //acu de letras con flag true, para saber cuantas se salteara de las que se pueden usar.
		 int letrasFlagTrue=0;
		 
		 int random=(int) (Math.random() * this.cantDudaLetra);
		
		 while(letrasFlagTrue <=random && letrasRevisadas< this.abecedarioProbabilistico.size())
		 { 
			 letraEvaluada=this.abecedarioProbabilistico.get(letrasRevisadas);	 			
			 if(letraEvaluada.getFlag()) letrasFlagTrue++;		 
			 letrasRevisadas++;		 
		 }
		 
		 //resto 1 porque los indices van desde el 0, y esa variable tiene N numeros de letras contadas.
		letrasRevisadas=letrasRevisadas-1;
		this.abecedarioProbabilistico.get(letrasRevisadas).setFlag(false);	
		this.intentoCalculado= String.valueOf(this.abecedarioProbabilistico.get(letrasRevisadas).getLetra());
		return this.intentoCalculado;	  
	 }
 
	 private String pensarUnaPalabra()
	 {	 
		 return "";
	 }
	 
	 public String simularImput(int letrasFaltanAdivinar, int largoPal)
	 { 
		 return this.pensarUnaLetra();	 
	 }
	 
	public String getIntentoCalculado()
	{
		return this.intentoCalculado;
	}
}
