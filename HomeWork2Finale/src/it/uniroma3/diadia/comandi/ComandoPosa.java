package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * comando per posare gli attrezzi della borsa del giocatore a terra
 * in una qualsiasi stanza.
 * 
 * @see ComandoPrendi
 */
public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null)
			this.io.mostraMessaggio("Che attrezzo vuoi posare?");
		Attrezzo a=partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(a==null)
			this.io.mostraMessaggio("L'attrezzo non è nella borsa.");
		if(partita.getStanzaCorrente().addAttrezzo(a)) {
			partita.getGiocatore().posareAttrezzo(nomeAttrezzo);
			this.io.mostraMessaggio("Attrezzo posato nella stanza!");

		}

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
