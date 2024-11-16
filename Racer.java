import kareltherobot.*;
import kareltherobot.Robot;
import java.awt.*;
import java.util.Stack;
import kareltherobot.Directions;

class Racer extends Robot implements RobotTask,Runnable,Directions{
    //constant attributes
    private static final int	EastVal   = 0;
    private static final int	infinity  =-1;
    private static final int	NorthVal  = 3;
    private static final int	SouthVal  = 1;
    private static final int	WestVal   = 2;
    public  static final int    exitStreet= 1;
    public  static final int    exitAvenue= 14;


    //stack for get the route followed for the robot
    final private Stack<Integer> steps;

    //control variables

    private int currentStreet;
    private int currentAvenue;

    private static final int maxBeepers = 4;
    private static int beepers=0;

    //constructor
    public Racer(int Street, int Avenue, Direction direction, int beepers, Color badge) {
        super(Street, Avenue, direction, beepers,badge);
        //Racer.beepers =this.beepers;
        this.currentStreet=Street;
        this.currentAvenue=Avenue;
        this.steps = new Stack<>();
        World.setupThread(this);
    }

    public int currentDirection(){
        if (facingEast()) {
            return EastVal;
        }
        else if (facingNorth()) {
            return NorthVal;
        }
        else if (facingWest()) {
            return WestVal;
        }
        else {
            return SouthVal;
        }
    }
    public void movement(int direction){
        switch (direction) {
            case EastVal : currentAvenue++; break;
            case WestVal : currentAvenue--; break;
            case NorthVal: currentStreet++; break;
            case SouthVal: currentStreet--; break;

        }
    }

    public void getBack(){
        while (!steps.isEmpty()) {  //execute while there is items remain in the stack
            int directionNow;
            directionNow = steps.pop();  //pop in the top of the stack

            switch (directionNow) {
                case EastVal:
                    if (currentDirection() != WestVal) {
                        do turnLeft();
                        while (!facingWest());
                    }
                    move();
                    break;

                case NorthVal:
                    if (currentDirection() != SouthVal) {
                        do turnLeft();
                        while (!facingSouth());

                    }
                    move();
                    break;

                case WestVal:
                    if (currentDirection() != EastVal) {
                        do turnLeft();
                        while (!facingEast());
                    }
                    move();
                    break;

                case SouthVal:
                    if (currentDirection() != NorthVal) {
                        do turnLeft();
                        while (!facingNorth());
                    }
                    move();
                    break;

                default:
                    break;
            }
        }
        turnLeft();
        turnLeft();
        move();
        turnOff();

    }
    @Override
    public void task() {
        int turnCounter=0;
        //move to the front of the door
        while(true){
            if(turnCounter==4){
                steps.push(currentDirection());
                movement(currentDirection());
                move();
                steps.push(currentDirection());
                movement(currentDirection());
                move();
                break;
            }
            else if (frontIsClear()){
                steps.push(currentDirection());
                movement(currentDirection());
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
        //move inside the building
        turnCounter=0;
        while(beepers<maxBeepers){
            if(currentStreet==exitStreet && currentAvenue==exitAvenue && beepers!=0 && beepers!=5 ){
                break;
            }
            while (nextToABeeper() && !nextToARobot()){
                    pickBeeper();
                    beepers++;
            }
            if(frontIsClear()){
                steps.push(currentDirection());
                movement(currentDirection());
                move();
                turnCounter=0;
            }
            else if (turnCounter==1) {
                turnLeft();
                turnLeft();
                turnCounter++;
            }
            else {
                turnLeft();
                turnCounter++;
            }
        }
        //return to initial position
        getBack();


    }
    public void run(){
        task();
    }


}
