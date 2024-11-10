import kareltherobot.*;
import java.awt.Color;
public class MiPrimerRobot implements Directions
{
    public static void main(String [] args)
    {
        World.readWorld("Mundo.kwld");
        World.setVisible(true);
        Racer first = new Racer(1, 1, East, 0,Color.red);
        Racer second = new Racer(2, 1, East, 0,Color.blue);
        first.task();
        second.task();

        first.turnOff();second.turnOff();
    }
}

