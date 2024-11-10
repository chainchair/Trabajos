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
        //move fist four avenues
        move();move();move();move();
        //if there is no wall continue
        if(frontIsClear()){
            move();move();move();move();move();move();move();move();move();move();
        }//if there is a wall turn left and advance four streets, and return to init direction(east)
        else{
            turnLeft();
            move();move();move();move();
            turnLeft();turnLeft();turnLeft();
            //if there is no wall continue
            if(frontIsClear()){
                move();move();move();move();move();move();move();move();move();move();
            }//if there is a wall turn left and advance four streets, and return to init direction(east)
            else{
                turnLeft();
                move();move();move();move();
                turnLeft();turnLeft();turnLeft();
                move();move();move();move();move();move();move();move();move();move();
            }

        }
        turnLeft();turnLeft();turnLeft();
        move();move();move();move();move();move();move();move();
        while(frontIsClear()){
            move();
        }
        turnLeft();turnLeft();turnLeft();
        move();


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

