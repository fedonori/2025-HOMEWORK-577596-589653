package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * comando per prendere gli attrezzi da terra
 * e inserirli nella borsa del giocatore(se non piena).
 * 
 * @see ComandoPosa
 */
public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null)
			this.io.mostraMessaggio("Che attrezzo vuoi prendere?");

		Attrezzo a=partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a==null)
			this.io.mostraMessaggio("L'attrezzo non è nella stanza");

		if(partita.getGiocatore().prendereAttrezzo(a)) {
			partita.getStanzaCorrente().removeAttrezzo(a);
			this.io.mostraMessaggio("Attrezzo inserito nella borsa!");
		}
		else
			this.io.mostraMessaggio("Borsa piena");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	@Override
	public void setIO(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
	}

}
