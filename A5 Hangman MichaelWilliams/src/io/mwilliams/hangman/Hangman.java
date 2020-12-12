// Michael Williams
package io.mwilliams.hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman
{
    // Constants
    private static final char[] ALPHABET = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    // Modifier used to generate display; data does not need to change.
    private static final int[][] MODIFIER = new int[][]{
            {2, 6, 48},
            {3, 5, 47},
            {3, 6, 124},
            {3, 7, 92},
            {4, 6, 124},
            {5, 5, 47},
            {5, 7, 92}
    };


    /**
     * Creates new workspace for hangman game and returns the completed
     * workspace in the form of a multidimensional character array;
     * @return Multidimensional character array contain game data.
     */
    protected static char[][] start()
    {
        int index = 0;
        int count = 0;
        char[][] output = new char[5][26];
        char[] word;

        // Generate random index number to pick game word from word list.
        Random random = new Random();
        index = random.nextInt(Main.WORDS.length);

        // Setting alphabet and game word in workspace.
        System.arraycopy(ALPHABET, 0, output[0], 0, 26);
        word = Utility.explode(Main.WORDS[index].toUpperCase());

        for(int i = 0; i < word.length; i++)
        {
            output[1][i] = word[i];
            output[2][i] = '_';
            count++;
        }

        // Resetting counter data
        Main.counter[0] = (char)count;
        Main.counter[1] = 0;
        Main.counter[2] = 0;
        Main.counter[3] = 0;

        return output;
    }


    /**
     * Updates workspace based on player's guess.
     * @param workspace Game data as a multidimensional character array.
     * @param guess Single alphabetical character chosen by player.
     * @return Updated version of workspace character array.
     */
    protected static char[][] update(char[][] workspace, char guess)
    {
        int index = Utility.getAlphabetIndex(guess);

        // Marking guess character of the alphabet and adding to guess list.
        workspace[0][index] = '_';
        workspace[3][index] = guess;
        index = 0;

        // Unmasking guess letters from game word.
        for(char letter : workspace[1])
        {
            if(letter == guess)
            {
                workspace[2][index] = guess;
                Main.counter[3]++;
            }
            index++;
        }

        // Updating counter array with revised incorrect and correct int.
        if(!Utility.compareCharArray(workspace[1], guess))
        {
            Main.counter[2]++;
        }
        else
        {
            Main.counter[1]++;
        }

        return workspace;
    }


    /**
     * Regenerates display based on current guess and workspace data.
     * @param workspace Game data as a multidimensional character array.
     */
    protected static void display(char[][] workspace)
    {
        String[] display = {
                "+-----+   ",
                "|     |   ",
                "|         ",
                "|         ",
                "|         ",
                "|         ",
                "",
                ""
        };
        StringBuilder sb = new StringBuilder();

        // Generates modified display strings based on the number of incorrect
        // guesses.  Uses MODIFIER constant in this class.
        for(int i = 0; i < Main.counter[2]; i++)
        {
            sb.append(display[MODIFIER[i][0]]);
            sb.setCharAt(MODIFIER[i][1], (char)MODIFIER[i][2]);
            display[MODIFIER[i][0]] = sb.toString();
            sb.setLength(0);
        }

        //  Adding generated alphabet and game word strings to display.
        sb.append(display[1]);
        sb.append(Utility.spread(workspace[0]));
        display[1] = sb.toString();
        sb.setLength(0);
        sb.append(Utility.spread(workspace[2]));
        display[7] = sb.toString();

        // Printing revised display string to player.
        for(int i = 0; i < 8; i++)
        {
            System.out.println(display[i]);
        }
    }


    /**
     * Prompts player for letter, validates, then returns character, if valid.
     * @param workspace Game data in the form of a character array.
     * @return Character
     */
    protected static char guess(char[][] workspace)
    {
        Scanner prompt = new Scanner(System.in);
        char output = '0';

        while(output == '0')
        {
            // Prompting user for input and updating output with single
            // character.
            System.out.print("Choose a letter: ");
            output = prompt.next().toUpperCase().charAt(0);

            // Checking selected guess against the 26 letter english alphabet.
            if(!Utility.compareCharArray(Main.ALPHABET, output))
            {
                System.out.println(output + " is not a valid letter."
                        + "Try again.");
                output = '0';
            }
            // Checking input to see if guess has already been selected.
            else if(Utility.compareCharArray(workspace[3], output))
            {
                System.out.println(output + " has already been used."
                        + "Try again.");
                output = '0';
            }
        }

        return output;
    }


    /**
     * Checks state of game, provides results if game ended and asks player
     * if they would like to play again.  No terminates the program.
     * @param workspace Game data as a multidimensional character array.
     * @return Updated state int
     */
    protected static int status(char[][] workspace)
    {
        int state = 9;
        char input = '0';
        Scanner prompt = new Scanner(System.in);

        do
        {
            // Checking if game was won.
            if (Main.counter[0] == Main.counter[3])
            {
                System.out.print("\nYou Won! Try Again? (y/n): ");
                input = prompt.next().charAt(0);
            // Checking if game was lost.
            } else if (Main.counter[2] == 7)
            {
                System.out.print("\nYou Lose! The word was "
                        + String.valueOf(workspace[1]).trim()
                        + ". Try again? (y/n): ");
                input = prompt.next().charAt(0);
            }

            input = Character.toLowerCase(input);

            // Modifying state based off response.
            switch (input)
            {
                case 'y':
                    state = 0;
                    break;
                case 'n':
                    state = 5;
                    break;
                default:
                    if (input == '0')
                    {
                        state = 2;
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid Response.");
                    }
            }
        } while(state == 9);

        return state;
    }
}
