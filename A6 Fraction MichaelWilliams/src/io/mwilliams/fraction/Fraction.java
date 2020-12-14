// Michael Williams
package io.mwilliams.fraction;

import static java.lang.Math.*;

public class Fraction
{
    private int numerator;
    private int denominator;

    /**
     * Parameterized constructor for Function class.
     * @param n Numerator
     * @param d Denominator
     */
    public Fraction(int n, int d)
    {
        this.numerator = n;
        this.denominator = d;
    }

    /**
     * Default constructor for Function class.
     */
    public Fraction()
    {
        this(1,1);
    }


    /**
     * Returns the greatest common denominator between two numbers.
     * @param n Numerator
     * @param d Denominator
     * @return GCD as a integer
     */
    private int gcd(int n, int d)
    {
        return (d == 0) ? n : gcd(d, n % d);
    }


    /**
     * Returns String in the form of a fractional number. (eg. 6/250)
     * @return String of the fractional number (eg. 6/250)
     */
    public String toString()
    {
        int n, d;
        n = abs(this.numerator);
        d = abs(this.denominator);

        // Checking for negative numbers and modifying as needed.
        if(this.numerator < 0 ^ this.denominator < 0)
        {
            n *= -1;
        }

        return n + "/" + d;
    }


    /**
     * Returns decimal equivalent of the object's fractional number.
     * @return Double datatype of the numerator divided by denominator.
     */
    public double getDecimal()
    {
        double n, d;
        n = abs(this.numerator);
        d = abs(this.denominator);

        // Checking for negative numbers and modifying as needed.
        if (this.numerator < 0.0 ^ this.denominator < 0.0)
        {
            n *= -1.0;
        }

        return n / d;
    }


    /**
     * Reduces object's fractional integers and updates with reduced integers.
     */
    public void reduce()
    {
        int n, d, gcd;
        n = abs(this.numerator);
        d = abs(this.denominator);
        gcd = gcd(n, d);

        n /= gcd;
        d /= gcd;

        // Checking for negative numbers and modifying as needed.
        if (this.numerator < 0 ^ this.denominator < 0)
        {
            n *= -1;
        }

        this.numerator = n;
        this.denominator = d;
    }

    /**
     * This method returns a String which includes the whole number and a
     * "reduced" fractional component.
     * @return String of reduced mixed number.
     */
    public String toMixed()
    {
        String output;
        int n, d, whole, remainder;
        reduce();
        n = abs(this.numerator);
        d = abs(this.denominator);

        // Checking if reduced fraction is whole, undefined, or mixed.
        if (d == 1)
        {
            output = Integer.toString(n);
        }
        else if (d == 0)
        {
            output = "undefined";
        }
        else
        {
            remainder = n % d;
            n -= remainder;
            whole = n / d;
            output = whole + " " + remainder + "/" + d;
        }

        return output;
    }
}
