import kareltherobot.*;
import java.awt.Color;
import java.util.Scanner;
public class MiPrimerRobot implements Directions
{
    public static int RobotNumber;
    public static Color[] colorArray = {Color.red,Color.blue,Color.green,Color.magenta,Color.lightGray,Color.yellow,Color.red,Color.blue,Color.green,Color.magenta,Color.lightGray,Color.yellow,Color.green,Color.magenta,Color.yellow};
    public static void main(String [] args)
    {

        World.readWorld("Mundo.kwld");
        World.setVisible(true);

        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("write the number of robots to create, a number between 1-15");
            RobotNumber= scan.nextInt();
        }while (RobotNumber<1 || RobotNumber>15);

        Thread[] array =new Thread[RobotNumber];
        //create threads
        for(int i=0; i<RobotNumber;i++){
            array[i] = new Thread(new Racer(i+1, 1, East, 0,colorArray[i]));
            array[i].start();
        }


    }
}

