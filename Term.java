/**
 * Term represents one term in a Bydysawd chemical formula. 
 * A term has the symbol for a Bydysawd element, i.e. an upper-case letter, 
 * optionally followed by a positive integer, e.g. S or F460. 
 *
 * @author Lyndon While
 * @version 2021
 */
public class Term
{
    // The element's symbol
    private char element;
    // How many atoms are there?
    private int count;

    /**
     * Makes a term containing element and count.
     */
    public Term(char element, int count)
    {
        this.element = element;
        this.count = count;
    }
    
    /**
     * Parses s to construct a term. s will be a legal term with no whitespace, 
     * e.g. "S" or "F460". If there is no number, it represents one atom. 
     */
    public Term(String s)
    {
        this.element = s.charAt(0);
        if(s.length() > 1)
        {
            this.count = Integer.parseInt(s.substring(1, s.length()));
        }
        else
        {
            this.count = 1;
        }
    }

    /**
     * Returns the element symbol.
     */
    public char getElement()
    {
        return element;
    }

    /**
     * Returns the number of atoms.
     */
    public int getCount()
    {
        return count;
    }

    /**
     * Returns the term as a String. Omits the number if it is 1. 
     */
    public String display()
    {
        if(count == 1)
        {
            return Character.toString(element);
        }
        else
        {
            return Character.toString(element) + count;
        }
    }
}
