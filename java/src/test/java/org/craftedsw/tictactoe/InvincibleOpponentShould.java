package org.craftedsw.tictactoe;

import org.craftedsw.tictactoe.strategy.AttackStrategy;
import org.craftedsw.tictactoe.strategy.MarkStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.craftedsw.tictactoe.Board.CELL_2;
import static org.craftedsw.tictactoe.Player.PLAYER_ONE;
import static org.craftedsw.tictactoe.Player.PLAYER_TWO;
import static org.craftedsw.tictactoe.strategy.MarkStrategy.NONE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class InvincibleOpponentShould {

    private static final int CELL_3 = 2;
    private InvincibleOpponent opponent;
    private MarkStrategy markStrategy = mock(MarkStrategy.class);
    private AttackStrategy attackStrategy = mock(AttackStrategy.class);
    private Board board = mock(Board.class);
    private String[] marks;

    @Before
    public void initialise() {
        this.marks =  new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
        when(board.marks()).thenReturn(marks);
        opponent = new InvincibleOpponent(PLAYER_ONE, markStrategy, attackStrategy);
        when(attackStrategy.nextMark(any(Player.class), any(Marks.class))).thenReturn(NONE);
    }

    @Test public void
    should_place_mark_on_first_empty_cell() {
        this.marks =  new String[]{"0", " ", "X", " ", " ", " ", " ", " ", " "};
        when(board.marks()).thenReturn(marks);
        when(markStrategy.winMark(PLAYER_ONE, marks)).thenReturn(NONE);
        when(markStrategy.defenceMark(PLAYER_TWO, marks)).thenReturn(NONE);

        int nextMark = opponent.nextCell(board);

        assertThat(nextMark, is(CELL_2));
    }

    @Test public void
    should_place_mark_according_to_winning_mark() {
        when(markStrategy.winMark(PLAYER_ONE, marks)).thenReturn(CELL_3);

        int nextMark = opponent.nextCell(board);

        assertThat(nextMark, is(CELL_3));
    }

    @Test public void
    should_defend_when_needed() {
        when(markStrategy.winMark(PLAYER_ONE, marks)).thenReturn(NONE);
        when(markStrategy.defenceMark(PLAYER_TWO, marks)).thenReturn(CELL_3);

        int nextMark = opponent.nextCell(board);

        assertThat(nextMark, is(CELL_3));
    }

}
