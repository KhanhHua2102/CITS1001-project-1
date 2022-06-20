/**
 * Equation represents a Bydysawd chemical equation. 
 * An equation can have multiple formulas on each side, 
 * e.g. X3 + Y2Z2 = ZX + Y2X2 + Z. 
 *
 * @author Lyndon While
 * @version 2021
 */
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List; 
import java.util.Collections;    

public class Equation
{
    // the two sides of the equation 
    // there can be multiple formulas on each side 
    private ArrayList<Formula> lhs = new ArrayList<>();
    private ArrayList<Formula> rhs = new ArrayList<>();

    /**
     * Parses s to construct an equation. s will contain a 
     * syntactically legal equation, e.g. X3 + Y2Z = ZX + Y2X4. 
     * s may contain whitespace between formulas and symbols. 
     */
    public Equation(String s)
    {
        // Split s into 2 side and remove spaces
        String[] formulas = s.split("=");
        formulas[0] = formulas[0].replaceAll("\\s+","");
        formulas[1] = formulas[1].replaceAll("\\s+","");
        
        int lastIndex = 0;
        for(int i = 0; i < formulas[0].length(); i++)
        {
            if((Character.toString(formulas[0].charAt(i)).equals("+")))
            {
                lhs.add(new Formula(formulas[0].substring(lastIndex, i)));
                lastIndex = i + 1;
            }
        }
        lhs.add(new Formula(formulas[0].substring(lastIndex, formulas[0].length())));
        
        lastIndex = 0;
        for(int i = 0; i < formulas[1].length(); i++)
        {
            if((Character.toString(formulas[1].charAt(i)).equals("+")))
            {
                rhs.add(new Formula(formulas[1].substring(lastIndex, i)));
                lastIndex = i + 1;
            }
        }
        rhs.add(new Formula(formulas[1].substring(lastIndex, formulas[1].length())));
    }

    /**
     * Returns the  = left-hand side of the equation.
     */
    public ArrayList<Formula> getLHS()
    {
        return lhs;
    }

    /**
     * Returns the right-hand side of the equation.
     */
    public ArrayList<Formula> getRHS()
    {
        return rhs;
    }
    
    /**
     * Returns the indices at which x occurs in s, 
     * e.g. indicesOf("ax34x", 'x') returns <1,4>. 
     */
    public static ArrayList<Integer> indicesOf(String s, char x)
    {
        ArrayList<Integer> listIndex = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == x)
            {
                listIndex.add(i);
            }
        }
        return listIndex;
    }
    
    /**
     * Parses s as one side of an equation. 
     * s will contain a series of formulas separated by pluses, 
     * and it may contain whitespace between formulas and symbols. 
     */
    
    public static ArrayList<Formula> parseSide(String s)
    {
        ArrayList<Formula> oneSide = new ArrayList<>();
        s = s.replaceAll("\\s+", "");
        String term = "";
        int lastIndex = 0;
        
        for(int i = 0; i < s.length(); i++)
        {
            if( Character.toString(s.charAt(i)).equals("+") || i == s.length() - 1)
            {
                // Extensional parseSide
                if(Character.isDigit(s.charAt(lastIndex)))
                {
                    int countUpperCase = 0;
                    int firstElementIndex = 0;
                    int index = lastIndex;
                    while(countUpperCase < 1)
                    {
                        index++;
                        if(Character.isUpperCase(s.charAt(index)))
                        {
                            countUpperCase ++;
                            firstElementIndex = index;
                        }
                    }
                    if(i != s.length() - 1)
                    {
                        while(countUpperCase < 2)
                        {
                            index++;
                            if(Character.isUpperCase(s.charAt(index)) || Character.toString(s.charAt(i)).equals("+"))
                            {
                                countUpperCase ++;
                            }
                        }
                        if(firstElementIndex + 1 == index)
                        {
                            term += Character.toString(s.charAt(firstElementIndex)) + Integer.parseInt(s.substring(lastIndex, firstElementIndex)) * Integer.parseInt(Character.toString(s.charAt(index)));
                        }
                        else
                        {
                            term += Character.toString(s.charAt(firstElementIndex)) + Integer.parseInt(s.substring(lastIndex, firstElementIndex)) * Integer.parseInt(s.substring(firstElementIndex + 1, index));
                        }
                        index ++;
                        lastIndex = index;
                    }
                    else
                    {
                        if(firstElementIndex == i)
                        {
                            term += Character.toString(s.charAt(firstElementIndex)) + Integer.parseInt(s.substring(lastIndex, firstElementIndex)) * Integer.parseInt(Character.toString(s.charAt(i)));
                        }
                        else
                        {
                            term += Character.toString(s.charAt(firstElementIndex)) + Integer.parseInt(s.substring(lastIndex, firstElementIndex)) * Integer.parseInt(s.substring(firstElementIndex + 1, i+1));
                        }
                        lastIndex = i;
                        index += 2;
                    }
                    if(lastIndex != i)
                    {
                        term += s.substring(index, i);
                    }
                    lastIndex = i + 1;
                }
                
                // Normal parseSide
                else
                {
                    if(i != s.length() - 1)
                    {
                        term += s.substring(lastIndex, i);
                        lastIndex = i + 1;
                    }
                    else
                    {
                        term += s.substring(lastIndex, i + 1);
                    }
                }
                oneSide.add(new Formula(term));
                term = "";
            }
        }
        return oneSide;
    }

    /**
     * Returns true if the equation is balanced, i.e. it has the 
     * same number of atoms of each Bydysawd element on each side. 
     */
    public boolean isValid()
    {
        String lhsString = "";
        String rhsString = "";
        
        for(Formula formula: lhs)
        {
            lhsString += formula.display();
        }
        for(Formula formula: rhs)
        {
            rhsString += formula.display();
        }
        
        Formula lhsFormula = new Formula(lhsString);
        Formula rhsFormula = new Formula(rhsString);
        lhsFormula.standardise();
        rhsFormula.standardise();
        
        return (lhsFormula.display().equals(rhsFormula.display())) ? true : false;
    }

    /**
     * Returns the equation as a String.
     */
    public String display()
    {
        String displayString = "";
        ArrayList<String> lhsString = new ArrayList<>();
        ArrayList<String> rhsString = new ArrayList<>();
        for(Formula formula: lhs)
        {
            lhsString.add(formula.display());
        }
        for(Formula formula: rhs)
        {
            rhsString.add(formula.display());
        }
        displayString += String.join(" + ", lhsString);
        displayString += " = ";
        displayString += String.join(" + ", rhsString);

        return displayString;
    }
}
