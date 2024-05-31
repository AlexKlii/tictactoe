package com.beater.game;

import com.beater.exceptions.InvalidInputException;

import java.util.Arrays;

import static com.beater.game.StringConstant.*;

/**
 * This class represents a Tic Tac Toe game.
 */
public class TicTacToe {

    /**
     * Indicates the current player's turn.
     */
    private Player playerTurn = Player.FIRST;

    /**
     * The 3x3 grid representing the Tic Tac Toe board.
     */
    private final char[][] grid = new char[][]{
            {PlayerSymbols.DEFAULT, PlayerSymbols.DEFAULT, PlayerSymbols.DEFAULT},
            {PlayerSymbols.DEFAULT, PlayerSymbols.DEFAULT, PlayerSymbols.DEFAULT},
            {PlayerSymbols.DEFAULT, PlayerSymbols.DEFAULT, PlayerSymbols.DEFAULT}
    };

    /**
     * Switches the turn to the other player.
     */
    public void switchPlayerTurn() {
        playerTurn = playerTurn == Player.FIRST ? Player.SECOND : Player.FIRST;
    }

    /**
     * Fills a cell with the correct symbol for the current player.
     *
     * @param cellNumber The number of the cell to fill (1-9).
     * @throws InvalidInputException if the cell is already filled
     *                               or if the number is out of range (should be between 1 and 9).
     */
    public void chooseCell(int cellNumber) throws InvalidInputException {
        if (cellNumber < 1 || cellNumber > 9) {
            throw new InvalidInputException("Enter a number between 1 and 9");
        }

        int row = (cellNumber - 1) / 3;
        int column = (cellNumber - 1) % 3;

        if (grid[row][column] != PlayerSymbols.DEFAULT) {
            throw new InvalidInputException("This cell is already filled");
        }

        grid[row][column] = playerTurn == Player.FIRST ? PlayerSymbols.FIRST : PlayerSymbols.SECOND;
    }

    /**
     * Checks if the game is a draw.
     *
     * @return true if the game is a draw, false otherwise.
     */
    public boolean checkDraw() {
        return !checkWin() && !Arrays.deepToString(grid).contains(PlayerSymbols.DEFAULT.toString());
    }

    /**
     * Checks if there is a winner.
     *
     * @return true if there is a winner, false otherwise.
     */
    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            boolean checkRow = grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]
                    && grid[i][2] != PlayerSymbols.DEFAULT;

            boolean checkColumn = grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]
                    && grid[2][i] != PlayerSymbols.DEFAULT;

            if (checkRow || checkColumn) return true;
        }

        // Check Diagonals
        return (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != PlayerSymbols.DEFAULT) ||
                (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != PlayerSymbols.DEFAULT);
    }

    /**
     * Gets the current player's turn.
     *
     * @return the current player's turn.
     */
    public Player getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Returns a string representation of the Tic Tac Toe board.
     *
     * @return a string representation of the board.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("TicTacToe Grid:").append(ROW_SEPARATOR);
        for (char[] row : grid) {
            for (char cell : row) {
                builder.append(SPACE).append(cell).append(SPACE);
            }
            builder.append(ROW_SEPARATOR);
        }

        return builder.toString();
    }
}