package org.craftedsw.tictactoe.model.strategy;

import org.craftedsw.tictactoe.model.game.PlayerMark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.craftedsw.tictactoe.model.game.PlayerMark.CROSS;

public class GameStrategies implements Iterable<Strategy> {

    private List<Strategy> strategies = new ArrayList<Strategy>();

    public GameStrategies(PlayerMark playerMark) {
        createListOfStrategiesFor(playerMark);
    }

    @Override
    public Iterator<Strategy> iterator() {
        return strategies.iterator();
    }

    public int size() {
        return strategies.size();
    }

    private void createListOfStrategiesFor(PlayerMark playerMark) {
        if (CROSS.equals(playerMark)) {
            prepareCrossStrategies();
        } else {
            prepareNoughtStrategies();
        }
    }

    private void prepareCrossStrategies() {
        strategies.add(new WinStrategy());
        strategies.add(new BlockStrategy());
        strategies.add(new ForkStrategy());
        strategies.add(new CornerAttackStrategy());
        strategies.add(new LineAttackStrategy());
        strategies.add(new FirstEmptyCellStrategy());
    }

    private void prepareNoughtStrategies() {
        strategies.add(new CentralCellStrategy());
        strategies.add(new WinStrategy());
        strategies.add(new BlockStrategy());
        strategies.add(new BlockingForkStrategy());
        strategies.add(new ForkStrategy());
        strategies.add(new CornerAttackStrategy());
        strategies.add(new LineAttackStrategy());
        strategies.add(new FirstEmptyCellStrategy());
    }

}
