//Property of Dheeraj Gottipalli and Justin Meeken 2014.
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.util.ArrayList;

public class GameRunner
{
    public  JFrame frame;
    public JPanel panel;
    public static ArrayList<Location> aircraftcarrier;
    public static ArrayList<Location> battleship;
    public static ArrayList<Location> submarine;
    public static ArrayList<Location> destroyer;
    public static ArrayList<Location> patrolboat;
    public static int friendlyShips = 5;
    public static int enemyShips = 5;
    public GameRunner()
    {
        frame = new JFrame();
        frame.setSize(1200,600);
        frame.setTitle("BattleShip Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridD component = new GridD();
        frame.add(component);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        System.out.print('\f');
        GameRunner gd = new GameRunner();
        boolean isOver = false;
        Scanner in = new Scanner(System.in);
        Grid playerBoard = new Grid();
        Grid guessBoard = new Grid();
        Grid aiBoard = new Grid();
        AIPlayer aiPlayer = new AIPlayer(aiBoard);
        System.out.println("Welcome to Dheeraj and Justin's game of Battleship.");
        
        System.out.print("\nTime to place your Aircraft Carrier of length 5. \nEnter the coordinate, \"J10\" for example: ");
        Location loc = convertToLocation(in.next());
        System.out.print("\nEnter the direction\"N S E W\": ");
        int dir = convertToDirection(in.next());
        placePlayerShip(loc, 5, dir, playerBoard, gd);
        aircraftcarrier = getShip(loc, 5, dir);
        
        System.out.print("\nTime to place your Battleship of length 4. \nEnter the coordinate: ");
        loc = convertToLocation(in.next());
        System.out.print("\nEnter the direction: ");
        dir = convertToDirection(in.next());
        placePlayerShip(loc, 4, dir, playerBoard, gd);
        battleship = getShip(loc, 4, dir);
        
        System.out.print("\nTime to place your Submarine of length 3. \nEnter the coordinate: ");
        loc = convertToLocation(in.next());
        System.out.print("\nEnter the direction: ");
        dir = convertToDirection(in.next());
        placePlayerShip(loc, 3, dir, playerBoard, gd);
        submarine = getShip(loc, 3, dir);
        
        System.out.print("\nTime to place your Destroyer of length 3. \nEnter the coordinate: ");
        loc = convertToLocation(in.next());
        System.out.print("\nEnter the direction: ");
        dir = convertToDirection(in.next());
        placePlayerShip(loc, 3, dir, playerBoard, gd);
        destroyer = getShip(loc, 3, dir);
        
        System.out.print("\nTime to place your Patrol Boat of length 2. \nEnter the coordinate: ");
        loc = convertToLocation(in.next());
        System.out.print("\nEnter the direction: ");
        dir = convertToDirection(in.next());
        placePlayerShip(loc, 2, dir, playerBoard, gd);
        patrolboat = getShip(loc, 2, dir);
        
        aiPlayer.placeShips();
        System.out.println("\nYour opponent has placed his ships. \nTime for the game to begin.");
        while(!isOver)
        {
            System.out.print("\nEnter the coordinate you want to attack: ");
            makeMove(convertToLocation(in.next()), aiBoard, guessBoard, gd, aiPlayer);
            if(enemyShips == 0)
            {
                System.out.println("\nYOU WIN.");
                isOver = true;
                break;
            }
            System.out.println("\nEnemy turn.");
            aiPlayer.makeGuess(gd, playerBoard);
            if(friendlyShips == 0)
            {
                System.out.println("\nYOU LOSE.");
                isOver = true;
                break;
            }
        }
        System.out.println("\nTHANK YOU FOR PLAYING.");
    }

    public void addPiece(BoardPiece bp)
    {
        //frame.setVisible(false);
        frame.add(bp);
        frame.repaint();
        frame.setVisible(true);
    }

    public void addShip(Location loc, int length, int dir)
    {
        int xinc = 0;
        int yinc = 0;
        if(dir == 1)
        {
            yinc = -1;
        }
        else if(dir == 2)
        {
            xinc = 1;
        }
        else if(dir == 3)
        {
            yinc = 1;
        }
        else if(dir == 4)
        {
            xinc = -1;
        }
        int xloc = loc.getX();
        int yloc = loc.getY();
        this.addPiece(new Ship(loc));
        for(int i = 1; i < length; i++)
        {
            xloc += xinc;
            yloc += yinc;
            this.addPiece(new Ship(new Location(xloc, yloc)));
        }
    }

    public static void makeMove(Location loc, Grid enemyBoard, Grid guessBoard, GameRunner gd, AIPlayer opp)
    {
        Scanner in = new Scanner(System.in);
        try
        {
            if(enemyBoard.containsShip(loc))
            {
                guessBoard.placeEmptySquareWithRedPeg(loc, true);
                System.out.println("\nYou hit an enemy ship.");
                gd.addPiece(guessBoard.getPiece(loc));
                if(shipDestroyed(loc, opp.getEaircraftcarrier(), opp.getEbattleship(), opp.getEdestroyer(), opp.getEsubmarine(), opp.getEpatrolboat()))
                {
                    System.out.println("\nYOU DESTROYED AN ENEMY SHIP.\n");
                    enemyShips--;
                }
            }
            else
            {
                guessBoard.placeEmptySquareWithWhitePeg(loc, true);
                System.out.println("\nYou missed.");
                gd.addPiece(guessBoard.getPiece(loc));
            }
        }
        catch(Exception e)
        {
            System.out.print("\nEnter a coordinate you have not attacked previously: ");
            makeMove(convertToLocation(in.next()), enemyBoard, guessBoard, gd, opp);
        }
    }

    public static void placePlayerShip(Location loc, int length, int dir, Grid playerBoard, GameRunner gd)
    {
        Scanner in = new Scanner(System.in);
        try
        {
            playerBoard.placeShip(loc, length, dir);
            gd.addShip(loc, length, dir);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.print("\nShip is out of bounds. \nEnter the coordinate, \"J10\" for example: ");
            loc = convertToLocation(in.next());
            System.out.print("\nEnter the direction\"N S E W\": ");
            dir = convertToDirection(in.next());
            placePlayerShip(loc, length, dir, playerBoard, gd);
        }
        catch(IllegalStateException e)
        {
            System.out.print("\nImproper placement. \nEnter the coordinate: ");
            loc = convertToLocation(in.next());
            System.out.print("\nEnter the direction\"N S E W\": ");
            dir = convertToDirection(in.next());
            placePlayerShip(loc, length, dir, playerBoard , gd);
        }
    }

    public static void displayBoard(Grid board)//only for testing purposes
    {
        System.out.println("----------------------------------------------------------------------------------------------------");
        for(int i = 0; i < board.ROWS; i++)
        {
            for(int j = 0; j < board.COLUMNS; j++)
            {
                System.out.printf("%-10h",board.getPiece(new Location(j, i)));
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public static void makeAIMove(AIPlayer player)
    {
        try
        {
            player.placeShips();
        }
        catch(IndexOutOfBoundsException e)
        {
            makeAIMove(player);
        }
        catch(IllegalStateException e)
        {
            makeAIMove(player);
        }
    }

    public static Location convertToLocation(String coord)
    {
        Scanner in = new Scanner(System.in);
        try
        {
            coord = coord.toUpperCase();
            String letter = coord.substring(0);
            int number = Integer.parseInt(coord.substring(1, coord.length()));
            char c = coord.charAt(0);
            if(number <= 10 && number > 0 && letter.toLowerCase().compareTo("a") > 0 && letter.toLowerCase().compareTo("k") < 0)
            {
                return new Location((int) c - 65, number - 1);
            }
            else
            {
                System.out.print("\nImproper coordinate. Enter again: ");
                coord = in.next();
                return convertToLocation(coord);
            }
        }
        catch(Exception e)
        {
            System.out.print("\nEnter coordinate in proper format, \"J10\" for example: ");
            coord = in.next();
            return convertToLocation(coord);
        }
    }

    public static int convertToDirection(String dir)
    {
        dir = dir.toLowerCase();
        if(dir.equals("n"))
        {
            return 1;
        }
        else if(dir.equals("e"))
        {
            return 2;
        }
        else if(dir.equals("s"))
        {
            return 3;
        }
        else if(dir.equals("w"))
        {
            return 4;
        }
        else
        {
            Scanner in = new Scanner(System.in);
            System.out.print("\nImproper direction. Enter again: ");
            dir = in.next();
            return convertToDirection(dir);
        }
    }

    public static ArrayList<Location> getShip(Location loc, int length, int direction)
    {
        ArrayList<Location> ship = new ArrayList<Location>();
        if(direction == Grid.NORTH)
        {
            for(int i = 0; i < length; i++)
            {
                ship.add(new Location(loc.getX(), loc.getY() - i));
            }
        }
        else if(direction == Grid.EAST)
        {
            for(int i = 0; i < length; i++)
            {
                ship.add(new Location(loc.getX() + i, loc.getY()));
            }
        }
        else if(direction == Grid.SOUTH)
        {
            for(int i = 0; i < length; i++)
            {
                ship.add(new Location(loc.getX(), loc.getY() + i));
            }
        }
        else if(direction == Grid.WEST)
        {
            for(int i = 0; i < length; i++)
            {
                ship.add(new Location(loc.getX() - i, loc.getY()));
            }
        }
        return ship;
    }

    public static boolean shipDestroyed(Location loc, ArrayList<Location> aircraftcarrier, ArrayList<Location> battleship, ArrayList<Location> submarine, ArrayList<Location> destroyer, ArrayList<Location> patrolboat)
    {
        for(int i = 0; i < aircraftcarrier.size(); i++)
        {
            if(aircraftcarrier.get(i).equals(loc))
            {
                aircraftcarrier.remove(i);
                if(aircraftcarrier.size() == 0)
                {
                    aircraftcarrier.add(new Location(99, 99));
                    return true;
                }
            }
        }
        for(int i = 0; i < battleship.size(); i++)
        {
            if(battleship.get(i).equals(loc))
            {
                battleship.remove(i);
                if(battleship.size() == 0)
                {
                    battleship.add(new Location(99, 99));
                    return true;
                }
            }
        }
        for(int i = 0; i < submarine.size(); i++)
        {
            if(submarine.get(i).equals(loc))
            {
                submarine.remove(i);
                if(submarine.size() == 0)
                {
                    submarine.add(new Location(99, 99));
                    return true;
                }
            }
        }
        for(int i = 0; i < destroyer.size(); i++)
        {
            if(destroyer.get(i).equals(loc))
            {
                destroyer.remove(i);
                if(destroyer.size() == 0)
                {
                    destroyer.add(new Location(99, 99));
                    return true;
                }
            }
        }
        for(int i = 0; i < patrolboat.size(); i++)
        {
            if(patrolboat.get(i).equals(loc))
            {
                patrolboat.remove(i);
                if(patrolboat.size() == 0)
                {
                    patrolboat.add(new Location(99, 99));
                    return true;
                }
            }
        }
        return false;
    }
}