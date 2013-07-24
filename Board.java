package XXXO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board
{
    boolean firstRun = true;
    static char X = 'X';
    static char O = 'O';
    static char EMPTY_CELL = ' ';
    static char[][] desk = new char[3][3];
    int x;
    int y;
    boolean playover = true;
    boolean draw = false;

    char getCurrentCell(){
        return this.desk[y - 1][x - 1];
    }

    void setCurrentCell(char value){
        this.desk[y - 1][x - 1] = value;
        printBoard();
    }

    public void printBoard()
    {
        for (int y = 0; y < this.desk.length; y++)
        {
            for (int x = 0; x < this.desk[y].length; x++)
            {
                if (firstRun){
                    this.desk[y][x] = EMPTY_CELL;
                    System.out.print(String.format("[%c]", this.desk[y][x]));
                }
                else System.out.print(String.format("[%c]", this.desk[y][x]));
            }
            System.out.println();
        }
        firstRun = false;
    }

    private int humanInput(String message){
        int humanInputValue;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try
            {
                System.out.println(message);
                String stringInput = reader.readLine();
                if (stringInput.equals("1") || stringInput.equals("2") || stringInput.equals("3")){
                    humanInputValue = Integer.parseInt(stringInput);
                    break;
                }
                else System.out.println("Неверный ввод. Введите цифру 1 или 2 или 3");
            } catch (IOException e){ }
        }
        return humanInputValue;
    }


    public void humanTurn()
    {
        System.out.println("Ваш ход:");
        x = humanInput("по горизонтали[1,2,3]:");
        y = humanInput("по вертикали[1,2,3]:");

        if (getCurrentCell() != EMPTY_CELL)
        {
            System.out.println("Ячейка занята. Введите новые координаты");
            humanTurn();
        } else setCurrentCell(X);

    }

    public void computerTurn()
    {
        System.out.println("Компьютер ходит:");
        while (true){
            x = (int) (Math.random() * 3 + 1);
            y = (int) (Math.random() * 3 + 1);
            if (getCurrentCell() == EMPTY_CELL){
                setCurrentCell(O);
                break;
            }
        }
    }

    public void gameOver()
    {
        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (desk[i][j] == EMPTY_CELL) count++;
            }
            if ((desk[i][0] == X && desk[i][1] == X && desk[i][2] == X) || (desk[i][0] == O && desk[i][1] == O && desk[i][2] == O))
                playover = false;
            if ((desk[0][i] == X && desk[1][i] == X && desk[2][i] == X) || (desk[0][i] == O && desk[1][i] == O && desk[2][i] == O))
                playover = false;
        }
        if ((desk[0][0] == X && desk[1][1] == X && desk[2][2] == X) || (desk[0][2] == X && desk[1][1] == X && desk[2][0] == X))
            playover = false;
        if ((desk[0][0] == O && desk[1][1] == O && desk[2][2] == O) || (desk[0][2] == O && desk[1][1] == O && desk[2][0] == O))
            playover = false;

        if (count < 1)
        {
            draw = true;
        }
    }

    public void startGame()
    {
        while (playover)
        {
            humanTurn();
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
            computerTurn();
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
        }
        System.out.println("Конец игры");
    }
}
