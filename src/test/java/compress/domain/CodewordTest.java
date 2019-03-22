package compress.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertTrue;

public class CodewordTest {

    private Codeword cw;

    @Before
    public void setUp() {
        this.cw = new Codeword();
    }

    @Test
    public void initialValuesAreSane() {
        assertTrue(cw.getIndex() == 0);
        assertTrue(cw.getBits().equals(new BitSet()));
    }
}
