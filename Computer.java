package XXXO;


import XXXO.Field.*;

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

    public Computer(State mark)
    {
        super();
        this.mark = mark;
    }

}
