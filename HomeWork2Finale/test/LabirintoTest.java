

import static org.junit.jupiter.api.Assertions.*; // Import statico per usare metodi come assertEquals, assertNotNull

import org.junit.jupiter.api.BeforeEach; // Eseguito prima di ogni test
import org.junit.jupiter.api.Test; // Indica che un metodo è un test

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe di test per la classe Labirinto.
 */
public class LabirintoTest {

    private Labirinto labirinto;
    private Stanza stanzaInizialeAttesa;
    private Stanza stanzaVincenteAttesa;

    /**
     * Metodo di setup eseguito prima di ogni metodo di test.
     * Inizializza un nuovo Labirinto per garantire l'indipendenza dei test.
     */
    @BeforeEach
    public void setUp() {
        // Crea l'istanza di Labirinto che verrà usata nei test
        // Questo chiama implicitamente il metodo init() del Labirinto
        labirinto = new Labirinto();

        // Nomi attesi basati sul codice di init()
        stanzaInizialeAttesa = new Stanza("Atrio");
        stanzaVincenteAttesa = new Stanza("Biblioteca");
    }

    /**
     * Test del metodo getStanzaIniziale.
     * Verifica che la stanza iniziale restituita sia quella attesa ("Atrio").
     */
    @Test
    public void testGetStanzaIniziale() {
        // Verifica che la stanza iniziale non sia nulla
        assertNotNull(labirinto.getStanzaIniziale(), "La stanza iniziale non dovrebbe essere nulla");
        // Verifica che il nome della stanza iniziale sia corretto
        assertEquals(stanzaInizialeAttesa.getNome(), labirinto.getStanzaIniziale().getNome(),
                     "Il nome della stanza iniziale non corrisponde");
    }

    /**
     * Test del metodo getStanzaVincente.
     * Verifica che la stanza vincente restituita sia quella attesa ("Biblioteca").
     */
    @Test
    public void testGetStanzaVincente() {
        // Verifica che la stanza vincente non sia nulla
        assertNotNull(labirinto.getStanzaVincente(), "La stanza vincente non dovrebbe essere nulla");
        // Verifica che il nome della stanza vincente sia corretto
        assertEquals(stanzaVincenteAttesa.getNome(), labirinto.getStanzaVincente().getNome(),
                     "Il nome della stanza vincente non corrisponde");
    }

    /*
     * Potenziali test aggiuntivi (più complessi, potrebbero richiedere
     * di esplorare il labirinto o di rendere accessibili più dettagli interni,
     * oppure di testare la classe Stanza separatamente):
     *
     * - testNavigazione: Verifica che le connessioni tra le stanze siano corrette
     * partendo dall'atrio e muovendosi.
     * - testPresenzaOggetti: Verifica che gli oggetti siano nelle stanze corrette
     * (es. lanterna in Aula N10, osso in Atrio).
     */
}