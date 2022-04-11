package capaNegocio;

public class flagLetra {
	
	private char letra;
	private boolean flagSinUsar;
	
	public flagLetra(char letra)
	{
		this.letra=letra;
		this.flagSinUsar=true;
	}
	
	public void setLetra(char letra)
	{
		this.letra=letra;
	}
	
	public char getLetra()
	{
		return this.letra;
	}
	
	public boolean getFlag()
	{
		return this.flagSinUsar;
	}
	 
	public void setFlag(boolean flag)
	{
		this.flagSinUsar=flag;
	}

}
