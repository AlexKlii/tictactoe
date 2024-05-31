import com.beater.exceptions.InvalidInputException;
import com.beater.game.TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.beater.game.StringConstant.NO;
import static com.beater.game.StringConstant.YES;

/**
 * The Main class contains the main method to run the Tic Tac Toe game.
 */
public class Main {

    private static final String PLAY_AGAIN_PROMPT = "Play again? (y/n)";
    private static final String ENTER_NUMBER_PROMPT = "Enter a number [1-9]:";
    private static final String INVALID_INPUT_MESSAGE = "Please enter a number between 1 and 9.";

    /**
     * The main method to start the Tic Tac Toe game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String tryAgain;
            do {
                final TicTacToe game = new TicTacToe();
                playGame(game, scanner);
                System.out.println(game);
                tryAgain = getValidInput(scanner);
            } while (tryAgain.equalsIgnoreCase(YES));
        }
    }

    /**
     * Plays the game by taking turns between players.
     *
     * @param game    the Tic Tac Toe game instance
     * @param scanner the Scanner instance for input
     */
    private static void playGame(TicTacToe game, Scanner scanner) {
        while (true) {
            try {
                System.out.println(game);
                System.out.println(game.getPlayerTurn() + " Player's turn.");
                System.out.println(ENTER_NUMBER_PROMPT);

                int playerInput = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                game.chooseCell(playerInput);

                if (game.checkWin()) {
                    System.out.println("Congratulations! " + game.getPlayerTurn() + " Player wins the game :)");
                    break;
                }

                if (game.checkDraw()) {
                    System.out.println("The game ended in a tie!");
                    break;
                }

                game.switchPlayerTurn();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
                scanner.next(); // Clear invalid input
            }
        }
    }

    /**
     * Prompts the player to enter a single character and loops until a valid input is provided.
     *
     * @param scanner the Scanner instance for input
     * @return the valid input entered by the player
     */
    private static String getValidInput(Scanner scanner) {
        String input;
        do {
            System.out.println(PLAY_AGAIN_PROMPT);
            input = scanner.nextLine();
        } while (!isValidInput(input));
        return input;
    }

    /**
     * Checks if the input is valid.
     *
     * @param input the input to check
     * @return true if the input is valid, false otherwise
     */
    private static boolean isValidInput(String input) {
        for (String validInput : new String[]{YES, NO}) {
            if (input.equalsIgnoreCase(validInput)) return true;
        }

        return false;
    }
}
