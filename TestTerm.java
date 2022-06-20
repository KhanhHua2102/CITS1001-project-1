import static org.junit.Assert.*;
import org.junit.Test;

/**
 * TestTerm tests the class Term.
 *
 * @author  Lyndon While
 * @version 2021 v1
 */
public class TestTerm
{
    Term t0   = new Term('L', Integer.MAX_VALUE);
    String s1 = "V";
    Term   t1 = new Term(s1);
    String s2 = "J2";
    Term   t2 = new Term(s2);
    String s3 = "L567";
    Term   t3 = new Term(s3);
    String s4 = "Z546456756";
    Term   t4 = new Term(s4);

    @Test
    public void testTerm1()
    {
        assertEquals("",        'L', t0.getElement());
        assertEquals("", 2147483647, t0.getCount());
    }

    @Test
    public void testTerm2()
    {
        assertEquals("", 'V', t1.getElement());
        assertEquals("",   1, t1.getCount());
        assertEquals("", 'J', t2.getElement());
        assertEquals("",   2, t2.getCount());
        assertEquals("", 'L', t3.getElement());
        assertEquals("", 567, t3.getCount());
        assertEquals("", 'Z', t4.getElement());
        assertEquals("", 546456756, t4.getCount());
    }

    @Test
    public void testTermDisplay()
    {
        assertTrue("", t0.display().equals("L2147483647"));
        assertTrue("", t1.display().equals(s1));
        assertTrue("", t2.display().equals(s2));
        assertTrue("", t3.display().equals(s3));
    }
}
