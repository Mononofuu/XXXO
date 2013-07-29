package com.mono.tictactoegame;

import com.mono.tictactoegame.Field.*;


public abstract class Player
{
    protected State mark;

    abstract void makeTurn(Field field);
    public abstract String toString();

    public void setMark(State mark)
    {
        this.mark = mark;
    }

    public State getMark()
    {
        return mark;
    }

    protected Player(State state)
    {
        this.mark = state;
    }

    protected Player()
    {
    }
}
