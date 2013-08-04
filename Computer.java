package com.mono.tictactoegame;


import com.mono.tictactoegame.Field.State;

import java.util.ArrayList;


public class Computer extends Player {
    @Override
    void makeTurn(Field field) {
        System.out.println("\nКомпьютер ходит:");
        while (true) {
            field.x = (int) (Math.random() * 3 + 1);
            field.y = (int) (Math.random() * 3 + 1);
            if (field.getCellState() == State.EMPTY_CELL) {
                field.setCellState(mark);
                break;
            }
        }

        field.history.add(new ArrayList<Integer>());
        field.history.get(field.history.size()-1).add(field.x);
        field.history.get(field.history.size()-1).add(field.y);
    }

    @Override
    public String toString() {
        System.out.println("Вы проиграли. Победил компьютер!");
        return null;
    }

    public Computer(State state) {
        super(state);
    }

    public Computer() {
    }
}
