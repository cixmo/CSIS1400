// Michael Williams
package io.mwilliams.fraction;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hardcoded array of Fraction objects
        Fraction[] testFracs = {
           new Fraction(-35,10),
           new Fraction(),
           new Fraction(125,30),
           new Fraction(-27,-9),
           new Fraction(36,0)
        };
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 5)
        {
            switch (choice)
            {
                case 0: // Selection prompt for end user.
                    System.out.println("\n1. Test the toString() method");
                    System.out.println("2. Test the reduce() method");
                    System.out.println("3. Test the toMixed() method");
                    System.out.println("4. Test the getDecimal() method");
                    System.out.println("5. Quit");
                    System.out.print("\n Choose between Option 1 - 5: ");
                    choice = input.nextInt();
                    continue;
                case 1: // Testing toString method
                    System.out.println("\nTesting toString Method:\n");
                    for (Fraction datum : testFracs)
                    {
                        System.out.println(datum.toString());
                    }
                    choice = 0;
                    continue;
                case 2: // Testing reduce method
                    System.out.println("\nTesting reduce Method: (displays"
                            + " nothing)\n");
                    for (Fraction datum : testFracs)
                    {
                        datum.reduce();
                    }
                    choice = 0;
                    continue;
                case 3: // Testing toMixed method
                    System.out.println("\nTesting toMixed Method:\n");
                    for (Fraction datum : testFracs)
                    {
                        System.out.println(datum.toMixed());
                    }
                    choice = 0;
                    continue;
                case 4: // Testing getDecimal method
                    System.out.println("\nTesting getDecimal Method:\n");
                    for (Fraction datum : testFracs)
                    {
                        System.out.println(datum.getDecimal());
                    }
                    choice = 0;
                    continue;
                default: // Notifies user of invalid choice and returns to menu
                    System.out.println("\n" + choice + " is a invalid choice.\n");
                    choice = 0;
            }
        }
    }
}
