import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import static org.junit.jupiter.api.Assertions.*;

public class BorsaTest {

    @Test
    void testCostruttoreDefault() {
        Borsa borsa = new Borsa();
        assertEquals(10, borsa.getPesoMax());
        assertEquals(0, borsa.getPeso());
        assertTrue(borsa.isEmpty());
    }

    @Test
    void testCostruttoreConPesoMax() {
        Borsa borsa = new Borsa(20);
        assertEquals(20, borsa.getPesoMax());
        assertEquals(0, borsa.getPeso());
        assertTrue(borsa.isEmpty());
    }

    @Test
    void testAddAttrezzoSuccesso() {
        Borsa borsa = new Borsa();
        Attrezzo spada = new Attrezzo("spada", 5);
        assertTrue(borsa.addAttrezzo(spada));
        assertEquals(5, borsa.getPeso());
        assertFalse(borsa.isEmpty());
        assertTrue(borsa.hasAttrezzo("spada"));
    }

    @Test
    void testAddAttrezzoPesoEccessivo() {
        Borsa borsa = new Borsa(4);
        Attrezzo spada = new Attrezzo("spada", 5);
        assertFalse(borsa.addAttrezzo(spada));
        assertEquals(0, borsa.getPeso());
        assertTrue(borsa.isEmpty());
        assertFalse(borsa.hasAttrezzo("spada"));
    }


    @Test
    void testGetAttrezzoPresente() {
        Borsa borsa = new Borsa();
        Attrezzo ascia = new Attrezzo("ascia", 7);
        borsa.addAttrezzo(ascia);
        Attrezzo attrezzoTrovato = borsa.getAttrezzo("ascia");
        assertNotNull(attrezzoTrovato);
        assertEquals("ascia", attrezzoTrovato.getNome());
        assertEquals(7, attrezzoTrovato.getPeso());
    }

    @Test
    void testGetAttrezzoNonPresente() {
        Borsa borsa = new Borsa();
        assertNull(borsa.getAttrezzo("martello"));
    }

    @Test
    void testGetPesoBorsaVuota() {
        Borsa borsa = new Borsa();
        assertEquals(0, borsa.getPeso());
    }

    @Test
    void testGetPesoConAttrezzi() {
        Borsa borsa = new Borsa();
        borsa.addAttrezzo(new Attrezzo("libro", 2));
        borsa.addAttrezzo(new Attrezzo("penna", 1));
        assertEquals(3, borsa.getPeso());
    }

    @Test
    void testIsEmptyVera() {
        Borsa borsa = new Borsa();
        assertTrue(borsa.isEmpty());
    }

    @Test
    void testIsEmptyFalsa() {
        Borsa borsa = new Borsa();
        borsa.addAttrezzo(new Attrezzo("chiave", 3));
        assertFalse(borsa.isEmpty());
    }

    @Test
    void testHasAttrezzoPresente() {
        Borsa borsa = new Borsa();
        borsa.addAttrezzo(new Attrezzo("arco", 3));
        assertTrue(borsa.hasAttrezzo("arco"));
    }

    @Test
    void testHasAttrezzoNonPresente() {
        Borsa borsa = new Borsa();
        assertFalse(borsa.hasAttrezzo("scudo"));
    }

    @Test
    void testRemoveAttrezzoPresente() {
        Borsa borsa = new Borsa();
        Attrezzo pozione = new Attrezzo("pozione", 1);
        borsa.addAttrezzo(pozione);
        Attrezzo rimosso = borsa.removeAttrezzo("pozione");
        assertNotNull(rimosso);
        assertEquals("pozione", rimosso.getNome());
        assertEquals(0, borsa.getPeso());
        assertTrue(borsa.isEmpty());
        assertFalse(borsa.hasAttrezzo("pozione"));
    }

    @Test
    void testRemoveAttrezzoNonPresente() {
        Borsa borsa = new Borsa();
        Attrezzo rimosso = borsa.removeAttrezzo("anello");
        assertNull(rimosso);
        assertTrue(borsa.isEmpty());
    }

    @Test
    void testRemoveAttrezzoTraAltri() {
        Borsa borsa = new Borsa();
        borsa.addAttrezzo(new Attrezzo("oggetto1", 1));
        Attrezzo oggetto2 = new Attrezzo("oggetto2", 2);
        borsa.addAttrezzo(oggetto2);
        borsa.addAttrezzo(new Attrezzo("oggetto3", 3));

        Attrezzo rimosso = borsa.removeAttrezzo("oggetto2");
        assertNotNull(rimosso);
        assertEquals("oggetto2", rimosso.getNome());
        assertEquals(4, borsa.getPeso());
        assertTrue(borsa.hasAttrezzo("oggetto1"));
        assertFalse(borsa.hasAttrezzo("oggetto2"));
        assertTrue(borsa.hasAttrezzo("oggetto3"));
    }

    @Test
    void testToStringBorsaVuota() {
        Borsa borsa = new Borsa();
        assertEquals("Borsa vuota", borsa.toString());
    }

    @Test
    void testToStringBorsaConAttrezzi() {
        Borsa borsa = new Borsa(15);
        borsa.addAttrezzo(new Attrezzo("martello", 6));
        borsa.addAttrezzo(new Attrezzo("chiodo", 1));
        assertEquals("Contenuto borsa(7kg/15kg): [martello (6kg) - chiodo (1kg)]", borsa.toString());
    }
}


