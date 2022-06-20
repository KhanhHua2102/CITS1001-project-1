import static org.junit.Assert.*;
import org.junit.Test;

/**
 * TestEquation tests the class Equation.
 *
 * @author  Lyndon While
 * @version 2021 v2
 */
import java.util.ArrayList;

public class TestEquation
{
    @Test
    public void testEquation()
    {
        Equation eqn;
        
        eqn = new Equation("A+B2 = AB2");
        assertEquals("", 2, eqn.getLHS().size());
        for (int k = 0; k < 2; k++)
        {
            assertEquals("",       1, eqn.getLHS().get(k).getTerms().size());
            assertEquals("", 'A' + k, eqn.getLHS().get(k).getTerms().get(0).getElement());
            assertEquals("",   1 + k, eqn.getLHS().get(k).getTerms().get(0).getCount());
        }
        assertEquals("", 1, eqn.getRHS().size());
        assertEquals("", 2, eqn.getRHS().get(0).getTerms().size());
        for (int k = 0; k < 2; k++)
        {
            assertEquals("", 'A' + k, eqn.getRHS().get(0).getTerms().get(k).getElement());
            assertEquals("",   1 + k, eqn.getRHS().get(0).getTerms().get(k).getCount());
        }
        
        eqn = new Equation("A3X2 + B4X2 + C100D = A2D8 + DC100AX10");
        assertEquals("", 3, eqn.getLHS().size());
        for (int k = 0; k < 2; k++)
        {
            assertEquals("",       2, eqn.getLHS().get(k).getTerms().size());
            assertEquals("", 'A' + k, eqn.getLHS().get(k).getTerms().get(0).getElement());
            assertEquals("",   3 + k, eqn.getLHS().get(k).getTerms().get(0).getCount());
            assertEquals("",     'X', eqn.getLHS().get(k).getTerms().get(1).getElement());
            assertEquals("",       2, eqn.getLHS().get(k).getTerms().get(1).getCount());
        }
        assertEquals("",   2, eqn.getLHS().get(2).getTerms().size());
        for (int j = 0; j < 2; j++)
        {
            assertEquals("",      'C' + j, eqn.getLHS().get(2).getTerms().get(j).getElement());
            assertEquals("", 100 - 99 * j, eqn.getLHS().get(2).getTerms().get(j).getCount());
        }
        assertEquals("", 2, eqn.getRHS().size());
        assertEquals("", 2, eqn.getRHS().get(0).getTerms().size());
        for (int j = 0; j < 2; j++)
        {
            assertEquals("", 'A' + 3 * j, eqn.getRHS().get(0).getTerms().get(j).getElement());
            assertEquals("",   2 + 6 * j, eqn.getRHS().get(0).getTerms().get(j).getCount());
        }
        assertEquals("",   4, eqn.getRHS().get(1).getTerms().size());
        assertEquals("", 'D', eqn.getRHS().get(1).getTerms().get(0).getElement());
        assertEquals("",   1, eqn.getRHS().get(1).getTerms().get(0).getCount());
        assertEquals("", 'C', eqn.getRHS().get(1).getTerms().get(1).getElement());
        assertEquals("", 100, eqn.getRHS().get(1).getTerms().get(1).getCount());
        assertEquals("", 'A', eqn.getRHS().get(1).getTerms().get(2).getElement());
        assertEquals("",   1, eqn.getRHS().get(1).getTerms().get(2).getCount());
        assertEquals("", 'X', eqn.getRHS().get(1).getTerms().get(3).getElement());
        assertEquals("",  10, eqn.getRHS().get(1).getTerms().get(3).getCount());
    }
    
    // returns a comment on the lists xs and ys
    private String samelist(ArrayList<Integer> xs, ArrayList<Integer> ys)
    {
        if (xs == null)             return "1st list is null";
        if (ys == null)             return "2nd list is null";
        if (xs.size() != ys.size()) return "lists have different lengths";
        for (int k = 0; k < xs.size(); k++)
            if (xs.get(k).intValue() != ys.get(k).intValue()) 
               return "first difference is at Index " + k; 
        return "same lists";
    }
    
    @Test
    public void testindicesOf()
    {
        String s;
        ArrayList<Integer> ks = new ArrayList<>();
        s = samelist(Equation.indicesOf("AB3",   '+'), ks);
        assertTrue(s, s.equals("same lists"));
        ks.add(0);
        s = samelist(Equation.indicesOf("+AB3",  '+'), ks);
        assertTrue(s, s.equals("same lists"));
        ks.add(4);
        s = samelist(Equation.indicesOf("+AB3+", '+'), ks);
        assertTrue(s, s.equals("same lists"));
        ks.clear();
        ks.add(1);
        s = samelist(Equation.indicesOf("A+B3",  '+'), ks);
        assertTrue(s, s.equals("same lists"));
    }
    
    @Test
    public void testparseSide()
    {
        ArrayList<Formula> c; 
        ArrayList<Term> ts;
        
        c = Equation.parseSide("XY2"); 
        assertEquals("", 1, c.size()); 
        ts = c.get(0).getTerms();
        assertEquals("", 2, ts.size()); 
        for (int k = 0; k < 2; k++)
        {
            assertEquals("", 'X' + k, ts.get(k).getElement()); 
            assertEquals("",   1 + k, ts.get(k).getCount()); 
        }
        
        c = Equation.parseSide("X+Y2"); 
        assertEquals("", 2, c.size()); 
        for (int k = 0; k < 2; k++)
        {
            ts = c.get(k).getTerms();
            assertEquals("",       1, ts.size()); 
            assertEquals("", 'X' + k, ts.get(0).getElement()); 
            assertEquals("",   1 + k, ts.get(0).getCount()); 
        }
        
        c = Equation.parseSide("X + KY8 + LY4 + MY2"); 
        assertEquals("",   4, c.size()); 
        ts = c.get(0).getTerms();
        assertEquals("",   1, ts.size()); 
        assertEquals("", 'X', ts.get(0).getElement()); 
        assertEquals("",   1, ts.get(0).getCount()); 
        for (int k = 1; k < 4; k++)
        {
            ts = c.get(k).getTerms();
            assertEquals("",                        2, ts.size());
            assertEquals("",                'K' + k-1, ts.get(0).getElement()); 
            assertEquals("",                        1, ts.get(0).getCount()); 
            assertEquals("",                      'Y', ts.get(1).getElement()); 
            assertEquals("", 16 / (int) Math.pow(2,k), ts.get(1).getCount()); 
        }
    }
    
    @Test
    public void testisValid()
    {
        assertTrue("", new Equation("A + B = AB").isValid());
        assertTrue("", new Equation("A + B + BA = A2B2").isValid());
        assertTrue("", new Equation("A + BC2 + BC2 = AB + BC + C3").isValid());
        assertTrue("", new Equation("A + BC2 + BC2 = AB2C + C3").isValid());
        
        assertFalse("", new Equation("A + B = ABC").isValid());
        assertFalse("", new Equation("A + B + BC = AB+AB").isValid());
        assertFalse("", new Equation("A + BC2 + BC2 = AB + BC + C4").isValid());
        assertFalse("", new Equation("A + BC2 + BC2 = AB2C + C2").isValid());
    }

    @Test
    public void testEquationDisplay()
    {
        assertTrue("", new Equation("A + B = AB3").display().replace(" ","").equals
                                   ("A+B=AB3"));
        assertTrue("", new Equation("A + B+B = AB").display().replace(" ","").equals
                                   ("A+B+B=AB"));
        assertTrue("", new Equation("A13 + B500+B56 = A11B+AB222+AB333").display().replace(" ","").equals
                                   ("A13+B500+B56=A11B+AB222+AB333"));
    }
}
