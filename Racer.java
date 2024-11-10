import kareltherobot.*;
import kareltherobot.Robot;
import java.awt.*;

class Racer extends Robot implements RobotTask {
    //attributes
    //int age=0;
    //constructor
    public Racer(int Street, int Avenue, Direction direction, int beepers, Color badge) {
        super(Street, Avenue, direction, beepers,badge);
        World.setupThread(this);
}

    @Override
    public void task() {
        move();move();move();
        if(frontIsClear()){
            move();move();move();move();move();move();move();move();move();move();
        }else{
            turnLeft();
            move();move();move();move();
            turnLeft();turnLeft();turnLeft();
            if(frontIsClear()){
                move();move();move();move();move();move();move();move();move();move();
            }else{
                turnLeft();
                move();move();move();move();
                turnLeft();turnLeft();turnLeft();
                move();move();move();move();move();move();move();move();move();move();
            }

        }

    }

    public void race()
    { while(! nextToABeeper()) move();
        pickBeeper();
        turnOff();
    }
    public void run()
    {
        race();
    }
}

