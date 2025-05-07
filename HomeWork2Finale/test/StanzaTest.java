

import static org.junit.jupiter.api.Assertions.*; // Import statico per le asserzioni
import org.junit.jupiter.api.BeforeEach; // Per il metodo di setup eseguito prima di ogni test
import org.junit.jupiter.api.Test; // Per marcare i metodi di test

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo; // Importa la classe Attrezzo

class StanzaTest {

    // Costanti per nomi e direzioni usate nei test
    private static final String NOME_STANZA_SEMPLICE = "Stanza Semplice";
    private static final String NOME_STANZA_NORD = "Stanza Nord";
    private static final String NOME_STANZA_SUD = "Stanza Sud";
    private static final String NOME_ATTREZZO_1 = "Osso";
    private static final String NOME_ATTREZZO_2 = "Spada";
    private static final String DIREZIONE_NORD = "nord";
    private static final String DIREZIONE_SUD = "sud";
    private static final String DIREZIONE_EST = "est"; // Direzione non usata per testare null

    private Stanza stanzaSemplice;
    private Stanza stanzaNord;
    private Stanza stanzaSud;
    private Attrezzo attrezzo1;
    private Attrezzo attrezzo2;

    // Metodo di setup: viene eseguito PRIMA di ogni metodo @Test
    @BeforeEach
    void setUp() {
        // Crea nuove istanze per ogni test per garantire l'isolamento
        stanzaSemplice = new Stanza(NOME_STANZA_SEMPLICE);
        stanzaNord = new Stanza(NOME_STANZA_NORD);
        stanzaSud = new Stanza(NOME_STANZA_SUD);
        attrezzo1 = new Attrezzo(NOME_ATTREZZO_1, 1); // Assumendo Attrezzo(nome, peso)
        attrezzo2 = new Attrezzo(NOME_ATTREZZO_2, 3);
    }

    // --- Test per impostaStanzaAdiacente ---

    @Test
    void testImpostaStanzaAdiacenteNuovaDirezione() {
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_NORD, stanzaNord);
        // Verifica che la stanza adiacente a nord sia quella impostata
        assertSame(stanzaNord, stanzaSemplice.getStanzaAdiacente(DIREZIONE_NORD), "La stanza a nord non è quella attesa.");
        // Verifica che il numero di stanze adiacenti sia aumentato
        // Nota: questo test richiede accesso a numeroStanzeAdiacenti o un metodo getNumeroStanzeAdiacenti
        // In alternativa, si testa tramite getDirezioni()
        assertEquals(1, stanzaSemplice.getDirezioni().length, "Il numero di direzioni non è 1.");
    }

    @Test
    void testImpostaStanzaAdiacenteSovrascriveDirezione() {
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_NORD, stanzaNord); // Imposta nord
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_NORD, stanzaSud); // Sovrascrive nord con stanzaSud
        // Verifica che la stanza adiacente a nord sia ora stanzaSud
        assertSame(stanzaSud, stanzaSemplice.getStanzaAdiacente(DIREZIONE_NORD), "La stanza a nord non è stata sovrascritta correttamente.");
        // Verifica che il numero di stanze adiacenti non sia aumentato (abbiamo sovrascritto)
        assertEquals(1, stanzaSemplice.getDirezioni().length, "Il numero di direzioni dovrebbe essere ancora 1.");
    }

    // --- Test per getStanzaAdiacente ---

    @Test
    void testGetStanzaAdiacenteEsistente() {
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_SUD, stanzaSud);
        // Verifica che getStanzaAdiacente restituisca la stanza corretta
        Stanza adiacenteOttenuta = stanzaSemplice.getStanzaAdiacente(DIREZIONE_SUD);
        assertSame(stanzaSud, adiacenteOttenuta, "Non è stata restituita la stanza adiacente sud corretta.");
    }

    @Test
    void testGetStanzaAdiacenteNonEsistente() {
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_NORD, stanzaNord);
        // Verifica che getStanzaAdiacente restituisca null per una direzione non impostata
        Stanza adiacenteOttenuta = stanzaSemplice.getStanzaAdiacente(DIREZIONE_EST);
        assertNull(adiacenteOttenuta, "Dovrebbe restituire null per una direzione non esistente.");
    }

    // --- Test per getNome ---

    @Test
    void testGetNome() {
        // Verifica che getNome restituisca il nome corretto impostato nel costruttore
        assertEquals(NOME_STANZA_SEMPLICE, stanzaSemplice.getNome(), "Il nome della stanza non è corretto.");
    }

    @Test
    void testGetNomeStanzaDiversa() {
        // Verifica il nome di un'altra istanza per sicurezza
        assertEquals(NOME_STANZA_NORD, stanzaNord.getNome(), "Il nome della stanza Nord non è corretto.");
    }

    // --- Test per getDescrizione / toString ---
    // Poiché getDescrizione chiama toString, testiamo toString

    @Test
    void testToStringStanzaVuota() {
        String expected = NOME_STANZA_SEMPLICE + "\nUscite: \nAttrezzi nella stanza: ";
        // Verifica la descrizione di una stanza senza uscite né attrezzi
        assertEquals(expected, stanzaSemplice.toString(), "La descrizione della stanza vuota non è corretta.");
    }

    @Test
    void testToStringStanzaConUsciteEAttrezzi() {
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_NORD, stanzaNord);
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_SUD, stanzaSud);
        stanzaSemplice.addAttrezzo(attrezzo1);
        stanzaSemplice.addAttrezzo(attrezzo2);

        String expected = NOME_STANZA_SEMPLICE +
                          "\nUscite:  " + DIREZIONE_NORD + " " + DIREZIONE_SUD + // Nota: l'ordine delle uscite dipende dall'implementazione
                          "\nAttrezzi nella stanza: " + attrezzo1.toString() + " " + attrezzo2.toString() + " ";
        // Verifica la descrizione completa
        // Nota: questo test è sensibile all'ordine e alla formattazione esatta di toString()
        assertEquals(expected, stanzaSemplice.toString(), "La descrizione della stanza piena non è corretta.");
        // Potrebbe essere meglio fare asserzioni più specifiche sulla presenza delle stringhe
        assertTrue(stanzaSemplice.toString().contains(DIREZIONE_NORD));
        assertTrue(stanzaSemplice.toString().contains(DIREZIONE_SUD));
        assertTrue(stanzaSemplice.toString().contains(attrezzo1.toString()));
        assertTrue(stanzaSemplice.toString().contains(attrezzo2.toString()));
    }


    // --- Test per getAttrezzi ---

    @Test
    void testGetAttrezziStanzaVuota() {
        Attrezzo[] attrezzi = stanzaSemplice.getAttrezzi();
        // Verifica che l'array non sia null
        assertNotNull(attrezzi, "L'array di attrezzi non dovrebbe essere null.");
        // Verifica che i primi elementi siano null (dato che numeroAttrezzi è 0)
        // Assumendo che l'array venga inizializzato ma non riempito
         for(int i=0; i<attrezzi.length; i++) {
             // Ci aspettiamo che solo i primi 'numeroAttrezzi' siano non-null
             if (i < 0) // In questo caso numeroAttrezzi è 0 all'inizio
                assertNotNull(attrezzi[i]);
             else
                assertNull(attrezzi[i], "L'elemento " + i + " dovrebbe essere null in una stanza vuota.");
         }
    }

    @Test
    void testGetAttrezziConAttrezzi() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        Attrezzo[] attrezzi = stanzaSemplice.getAttrezzi();
        // Verifica che l'array non sia null
        assertNotNull(attrezzi, "L'array di attrezzi non dovrebbe essere null.");
        // Verifica che il primo attrezzo sia quello aggiunto
        assertSame(attrezzo1, attrezzi[0], "Il primo attrezzo non è quello atteso.");
        // Verifica che il secondo sia null
        assertNull(attrezzi[1], "Il secondo slot attrezzo dovrebbe essere null.");
    }


    // --- Test per addAttrezzo ---

    @Test
    void testAddAttrezzoStanzaNonPiena() {
        // Aggiunge un attrezzo e verifica che il metodo restituisca true
        boolean aggiunto = stanzaSemplice.addAttrezzo(attrezzo1);
        assertTrue(aggiunto, "L'aggiunta dell'attrezzo dovrebbe restituire true.");
        // Verifica che l'attrezzo sia effettivamente presente
        assertTrue(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_1), "L'attrezzo aggiunto non è stato trovato.");
        // Verifica che numeroAttrezzi sia aumentato (indirettamente tramite getAttrezzi)
         assertSame(attrezzo1, stanzaSemplice.getAttrezzi()[0]);
    }

    @Test
    void testAddAttrezzoStanzaPiena() {
        // Riempie la stanza fino alla capacità massima
        int maxAttrezzi = 10; // Basato sulla costante nella classe Stanza
        for (int i = 0; i < maxAttrezzi; i++) {
            assertTrue(stanzaSemplice.addAttrezzo(new Attrezzo("Attrezzo " + i, 1)), "Aggiunta attrezzo " + i + " fallita.");
        }
        // Prova ad aggiungere un ulteriore attrezzo
        boolean aggiunto = stanzaSemplice.addAttrezzo(attrezzo2);
        // Verifica che l'aggiunta fallisca (restituisce false)
        assertFalse(aggiunto, "L'aggiunta dell'attrezzo in stanza piena dovrebbe restituire false.");
        // Verifica che l'attrezzo extra non sia stato aggiunto
        assertFalse(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_2), "L'attrezzo extra non dovrebbe essere nella stanza.");
    }


    // --- Test per hasAttrezzo ---

    @Test
    void testHasAttrezzoPresente() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        // Verifica che hasAttrezzo trovi l'attrezzo aggiunto
        assertTrue(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_1), "hasAttrezzo dovrebbe trovare l'attrezzo presente.");
    }

    @Test
    void testHasAttrezzoNonPresente() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        // Verifica che hasAttrezzo non trovi un attrezzo non aggiunto
        assertFalse(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_2), "hasAttrezzo non dovrebbe trovare un attrezzo non presente.");
        // Verifica anche su stanza completamente vuota
        assertFalse(new Stanza("Vuota").hasAttrezzo(NOME_ATTREZZO_1), "hasAttrezzo su stanza vuota dovrebbe restituire false.");
    }

    // --- Test per getAttrezzo ---

    @Test
    void testGetAttrezzoPresente() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        stanzaSemplice.addAttrezzo(attrezzo2);
        // Verifica che getAttrezzo restituisca l'oggetto Attrezzo corretto
        Attrezzo ottenuto = stanzaSemplice.getAttrezzo(NOME_ATTREZZO_1);
        assertSame(attrezzo1, ottenuto, "getAttrezzo non ha restituito l'attrezzo corretto.");
    }

    @Test
    void testGetAttrezzoNonPresenteONull() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        // Verifica che getAttrezzo restituisca null se l'attrezzo non c'è
        Attrezzo ottenuto = stanzaSemplice.getAttrezzo(NOME_ATTREZZO_2);
        assertNull(ottenuto, "getAttrezzo dovrebbe restituire null per un attrezzo non presente.");
         // Verifica che getAttrezzo restituisca null se il nome è null
         ottenuto = stanzaSemplice.getAttrezzo(null);
         assertNull(ottenuto, "getAttrezzo dovrebbe restituire null se il nome è null.");
    }

    // --- Test per removeAttrezzo ---

    @Test
    void testRemoveAttrezzoPresente() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        stanzaSemplice.addAttrezzo(attrezzo2);
        // Rimuove un attrezzo e verifica che il metodo restituisca true
        boolean rimosso = stanzaSemplice.removeAttrezzo(attrezzo1);
        assertTrue(rimosso, "La rimozione di un attrezzo presente dovrebbe restituire true.");
        // Verifica che l'attrezzo sia stato effettivamente rimosso
        assertFalse(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_1), "L'attrezzo rimosso è ancora presente.");
        // Verifica che l'altro attrezzo sia ancora lì
        assertTrue(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_2), "L'altro attrezzo è stato rimosso erroneamente.");
        // Verifica che numeroAttrezzi sia diminuito (indirettamente)
        assertNull(stanzaSemplice.getAttrezzi()[1], "Lo slot successivo all'attrezzo rimanente dovrebbe essere null dopo la rimozione.");

    }

    @Test
    void testRemoveAttrezzoNonPresente() {
        stanzaSemplice.addAttrezzo(attrezzo1);
        // Prova a rimuovere un attrezzo non presente
        Attrezzo attrezzoNonPresente = new Attrezzo("Fantasma", 1);
        boolean rimosso = stanzaSemplice.removeAttrezzo(attrezzoNonPresente);
        // Verifica che il metodo restituisca false
        assertFalse(rimosso, "La rimozione di un attrezzo non presente dovrebbe restituire false.");
        // Verifica che l'attrezzo originale sia ancora lì
        assertTrue(stanzaSemplice.hasAttrezzo(NOME_ATTREZZO_1), "L'attrezzo originale non dovrebbe essere stato rimosso.");
    }


    // --- Test per getDirezioni ---

    @Test
    void testGetDirezioniStanzaSenzaUscite() {
        String[] direzioni = stanzaSemplice.getDirezioni();
        // Verifica che l'array non sia null
        assertNotNull(direzioni, "L'array delle direzioni non può essere null.");
        // Verifica che l'array sia vuoto
        assertEquals(0, direzioni.length, "La stanza senza uscite dovrebbe avere un array di direzioni di lunghezza 0.");
    }

    @Test
    void testGetDirezioniStanzaConUscite() {
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_NORD, stanzaNord);
        stanzaSemplice.impostaStanzaAdiacente(DIREZIONE_SUD, stanzaSud);
        String[] direzioni = stanzaSemplice.getDirezioni();
        // Verifica che la lunghezza sia corretta
        assertEquals(2, direzioni.length, "La stanza dovrebbe avere 2 direzioni.");
        // Verifica che le direzioni siano presenti (l'ordine potrebbe variare)
        // Un modo è usare un Set per ignorare l'ordine
        java.util.Set<String> setDirezioni = new java.util.HashSet<>(java.util.Arrays.asList(direzioni));
        assertTrue(setDirezioni.contains(DIREZIONE_NORD), "La direzione NORD manca.");
        assertTrue(setDirezioni.contains(DIREZIONE_SUD), "La direzione SUD manca.");
    }
}