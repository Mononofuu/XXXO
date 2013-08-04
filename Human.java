package com.mono.tictactoegame;


import com.mono.tictactoegame.Field.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Human extends Player {

    static boolean firstTurn = true;

    @Override
    void makeTurn(Field field) {
        if (firstTurn) field.printField();
        if (firstTurn == false){
            System.out.println("Повторить ход? [Y/N]");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try {
                input = reader.readLine().toUpperCase();
                if (input.equals("Y")) field.rewindOneTurn();
                else;
            } catch (IOException e) {
            }
        }
        System.out.println("Ваш ход:");
        field.x = humanInput("по горизонтали[1,2,3]:");
        field.y = humanInput("\nпо вертикали[1,2,3]:");

        if (field.getCellState() != State.EMPTY_CELL) {
            System.out.println("Ячейка занята. Введите новые координаты");
            makeTurn(field);
        } else field.setCellState(mark);
        field.history.add(new ArrayList<Integer>());
        field.history.get(field.history.size()-1).add(field.x);
        field.history.get(field.history.size()-1).add(field.y);
        firstTurn = false;
    }

    @Override
    public String toString() {
        System.out.println("Победил человек!");
        return null;
    }

    private int humanInput(String message) {
        int humanInputValue;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println(message);
                String stringInput = reader.readLine();
                if (stringInput.equals("1") || stringInput.equals("2") || stringInput.equals("3")) {
                    humanInputValue = Integer.parseInt(stringInput);
                    break;
                }else System.out.println("Неверный ввод. Введите цифру 1 или 2 или 3");
            } catch (IOException e) {
            }
        }
        return humanInputValue;
    }

    public Human(State state) {
        super(state);
    }

    public Human() {
    }
}
