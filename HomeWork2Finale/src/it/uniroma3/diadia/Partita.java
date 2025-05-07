package it.uniroma3.diadia;



import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;


	public Partita(){
		this.labirinto=new Labirinto();
		this.stanzaCorrente=labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore=new Giocatore();
	}
	/**
	 * restituisce la stanza corrente 
	 * @return
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	/**
	 * imposta la stanza corrente
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()==this.labirinto.getStanzaVincente();
	}
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() <= 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	/**
	 * restituisce il giocatore
	 * @return
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}
	public boolean giocatoreIsVivo() {
		if(giocatore.getCfu()>0)
			return true;
		return false;

	}

}
