import java.util.Random;
import java.util.ArrayList;
public class AIPlayer
{
    private Location previousGuess;
    private boolean wasHit;
    private Grid board;
    private ArrayList<Location> Eaircraftcarrier = new ArrayList<Location>();
    private ArrayList<Location> Ebattleship = new ArrayList<Location>();
    private ArrayList<Location> Esubmarine = new ArrayList<Location>();
    private ArrayList<Location> Edestroyer = new ArrayList<Location>();
    private ArrayList<Location> Epatrolboat = new ArrayList<Location>();
    
    public AIPlayer(Grid grid)
    {
        wasHit = false;
        board = grid;
    }
    public void placeShips()
    {
        {
            boolean exception = true;
            do
            {
                try
                {
                    int i =5;
                    int x = (int)(Math.random()*10);
                    int y = (int)(Math.random()*10);
                    Location shiploc = new Location(x,y);
                    int dir = (int)(Math.random() * 4 + 1);
                    board.placeShip(shiploc,i,dir);
                    Eaircraftcarrier = GameRunner.getShip(shiploc,i,dir);
                    exception = false;
                }
                catch(Exception e)
                {
                   
                }
            }
            while(exception);
            exception = true;
            do
            {
                try
                {
                    int j =4;
                    int x = (int)(Math.random()*10);
                    int y = (int)(Math.random()*10);
                    Location shiploc = new Location(x,y);
                    int dir = (int)(Math.random() * 4 + 1);
                    board.placeShip(shiploc,j,dir);
                    Ebattleship = GameRunner.getShip(shiploc,j,dir);
                    exception = false;
                }
                catch(Exception e)
                {
                   
                }
            }
            while(exception);
            exception = true;
            do
            {
                try
                {
                    int k =3;
                    int x = (int)(Math.random()*10);
                    int y = (int)(Math.random()*10);
                    Location shiploc = new Location(x,y);
                    int dir = (int)(Math.random() * 4 + 1);
                    board.placeShip(shiploc,k,dir);
                    Esubmarine = GameRunner.getShip(shiploc,k,dir);
                    exception = false;
                }
                catch(Exception e)
                {
                   
                }
            }
            while(exception);
            exception = true;
            do
            {
                try
                {
                    int l =3;
                    int x = (int)(Math.random()*10);
                    int y = (int)(Math.random()*10);
                    Location shiploc = new Location(x,y);
                    int dir = (int)(Math.random() * 4 + 1);
                    board.placeShip(shiploc,l,dir);
                    Edestroyer = GameRunner.getShip(shiploc,l,dir);
                    exception = false;
                }
                catch(Exception e)
                {
                   
                }
            }
            while(exception);
            exception = true;
            do
            {
                try
                {
                    int m =2;
                    int x = (int)(Math.random()*10);
                    int y = (int)(Math.random()*10);
                    Location shiploc = new Location(x,y);
                    int dir = (int)(Math.random() * 4 + 1);
                    board.placeShip(shiploc,m,dir);
                    Epatrolboat = GameRunner.getShip(shiploc,m,dir);
                    exception = false;
                }
                catch(Exception e)
                {
                   
                }
            }
            while(exception);
            
        }
       
    }
    public void makeGuess(GameRunner gd, Grid ourGrid)
    {
        boolean badmove = false;
        Location loc = null;
        do
        {
            int x = (int)(Math.random()*10);
            int y = (int)(Math.random()*10);
            loc = new Location(x,y);
            if(ourGrid.containsPeg(loc))
            {
                badmove = true;
            }
            else
            {
                badmove = false;
            }
        }
        while(badmove);
        if(ourGrid.containsShip(loc))
        {
            ShipWithPeg swp = new ShipWithPeg(loc);
            gd.addPiece(swp);
            ourGrid.placeShipWithPeg(loc);
            System.out.println("\nThe enemy hit your ship.");
            if(GameRunner.shipDestroyed(loc, GameRunner.aircraftcarrier, GameRunner.battleship, GameRunner.submarine, GameRunner.destroyer, GameRunner.patrolboat))
            {
                System.out.println("\nTHE ENEMY DESTROYED YOUR SHIP.\n");
                GameRunner.friendlyShips--;
            }
        }
        else
        {
            gd.addPiece(new EmptySquareWithWhitePeg(loc,false));
            ourGrid.placeEmptySquareWithWhitePeg(loc,false);
            System.out.println("\nThe enemy missed your ships.");
        }
    }
    public ArrayList<Location> getEaircraftcarrier()
    {
        return Eaircraftcarrier;
    }
    public ArrayList<Location> getEbattleship()
    {
        return Ebattleship;
    }
    public ArrayList<Location> getEsubmarine()
    {
        return Esubmarine;
    }
    public ArrayList<Location> getEdestroyer()
    {
        return Edestroyer;
    }
    public ArrayList<Location> getEpatrolboat()
    {
        return Epatrolboat;
    }
}