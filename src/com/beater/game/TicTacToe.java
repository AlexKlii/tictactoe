package com.beater.game;

import static com.beater.game.StringConstant.LINE_SEPARATOR;
import static com.beater.game.StringConstant.SPACE;

public class TicTacToe {
    private final char[][] grid = new char[][]{
            { '.', '.', '.' },
            { '.', '.', '.' },
            { '.', '.', '.' }
    };

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("TicTacToe Grid:").append(LINE_SEPARATOR);
        for(char[] line: grid) {
            for(char cell: line) {
                builder.append(SPACE).append(cell).append(SPACE);
            }
            builder.append(LINE_SEPARATOR);
        }

        return builder.toString();
    }
}
