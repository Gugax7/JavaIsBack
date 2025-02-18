import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(Parameterized.class)
public class UtilitiesTestParameterized {

    private String string;
    private String expected;

    public UtilitiesTestParameterized(String string, String expected) {
        this.string = string;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> setParameters(){
        return Arrays.asList(new Object[][]{
                {"ABCDEFF", "ABCDEF"},
                {"AB88EFFG", "AB8EFG"},
                {"112233445566", "123456"},
                {"ZYZQQB", "ZYZQB"},
                {"A", "A"}
        });
    }
    @Test
    public void removePairs() {
        Utilities util = new Utilities();
        System.out.println(util.removePairs(string) + " --> " + expected);
        assertEquals(expected,util.removePairs(string));
    }

}
