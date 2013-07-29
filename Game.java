package com.mono.tictactoegame;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Game

{
    boolean playover = true;
    boolean draw = false;
    Field field = new Field();
    Player computer;
    Player human;
    Player human2;
    int mode;

    static void selectGameType(Game game)
    {
        System.out.println("TIC TAC TOE\n Выберите режим игры:\n 1.Человек против компьютера\n 2.Человек против человека");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true)
        {
            try
            {
                input = reader.readLine();
                if (input.equals("1") || input.equals("2"))
                {
                    game.setMode(Integer.parseInt(input));
                    break;
                } else System.out.println("Введите 1 или 2:");

            }
            catch (IOException e)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        boolean again = true;
        while (again)
        {
            again = false;
            Game game = new Game();
            selectGameType(game);
            if (game.getMode() == 1) game.startHumanVsComputerGame();
            else game.startHumanVsHumanGame();
            System.out.println("Еще сыграем? [Y/N]");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try
            {
                input = reader.readLine().toUpperCase();
                if (input.equals("Y") || input.equals("N"))
                {
                    again = input.equals("Y");
                } else System.out.println("THE END");
            }
            catch (IOException e)
            {
            }
        }
    }

    public int getMode()
    {
        return mode;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }

    void selectMark()
    {
        System.out.println("Чем играете? Крестик или нолик? Введите X или O:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true)
        {
            try
            {
                input = reader.readLine().toUpperCase();
                if (input.equals("X") || input.equals("O"))
                {
                    human.setMark(input.equals("X") ? Field.State.X : Field.State.O);
                    computer.setMark(input.equals("X") ? Field.State.O : Field.State.X);
                    break;
                }

                if (input.charAt(0) == '0')
                {
                    human.setMark(Field.State.O);
                    computer.setMark(Field.State.X);
                    break;
                } else System.out.println("Введите X или O:");
            }
            catch (StringIndexOutOfBoundsException se)
            {
                System.out.println("Введите X или O:");
            }
            catch (IOException e)
            {
            }
        }
    }

    public void startHumanVsComputerGame()
    {
        human = new Human();
        computer = new Computer();
        selectMark();
        Player player1;
        Player player2;
        player1 = (human.getMark().equals(Field.State.X)) ? human : computer;
        player2 = (human.getMark().equals(Field.State.O)) ? human : computer;

        while (playover)
        {
            player1.makeTurn(field);
            gameOverCheck();
            if (playover == false)
            {
                player1.toString();
                break;
            }
            if (draw)
            {
                System.out.println("НИЧЬЯ!");
                break;
            }
            player2.makeTurn(field);
            gameOverCheck();
            if (playover == false)
            {
                player2.toString();
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

    public void startHumanVsHumanGame()
    {
        human = new Human(Field.State.X);
        human2 = new Human(Field.State.O);

        while (playover)
        {
            System.out.println("Игрок 1 ходит");
            human.makeTurn(field);
            gameOverCheck();
            if (playover == false)
            {
                human.toString();
                break;
            }
            if (draw)
            {
                System.out.println("НИЧЬЯ!");
                break;
            }
            System.out.println("Игрок 2 ходит");
            human2.makeTurn(field);
            gameOverCheck();
            if (playover == false)
            {
                human2.toString();
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

    public void gameOverCheck()
    {
        int count = 0;
        int countDiag1 = 0;
        int countDiag2 = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (field.cells[i][j] == Field.State.EMPTY_CELL) count++;
            }
            if ((field.cells[i][0] == field.cells[i][1]) && (field.cells[i][1] == field.cells[i][2]) && (field.cells[i][2] != Field.State.EMPTY_CELL))
                playover = false;
            if ((field.cells[0][i] == field.cells[1][i]) && (field.cells[1][i] == field.cells[2][i]) && (field.cells[2][i] != Field.State.EMPTY_CELL))
                playover = false;
            if (field.cells[i][i].equals(Field.State.X)) countDiag1++;
            else if (field.cells[i][i].equals(Field.State.O)) countDiag1--;
            if (Math.abs(countDiag1) == 3) playover = false;

            if (field.cells[i][2 - i].equals(Field.State.X)) countDiag2++;
            else if (field.cells[i][i].equals(Field.State.O)) countDiag2--;
            if (Math.abs(countDiag2) == 3) playover = false;

        }

        if (count < 1)
        {
            draw = true;
        }
    }
}
