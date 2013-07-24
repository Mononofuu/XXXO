package XXXO;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board
{
    char[][] desk = new char[3][3];
    int x;
    int y;
    boolean playover = true;
    boolean draw = false;


    public void setXBoard(int x, int y){
        desk[y-1][x-1] = 'X';
        printBoard();
    }

    public void setOBoard(int x, int y){
        if (desk[x-1][y-1] == ' '){
            desk[x-1][y-1] = 'O';
            System.out.println("Компьютер ходит:");
            printBoard();
        }
        else computerTurn();

    }

    public void initBoard(){
        for (int y = 0; y<this.desk.length;y++){
            for (int x = 0; x<this.desk[y].length;x++){
                this.desk[y][x]=' ';
            }
        }
        printBoard();
    }

    public void printBoard(){
        for (int y = 0; y<this.desk.length;y++){
            for (int x = 0; x<this.desk[y].length;x++){
                System.out.print(String.format("[%c]", this.desk[y][x]));
            }
            System.out.println();
        }
    }


    public void humanTurn(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ваш ход:");
        System.out.println("по горизонтали[1,2,3]:");
        try
        {
            x = Integer.parseInt(reader.readLine());

        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("по вертикали[1,2,3]:");
        try
        {
            y = Integer.parseInt(reader.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        setXBoard(x,y);

    }

    public void computerTurn(){
        x =(int)(Math.random()*3+1);
        y =(int)(Math.random()*3+1);
        setOBoard(x, y);

    }

    public void gameOver(){
        int count = 0;
        for (int i=0;i<3;i++){
            for (int j = 0; j<3; j++){
                if (desk[i][j]==' ') count++;
            }
            if ((desk[i][0]=='X' && desk[i][1]=='X' && desk[i][2]=='X') || (desk[i][0]=='O' && desk[i][1]=='O' && desk[i][2]=='O'))  playover = false;
            if ((desk[0][i]=='X' && desk[1][i]=='X' && desk[2][i]=='X') || (desk[0][i]=='O' && desk[1][i]=='O' && desk[2][i]=='O'))  playover = false;
        }
        if ((desk[0][0]=='X' && desk[1][1]=='X' && desk[2][2]=='X') || (desk[0][2]=='X' && desk[1][1]=='X' && desk[2][0]=='X'))  playover = false;
        if ((desk[0][0]=='O' && desk[1][1]=='O' && desk[2][2]=='O') || (desk[0][2]=='O' && desk[1][1]=='O' && desk[2][0]=='O'))  playover = false;

        if (count<1){
            draw = true;
        }
    }

    public void startGame(){
        while (playover){
            humanTurn();
            gameOver();
            if (playover == false){
                System.out.println("ВЫ ПОБЕДИЛИ!");
                break;
            }
            if (draw){
                System.out.println("НИЧЬЯ!");
                break;
            }
            computerTurn();
            gameOver();
            if (playover == false){
                System.out.println("ВЫ ПРОИГРАЛИ!");
                break;
            }
            if (draw){
                System.out.println("НИЧЬЯ!");
                break;
            }
        }
        System.out.println("Конец игры");

    }
}
