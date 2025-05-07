package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	static final private String RIGA="_______________________________________________________________";
	private IO io;

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(RIGA+"\n");
		this.io.mostraMessaggio(partita.getStanzaCorrente().toString());
		this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		this.io.mostraMessaggio("CFU:"+partita.getGiocatore().getCfu());
		this.io.mostraMessaggio(RIGA);

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIO(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
	}

}
