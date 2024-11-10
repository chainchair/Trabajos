import kareltherobot.*;
import kareltherobot.Robot;
import java.awt.*;

class Racer extends Robot implements RobotTask,Runnable{
    //attributes
    //int age=0;
    //constructor
    public Racer(int Street, int Avenue, Direction direction, int beepers, Color badge) {
        super(Street, Avenue, direction, beepers,badge);
        World.setupThread(this);
}

    @Override
    public void task() {
        //move to the front of the door
        int turnCounter=0;
        boolean a=true;
        while(a){
            if(turnCounter==4){

                move();
                move();
                a=false;
                break;
            }
            else if (frontIsClear()){
                move();
            }
            else if(turnCounter>=1 && turnCounter<4){
                turnLeft();
                turnLeft();
                turnLeft();
                turnCounter++;

            }
            else{
                turnLeft();
                turnCounter++;
            }

        }


    }
    public void run(){
        task();
    }
/*
    public void race()
    { while(! nextToABeeper()) move();
        pickBeeper();
        turnOff();
    }
    public void run()
    {
        race();
    }*/
}

