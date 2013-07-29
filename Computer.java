package com.mono.tictactoegame;

import com.mono.tictactoegame.Field.*;

public class Computer extends Player
{
    @Override
    void makeTurn(Field field)
    {
        System.out.println("Компьютер ходит:");
        while (true)
        {
            field.x = (int) (Math.random() * 3 + 1);
            field.y = (int) (Math.random() * 3 + 1);
            if (field.getCellState() == State.EMPTY_CELL)
            {
                field.setCellState(mark);
                break;
            }
        }
    }

    @Override
    public String toString()
    {
        System.out.println("Вы проиграли. Победил компьютер!");
        return null;
    }

    public Computer(State state)
    {
        super(state);
    }

    public Computer()
    {
    }
}
