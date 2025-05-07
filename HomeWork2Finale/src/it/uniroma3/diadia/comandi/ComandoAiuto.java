package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	static final private String AIUTO_VAI =" --> Vai nelle altre stanze utilizzando i punti cardinali  -[vai sud]-";
	static final private String AIUTO_GUARDA=" --> Guarda all'interno della stanza e controlla la tua borsa";
	static final private String AIUTO_PRENDI=" --> Prendi un attrezzo dalle stanza e inseriscilo nella borsa";
	static final private String AIUTO_POSA=" --> Posa un attrezzo dalla borsa nella stanza";
	static final private String AIUTO_FINE=" --> Termina la partita";
	static final private String AIUTO_MESSAGGIO="        --Ecco i comandi che puoi utilizzare all'interno di diadia--";
	static final private String AIUTO_RIGA="_____________________________________________________________________________";
	private IO io;
	static final private String[] elencoComandi = {AIUTO_MESSAGGIO+"\n"+AIUTO_RIGA+"\n","[vai]"+ AIUTO_VAI, "[prendi]"+ AIUTO_PRENDI,
			"[posa]"+ AIUTO_POSA,"[guarda]"+AIUTO_GUARDA,"[fine]"+AIUTO_FINE+"\n"+AIUTO_RIGA};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.io.mostraMessaggio(elencoComandi[i]+" ");
		this.io.mostraMessaggio(" ");

	}

	@Override
	public void setParametro(String parametro) {	

	}

	@Override
	public void setIO(IO io) {
		// TODO Auto-generated method stub
		this.io=io;
	}
}
