package it.uniroma3.diadia.giocatore;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private Borsa borsa;
	private int cfu;

	public Giocatore() {
		this.cfu= CFU_INIZIALI;
		this.borsa=new Borsa();

	}
	/**
	 * restituisce i cfu
	 * @return
	 */
	public int getCfu() {
		return cfu;
	}
	/**
	 * imposta i cfu
	 * @param cfu
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	/**
	 * restituisce un oggetto borsa
	 * @return
	 */
	public Borsa getBorsa() {
		return borsa;
	}
	/**
	 * imposta la borsa
	 * @param borsa
	 */
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	/**
	 * prende un atrezzo dalla stanza 
	 * @param attrezzo
	 * @return
	 */
	public boolean prendereAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	/**
	 * posa un attrezzo nella stanza
	 * @param nomeAttrezzo
	 * @return
	 */
	public Attrezzo posareAttrezzo(String nomeAttrezzo) {
		return this.borsa.removeAttrezzo(nomeAttrezzo);
	}
}
