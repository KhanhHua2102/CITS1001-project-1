import static org.junit.Assert.*;
import org.junit.Test;

/**
 * TestFormula tests the class Formula.
 *
 * @author  Lyndon While
 * @version 2021 v1
 */
public class TestFormula
{
    String  s1 = "J";
    Formula c1 = new Formula(s1);
    String  s2 = "A34B";
    Formula c2 = new Formula(s2);
    String  s3 = "HL5P23";
    Formula c3 = new Formula(s3);

    @Test
    public void testFormula()
    {
        assertEquals("",   1, c1.getTerms().size());
        assertFalse ("",      c1.getTerms().get(0) == null);
        assertEquals("", 'J', c1.getTerms().get(0).getElement());
        assertEquals("",   1, c1.getTerms().get(0).getCount());
        
        assertEquals("",   2, c2.getTerms().size());
        assertFalse ("",      c2.getTerms().get(0) == null);
        assertEquals("", 'A', c2.getTerms().get(0).getElement());
        assertEquals("",  34, c2.getTerms().get(0).getCount());
        assertFalse ("",      c2.getTerms().get(1) == null);
        assertEquals("", 'B', c2.getTerms().get(1).getElement());
        assertEquals("",   1, c2.getTerms().get(1).getCount());
        
        assertEquals("",   3, c3.getTerms().size());
        assertFalse ("",      c3.getTerms().get(0) == null);
        assertEquals("", 'H', c3.getTerms().get(0).getElement());
        assertEquals("",   1, c3.getTerms().get(0).getCount());
        assertFalse ("",      c3.getTerms().get(1) == null);
        assertEquals("", 'L', c3.getTerms().get(1).getElement());
        assertEquals("",   5, c3.getTerms().get(1).getCount());
        assertFalse ("",      c3.getTerms().get(2) == null);
        assertEquals("", 'P', c3.getTerms().get(2).getElement());
        assertEquals("",  23, c3.getTerms().get(2).getCount());
    }

    @Test
    public void testlastUC()
    {
        assertEquals("", 0, c1.lastUC(s1));
        assertEquals("", 3, c2.lastUC(s2));
        assertEquals("", 3, c3.lastUC(s3));
    }

    @Test
    public void testcountElement()
    {
        assertEquals("",  0, c3.countElement('A'));
        assertEquals("",  5, c3.countElement('L'));
        assertEquals("", 23, c3.countElement('P'));
        assertEquals("",  3, new Formula("PPP").countElement('P'));
        assertEquals("",  4, new Formula("P2QP2").countElement('P'));
    }

    @Test
    public void teststandardise()
    {
        c1 = new Formula("AB34");
        c1.standardise();
        assertTrue("", c1.display().equals("AB34"));
        c1 = new Formula("B34A");
        c1.standardise();
        assertTrue("", c1.display().equals("AB34"));
        c1 = new Formula("B34AA");
        c1.standardise();
        assertTrue("", c1.display().equals("A2B34"));
        c1 = new Formula("B34AAAB");
        c1.standardise();
        assertTrue("", c1.display().equals("A3B35"));
    }
    
    @Test
    public void testisIsomer()
    {
        assertTrue ("", c3.isIsomer(new Formula("HL5P23")));
        assertTrue ("", c3.isIsomer(new Formula("P23LL4H")));
        assertTrue ("", c3.isIsomer(new Formula("LP2LP14LHLP7L")));
        assertFalse("", c3.isIsomer(new Formula("HM5P23")));
        assertFalse("", c3.isIsomer(new Formula("HL5P22")));
        assertFalse("", c3.isIsomer(new Formula("H2L5P23")));
    }
    
    @Test
    public void testFormulaDisplay()
    {
        assertTrue("", c1.display().equals(s1));
        assertTrue("", c2.display().equals(s2));
        assertTrue("", c3.display().equals(s3));
    } 
}
