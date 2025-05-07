package it.uniroma3.diadia.comandi;


import java.util.Scanner;

public interface FabricaDiComandi {
	public interface FabbricaDiComandi {
		public Comando costruisciComando(String istruzione);
	}
	public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
		@Override
		public Comando costruisciComando(String istruzione) {
			@SuppressWarnings("resource")
			Scanner scannerDiParole = new Scanner(istruzione);
			String nomeComando = null;
			String parametro = null;
			Comando comando = null;
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();// prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next(); // seconda parola: eventuale param.
			if (nomeComando == null);
			return comando;
		}
	}
	}