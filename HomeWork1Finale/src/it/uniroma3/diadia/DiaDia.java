package it.uniroma3.diadia;


import java.util.Scanner;


import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};
	private IOConsole console;
	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
		this.console=new IOConsole();


	}


	public void gioca() {
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("borsa"))
			this.borsa();
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else if(this.partita.isFinita()) {
			if(this.partita.getGiocatore().getCfu()<=0)
				console.mostraMessaggio("CFU ESAURITI");
			console.mostraMessaggio("Partita finita!");
			return true;
		}
		return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
		console.mostraMessaggio(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else if(this.partita.isFinita())
			console.mostraMessaggio("Hai terminato i cfu!");

		else{
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		console.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione()+ "CFU:" +partita.getGiocatore().getCfu());
	}

	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			console.mostraMessaggio("Inserisci l'attrezzo che vuoi prendere!");
		else {
			Attrezzo a=this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(a==null)
				console.mostraMessaggio("L'attrezzo non è nella stanza!");
			else {
				if(this.partita.getGiocatore().prendereAttrezzo(a)) {
					this.partita.getStanzaCorrente().removeAttrezzo(a);
					console.mostraMessaggio("Attrezzo inserito nella borsa!");
				}else
					console.mostraMessaggio("ERRORE: borsa piena o peso max raggiunto!");

			}
		}
	}

	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			console.mostraMessaggio("Inserisci l'attrezzo che vuoi posare!");
		else {
			Attrezzo a=this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(a==null)
				console.mostraMessaggio("L'attrezzo non è nella borsa!");
			else {
				if(this.partita.getStanzaCorrente().addAttrezzo(a))
					this.partita.getGiocatore().posareAttrezzo(nomeAttrezzo);
				console.mostraMessaggio("Attrezzo posato nella stanza!");

			}

		}
	}
	private void borsa() {
		console.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
	}
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
		//IOConsole console= new IOConsole();
	}

}