// Michael Williams
package io.mwilliams.hangman;

public class Utility
{


    /**
     * Converts string to character array.
     *
     * @param word Word in the form of a string.
     * @return char[]
     */
    protected static char[] explode(String word)
    {
        char[] output = new char[word.length()];

        for (int i = 0; i < word.length(); i++)
        {
            output[i] = word.charAt(i);
        }

        return output;
    }


    /**
     * Formats character array with spaces between the characters.
     * @param arrayList Character array needing to be formatted.
     * @return String array with space modifications.
     */
    protected static String spread(char[] arrayList)
    {
        String output = new String(arrayList);
        return output.replace("", " ").trim();
    }


    /**
     * Compares character to character array and returns true if character is in array
     *
     * @param charArray Character array that needs to be compared.
     * @param character Character that needs to be compared to the character array
     * @return true: if character is in array, false: character is not in array
     */
    protected static boolean compareCharArray(char[] charArray, char character)
    {
        boolean isPresent = false;

        for (char item : charArray)
        {
            if (item == character)
            {
                isPresent = true;
                break;
            }
        }

        return isPresent;
    }


    /**
     * Checks alphabet for index location and returns parallel index location.
     * @param character Character that needs to correct index of the parallel array.
     * @return Integer of the alphabet index of the requested character.
     */
    protected static int getAlphabetIndex(char character)
    {
        int count = 0;

        for(char letter : Main.ALPHABET)
        {
            if(letter == character)
            {
                break;
            }
            count++;
        }

        return count;
    }
}