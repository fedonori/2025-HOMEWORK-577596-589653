package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.*;

public class ComandoPosa extends AbstractComando {
	private static final String NOME = "posa";
	
	@Override
	public void esegui(Partita partita) {
		if(this.getParametro()==null) {
			partita.getIO().mostraMessaggio("Quale attrezzo vuoi posare?");
			this.setParametro(partita.getIO().leggiRiga());
		}
		Attrezzo a = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
		if(a==null) {
			partita.getIO().mostraMessaggio("Questo attrezzo non è presente nella tua borsa!");
			return;
		}
		if(!partita.getStanzaCorrente().addAttrezzo(a)) {
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getIO().mostraMessaggio("La stanza e' piena! ");
		}
		else {
			partita.getIO().mostraMessaggio("Attrezzo posato! ");	
		}
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
