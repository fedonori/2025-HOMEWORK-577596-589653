package it.uniroma3.diadia.comandi;




import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


public interface Comando {
	/**
	 * esecuzione del comando
	 * @param partita
	 */
	public void esegui(Partita partita);

	/**
	 * set parametro del comando
	 * @param parametro
	 */
	public void setParametro(String parametro);

	public void setIO(IO io);
}