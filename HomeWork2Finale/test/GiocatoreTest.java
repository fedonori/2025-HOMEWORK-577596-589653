

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {

	private Giocatore giocatore;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception {
		giocatore = new Giocatore();
		attrezzo = new Attrezzo("martello", 2);
	}

	@Test
	void testGetCfuIniziali() {
		assertEquals(20, giocatore.getCfu());
	}

	@Test
	void testSetGetCfu() {
		giocatore.setCfu(10);
		assertEquals(10, giocatore.getCfu());
		giocatore.setCfu(0);
		assertEquals(0, giocatore.getCfu());
		giocatore.setCfu(-5);
		assertEquals(-5, giocatore.getCfu()); // I CFU possono essere negativi
	}

	@Test
	void testGetBorsaIniziale() {
		assertNotNull(giocatore.getBorsa());
		assertTrue(giocatore.getBorsa().isEmpty());
	}

	@Test
	void testSetGetBorsa() {
		Borsa nuovaBorsa = new Borsa(5);
		giocatore.setBorsa(nuovaBorsa);
		assertEquals(nuovaBorsa, giocatore.getBorsa());
		assertNotSame(new Borsa(), giocatore.getBorsa()); // Assicuriamoci che sia un'istanza diversa
	}

	@Test
	void testPrendereAttrezzoSuccesso() {
		assertTrue(giocatore.prendereAttrezzo(attrezzo));
		assertTrue(giocatore.getBorsa().hasAttrezzo("martello"));
		assertEquals(2, giocatore.getBorsa().getPeso());
	}

	@Test
	void testPrendereAttrezzoBorsaPiena() {
		Borsa borsaPiena = new Borsa(1);
		borsaPiena.addAttrezzo(new Attrezzo("chiodo", 1));
		giocatore.setBorsa(borsaPiena);
		Attrezzo attrezzoPesante = new Attrezzo("incudine", 5);
		assertFalse(giocatore.prendereAttrezzo(attrezzoPesante));
		assertFalse(giocatore.getBorsa().hasAttrezzo("incudine"));
	}

	@Test
	void testPosareAttrezzoSuccesso() {
		giocatore.prendereAttrezzo(attrezzo);
		Attrezzo attrezzoPosato = giocatore.posareAttrezzo("martello");
		assertNotNull(attrezzoPosato);
		assertEquals("martello", attrezzoPosato.getNome());
		assertFalse(giocatore.getBorsa().hasAttrezzo("martello"));
		assertEquals(0, giocatore.getBorsa().getPeso());
	}

	@Test
	void testPosareAttrezzoNonPresente() {
		Attrezzo attrezzoPosato = giocatore.posareAttrezzo("spada");
		assertNull(attrezzoPosato);
		assertFalse(giocatore.getBorsa().hasAttrezzo("spada"));
	}

}