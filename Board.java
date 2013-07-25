package XXXO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board
{
    static State[][] desk = new State[3][3];
    boolean firstRun = true;
    boolean humanPlaysX;
    int x;
    int y;
    boolean playover = true;
    boolean draw = false;

    State getCurrentCell()
    {
        return this.desk[y - 1][x - 1];
    }

    void setCurrentCell(State value)
    {
        this.desk[y - 1][x - 1] = value;
        printBoard();
    }

    public void printBoard()
    {
        for (int y = 0; y < this.desk.length; y++)
        {
            for (int x = 0; x < this.desk[y].length; x++)
            {
                if (firstRun)
                {
                    this.desk[y][x] = State.EMPTY_CELL;
                    System.out.print(String.format("[%s]", this.desk[y][x].displayName()));
                } else System.out.print(String.format("[%s]", this.desk[y][x].displayName()));
            }
            System.out.println();
        }
    }

    private int humanInput(String message)
    {
        int humanInputValue;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            try
            {
                System.out.println(message);
                String stringInput = reader.readLine();
                if (stringInput.equals("1") || stringInput.equals("2") || stringInput.equals("3"))
                {
                    humanInputValue = Integer.parseInt(stringInput);
                    break;
                } else System.out.println("Неверный ввод. Введите цифру 1 или 2 или 3");
            }
            catch (IOException e)
            {
            }
        }
        return humanInputValue;
    }

    public void humanTurn(State value)
    {
        System.out.println("Ваш ход:");
        x = humanInput("по горизонтали[1,2,3]:");
        y = humanInput("по вертикали[1,2,3]:");

        if (getCurrentCell() != State.EMPTY_CELL)
        {
            System.out.println("Ячейка занята. Введите новые координаты");
            humanTurn(value);
        } else setCurrentCell(value);

    }

    public void computerTurn(State value)
    {
        System.out.println("Компьютер ходит:");
        while (true)
        {
            x = (int) (Math.random() * 3 + 1);
            y = (int) (Math.random() * 3 + 1);
            if (getCurrentCell() == State.EMPTY_CELL)
            {
                setCurrentCell(value);
                break;
            }
        }
    }

    public void gameOver()
    {
        int count = 0;
        int countDiag1 = 0;
        int countDiag2 = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (desk[i][j] == State.EMPTY_CELL) count++;
            }
            if ((desk[i][0] == desk[i][1]) && (desk[i][1] == desk[i][2]) && (desk[i][2] != State.EMPTY_CELL))
                playover = false;
            if ((desk[0][i] == desk[1][i]) && (desk[1][i] == desk[2][i]) && (desk[2][i] != State.EMPTY_CELL))
                playover = false;
            if (desk[i][i].equals(State.X)) countDiag1++;
            else if (desk[i][i].equals(State.O)) countDiag1--;
            if (Math.abs(countDiag1) == 3) playover = false;

            if (desk[i][2 - i].equals(State.X)) countDiag2++;
            else if (desk[i][i].equals(State.O)) countDiag2--;
            if (Math.abs(countDiag2) == 3) playover = false;

        }

        if (count < 1)
        {
            draw = true;
        }
    }

    public void startGame()
    {
        while (playover)
        {
            if (firstRun)
            {
                System.out.println("Чем играете? Крестик или нолик? Введите X или O:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input;

                while (true)
                {
                    try
                    {
                        input = reader.readLine().toUpperCase();
                        if (input.equals("")) System.out.println("Введите X или O:");
                        if (input.equals("X") || input.equals("O"))
                        {
                            humanPlaysX = (input.equals("X") ? true : false);
                            break;
                        }

                        if (input.charAt(0) == '0')
                        {
                            humanPlaysX = false;
                            break;
                        } else System.out.println("Введите X или O:");
                    }
                    catch (IOException e)
                    {
                    }
                }
                firstRun = false;
            }

            if (humanPlaysX)
            {
                humanTurn(State.X);
                gameOver();
                if (playover == false)
                {
                    System.out.println("ВЫ ПОБЕДИЛИ!");
                    break;
                }
                if (draw)
                {
                    System.out.println("НИЧЬЯ!");
                    break;
                }
                computerTurn(State.O);
                gameOver();
                if (playover == false)
                {
                    System.out.println("ВЫ ПРОИГРАЛИ!");
                    break;
                }
                if (draw)
                {
                    System.out.println("НИЧЬЯ!");
                    break;
                }
            } else
            {
                computerTurn(State.X);
                gameOver();
                if (playover == false)
                {
                    System.out.println("ВЫ ПРОИГРАЛИ!");
                    break;
                }
                if (draw)
                {
                    System.out.println("НИЧЬЯ!");
                    break;
                }
                humanTurn(State.O);
                gameOver();
                if (playover == false)
                {
                    System.out.println("ВЫ ПОБЕДИЛИ!");
                    break;
                }
                if (draw)
                {
                    System.out.println("НИЧЬЯ!");
                    break;
                }
            }

        }
        System.out.println("Конец игры");
    }

    enum State
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
}
