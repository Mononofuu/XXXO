package com.mono.tictactoegame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiGame extends JFrame {
    int[] field;
    Cell[] cells;
    int best;
    static final int SIZE = 3;
    int humanSide = 1, currentSide = 1;

    GuiGame() {
        super("Tic Tac Toe");
        field = new int[SIZE * SIZE];
        cells = new Cell[field.length];
        for (int i = 0; i < field.length; i++) {
            field[i] = 0;
            add(cells[i] = new Cell(i));

        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(SIZE,SIZE));
        setVisible(true);
        Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(scrDim.width / 4, scrDim.height / 4);
        setSize(scrDim.width / 2, scrDim.height / 2);

    }

    public int getField(int x, int y) {
        return field[y * SIZE + x];
    }

    public void setField(int x, int y, int value) {
        field[y * SIZE + x] = value;
    }

    int getX(int i) {
        return i % SIZE;
    }

    int getY(int i) {
        return i / SIZE;
    }

    class Cell extends JButton implements ActionListener {
        private int id;
        Dimension dim = new Dimension();

        Cell(int id) {
            this.id = id;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pushed(id);
        }

        void mark(int side) {
            field[id] = side;
            setEnabled(false);
            if (side > 0) setText("X");
            else setText("O");
        }
    }

    private void pushed(int id) {
        if(currentSide!=humanSide)
            return;
        cells[id].mark(currentSide);
        if(checkWin(getX(id),getY(id))){
            JOptionPane.showMessageDialog(this,"Победа!!!");
            return;
        }//if
        currentSide=-currentSide;
        assess(currentSide);
        if(best<0){
            JOptionPane.showMessageDialog(this,"Ничья");
            return;
        }//if
        cpuTurn();
    }

    boolean checkWin(int x,int y){
        int i,s;
        for(i=0,s=0;i<SIZE;i++)
            s+=getField(i, y);
        if(Math.abs(s)==SIZE)
            return true;
        for(i=0,s=0;i<SIZE;i++)
            s+=getField(x, i);
        if(Math.abs(s)==SIZE)
            return true;
        if(x==y){
            for(i=0,s=0;i<SIZE;i++)
                s+=getField(i, i);
            if(Math.abs(s)==SIZE)
                return true;
        }//if
        if(x+y+1==SIZE){
            for(i=0,s=0;i<SIZE;i++)
                s+=getField(i, SIZE - i - 1);
            if(Math.abs(s)==SIZE)
                return true;
        }//if
        return false;
    }//checkWin

    int assess(int side){
        int best=-1,bestVal=-2,curVal;
        for(int i=0;i<field.length;i++){
            if(field[i]!=0)
                continue;
            field[i]=side;
            if(checkWin(getX(i),getY(i))){
                field[i]=0;
                this.best=i;
                return side;
            }//if
            curVal=assess(-side);
            field[i]=0;
            if(curVal*side>bestVal){
                bestVal=curVal*side;
                best=i;
            }//if
        }//for
        this.best=best;
        if(best<0)
            return 0;
        return bestVal*side;
    }//assess

    void cpuTurn(){
        cells[best].mark(currentSide);
        if(checkWin(getX(best),getY(best))){
            JOptionPane.showMessageDialog(this,"Вы проиграли!");
            return;
        }//if
        currentSide=-currentSide;
        assess(currentSide);
        if(best<0){
            JOptionPane.showMessageDialog(this,"Ничья");
            return;
        }//if
    }//machineMove

    public static void main(String[] args) {
        new GuiGame();
    }
}
