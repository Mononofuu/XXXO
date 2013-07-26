package XXXO;


public class Field
{
    int x;
    int y;
    static int fieldSize = 3;
    final State[][] cells = new State[fieldSize][fieldSize];



    static enum State
    {
        X("X"),
        O("O"),
        EMPTY_CELL(" ");
        private String displayName;

        State(String displayName)
        {
            this.displayName = displayName;
        }

        public String displayName()
        {
            return displayName;
        }
    }

    public Field()
    {
        for (int y = 0; y < cells.length; y++)
        {
            for (int x = 0; x < cells[y].length; x++)
            {
                cells[y][x] = State.EMPTY_CELL;
                System.out.print(String.format("[%s]", cells[y][x].displayName()));
            }
            System.out.println();
        }
    }

    State getCellState()
    {
        return cells[y - 1][x - 1];
    }

    void setCellState(State value)
    {
        this.cells[y - 1][x - 1] = value;
        printField();
    }

    public void printField()
    {
        for (int y = 0; y < this.cells.length; y++)
        {
            for (int x = 0; x < this.cells[y].length; x++)
            {
                System.out.print(String.format("[%s]", this.cells[y][x].displayName()));
            }
            System.out.println();
        }
    }

}
