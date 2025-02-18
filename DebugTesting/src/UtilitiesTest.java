import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilitiesTest {
    Utilities utilities = new Utilities();


    @Test
    public void everyNthChar() {
        //System.out.println(utilities.everyNthChar("hello".toCharArray(),2));
        assertArrayEquals("el".toCharArray(),utilities.everyNthChar("hello".toCharArray(),2));
        assertArrayEquals("hello".toCharArray(), utilities.everyNthChar("hello".toCharArray(),10));
    }

    @Test
    public void removePairs() {
        assertEquals("AHA", utilities.removePairs("AHHHAAAA"));
        assertEquals("ABC", utilities.removePairs("AAABBBCCC"));
        assertEquals("GUSTAVO", utilities.removePairs("GUSTAVO"));
        assertNull(null,utilities.removePairs(null));
        assertEquals("A", utilities.removePairs("A"));
        assertEquals("", utilities.removePairs(""));

    }

    @Test
    public void converter() {
        assertEquals(0, utilities.converter(10,0));
    }

    @Test
    public void nullIfOddLength() {
        assertNull("Ops, it should be null",utilities.nullIfOddLength("hello"));
        assertNotNull("Ops, it shouldn't be null", utilities.nullIfOddLength("Common"));
    }
}