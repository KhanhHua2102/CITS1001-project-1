/**
 * Formula represents a Bydysawd chemical formula. 
 * A formula is a sequence of terms, e.g. AX3YM67. 
 *
 * @author Lyndon While
 * @version 2021
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Formula
{
    // the constituent terms of the formula
    private ArrayList<Term> terms = new ArrayList<>();

    /**
     * Makes a formula containing a copy of terms.
     */
    public Formula(ArrayList<Term> terms)
    {
        this.terms = (ArrayList<Term>) terms.clone();
    }

    /**
     * Parses s to construct a formula. s will be a legal sequence 
     * of terms with no whitespace, e.g. "AX3YM67" or "Z".  
     * The terms in the field must be in the same order as in s. 
     */
    public Formula(String s)
    {
        int lastIndex = 0;
        for(int i = 1; i < s.length(); i++)
        {
            if(Character.isUpperCase(s.charAt(i)))
            {
                this.terms.add(new Term(s.substring(lastIndex, i)));
                lastIndex = i;
            }
        }
        this.terms.add(new Term(s.substring(lastIndex)));
    }

    /**
     * Returns the terms of the formula.
     */
    public ArrayList<Term> getTerms()
    {
        return terms;
    }
    
    /**
     * Returns the index in s where the rightmost upper-case letter sits, 
     * e.g. lastTerm("AX3YM67") returns 4. 
     * Returns -1 if there are no upper-case letters. 
     */
    public static int lastUC(String s)
    {
        for(int i = s.length() - 1; i >= 0; i--)
        {
            if(Character.isUpperCase(s.charAt(i)))
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns the total number of atoms of element in terms. 
     * e.g. if terms = <<W,2>,<X,1>,<W,5>>, countElement('W') returns 7, 
     * countElement('X') returns 1, and countElement('Q') returns 0.
     */
    public int countElement(char element)
    {
        int sumElement = 0;
        for(Term term: terms)
        {
            if(term.getElement() == element)
            {
                sumElement += term.getCount();
            }
        }
        return sumElement;
    }

    /**
     * Puts terms in standardised form, where each element present is 
     * represented by exactly one term, and terms are in alphabetical order.
     * e.g. <<C,3>,<D,1>,<B,2>,<D,2>,<C,1>> becomes <<B,2>,<C,4>,<D,3>>.
     */
    public void standardise()
    {
        ArrayList<Character> elements = new ArrayList<>();
        for(Term term: terms)
        {
            elements.add(term.getElement());
        }
        //Sort and remove duplicated element
        Collections.sort(elements);
        ArrayList<Character> deDupElements = new ArrayList<>(new HashSet<>(elements));
        ArrayList<Term> subTerms = new ArrayList<>();
        
        for(Character element: deDupElements)
        {
            subTerms.add(new Term(element, countElement(element)));
        }
        terms.clear();
        terms.addAll(subTerms);
    }

    /**
     * Returns true if this formula and other are isomers, 
     * i.e. they contain the same number of every Bydysawd element. 
     */
    public boolean isIsomer(Formula other)
    {
        standardise();
        other.standardise();
        if(display().equals(other.display()))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
     * Returns the formula as a String. 
     * e.g. if terms = <<B,22>,<E,1>,<D,3>>, it returns "B22ED3". 
     */
    public String display()
    {
        String display = "";
        for(Term term: terms)
        {
            String count = (term.getCount() == 1) ? "" : Integer.toString(term.getCount());
            display += term.getElement() + count;
        }
        return display;
    }
}