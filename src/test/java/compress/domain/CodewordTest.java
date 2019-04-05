package compress.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public class CodewordTest {

    private Codeword cw;

    @Before
    public void setUp() {
        this.cw = new Codeword();
    }

    @Test
    public void initialValuesAreSane() {
        assertEquals(0, cw.getIndex());
        assertEquals(cw.getBits(), new BitSet());
    }

    @Test
    public void toStringTest() {
        assertEquals("", cw.toString());
        cw.setNext(false);
        assertEquals("0", cw.toString());
        cw.setNext(true);
        assertEquals("10", cw.toString());
        cw.setNext(true);
        assertEquals("110", cw.toString());
    }

    @Test
    public void reverseTest() {
        cw.setNext(false);
        cw.setNext(true);
        assertEquals("10", cw.toString());
        cw.reverse();
        assertEquals("01", cw.toString());
        cw.setNext(true);
        assertEquals("101", cw.toString());
        cw.reverse();
        assertEquals("101", cw.toString());
    }
}
