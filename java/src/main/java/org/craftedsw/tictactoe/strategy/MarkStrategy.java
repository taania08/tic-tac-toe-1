package org.craftedsw.tictactoe.strategy;

import org.craftedsw.tictactoe.BoardLines;
import org.craftedsw.tictactoe.Line;

public class MarkStrategy {

    public static final int NONE = -1;
    private BoardLines boardLines = new BoardLines();

    public int winMark(String[] marks) {
        Line winningLine = boardLines.winningLine(marks);
        if (winningLine != null) {
            return winningLine.firstEmptyCell(marks);
        }
        return NONE;
    }

    public int defenceMark(String[] marks) {
        return NONE;
    }

}