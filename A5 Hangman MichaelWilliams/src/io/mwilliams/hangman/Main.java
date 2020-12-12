// Michael Williams
package io.mwilliams.hangman;

public class Main
{
    protected static final char[] ALPHABET = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
            'R','S','T','U','V','W','X','Y','Z'
    };
    protected static final String[] WORDS = {"javascript","declaration",
            "object","class","failing"};

    // Counter - [0] letter length / [1] correct / [2] incorrect / [3] complete
    protected static int[] counter = new int[4];
    // Game state - [0] New / [1] Status / [2] Display / [3] Input / [4] Process
    //              [5] Terminate
    protected static int state = 0;

    public static void main(String[] args)
    {
        char guess = '0';
        char[][] workspace = new char[4][26];

        do
        {
            switch (state)
            {
                case 1: // Checking game status and returning proper state.
                    state = Hangman.status(workspace);
                    continue;
                case 2: // Returns generated display based on current game data.
                    Hangman.display(workspace);
                    state = 3;
                    continue;
                case 3: // Prompting player for guess
                    guess = Hangman.guess(workspace);
                    state = 4;
                    continue;
                case 4: // Processing guess input and updating workspace.
                    workspace = Hangman.update(workspace, guess);
                    state = 1;
                    continue;
                case 5: // Terminates game.
                    System.out.println("Game Ending.  Thank you for playing.");
                    break;
                default: // Generates game with random word.
                    workspace = Hangman.start();
                    state = 1;
            }
        } while (state != 5);
    }
}
