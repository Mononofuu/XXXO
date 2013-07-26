package XXXO;


import XXXO.Field.State;

public abstract class Player
{
    State mark;

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
