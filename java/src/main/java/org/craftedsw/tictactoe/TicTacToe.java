package org.craftedsw.tictactoe;

import org.craftedsw.tictactoe.model.board.Board;
import org.craftedsw.tictactoe.model.game.Opponent;
import org.craftedsw.tictactoe.model.strategy.GameStrategies;
import org.craftedsw.tictactoe.view.Console;

import static org.craftedsw.tictactoe.model.game.Player.PLAYER_ONE;
import static org.craftedsw.tictactoe.model.game.Player.PLAYER_TWO;
import static org.craftedsw.tictactoe.view.BoardDisplay.ASK_FOR_NEXT_MARK;
import static org.craftedsw.tictactoe.view.BoardDisplay.CELL_INDEX_INSTRUCTIONS;

public class TicTacToe {

    public final static String DRAW_MESSAGE = "It was a draw!!!";
    public static final String YOU_WIN = "You win!!!";
    public static final String YOU_LOSE = "You LOSE!!!";

    private final Console console;
    private final Board board;

    public TicTacToe(Console console, Board board) {
        this.console = console;
        this.board = board;
    }

    public void newSinglePlayerGame(Opponent opponent) {
        board.newGame();
        board.place(opponent.nextCell(board.marks()));
        while (!board.hasWinner() && !board.isFull()) {
            board.place(playerNextCell());
            if (!board.hasWinner()) {
                board.place(opponent.nextCell(board.marks()));
            }
        }
        displayGameResult();
    }

    private void displayGameResult() {
        if (board.hasWinner()) {
            console.print(board.winner() == PLAYER_TWO
                                ? YOU_WIN
                                : YOU_LOSE);
        } else {
            console.print(DRAW_MESSAGE);
        }
    }

    private int playerNextCell() {
        return console.ask(ASK_FOR_NEXT_MARK) - 1;
    }

    public static void main(String[] args) {
        Console console = new Console();
        TicTacToe ticTacToe = new TicTacToe(console, new Board(console));

        ticTacToe.newSinglePlayerGame(
                new Opponent(PLAYER_ONE, new GameStrategies()));
    }

}
