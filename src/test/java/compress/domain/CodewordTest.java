package compress.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CodewordTest {

    private Codeword cw;

    @Before
    public void setUp() {
        this.cw = new Codeword();
    }

    @Test
    public void initialValuesAreSane() {
        assertEquals(0, cw.getIndex());
    }

    @Test
    public void setGetTest() {
        cw.setNext(true);
        assertTrue(cw.get(0));
        cw.setNext(false);
        assertTrue(cw.get(1));
        assertFalse(cw.get(0));
        cw = new Codeword();
        cw.setNext(false);
        assertFalse(cw.get(0));
        cw.setNext(true);
        assertFalse(cw.get(1));
        assertTrue(cw.get(0));
    }


}
