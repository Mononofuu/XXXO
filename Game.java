package XXXO;


public class Game

{
    Field field = new Field();
    Computer p1 = new Computer(Field.State.X);
    Human p2 = new Human(Field.State.O);


    public void startGame()
    {
        p1.makeTurn(field);
        p2.makeTurn(field);
    }

}
