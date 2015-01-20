public class Grid
{
    private BoardPiece[][] board;
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;
    public static final int COLUMNS = 10;
    public static final int ROWS = 10;
    public Grid()
    {
        board = new BoardPiece[10][10];
    }

    private boolean isValidShipPlacement(Location loc, int length, int direction)
    {
        if(direction == NORTH)
        {
            if(isValid(new Location(loc.getX(), loc.getY() - length + 1)))
            {
                for(int i = 0; i < length; i++)
                {
                    if(!isEmpty(new Location(loc.getX(), loc.getY() - i)))
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        else if(direction == EAST)
        {
            if(isValid(new Location(loc.getX() + length - 1, loc.getY())))
            {
                for(int i = 0; i < length; i++)
                {
                    if(!isEmpty(new Location(loc.getX() + i, loc.getY())))
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        else if(direction == SOUTH)
        {
            if(isValid(new Location(loc.getX(), loc.getY() + length - 1)))
            {
                for(int i = 0; i < length; i++)
                {
                    if(!isEmpty(new Location(loc.getX(), loc.getY() + i)))
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        else if(direction == WEST)
        {
            if(isValid(new Location(loc.getX() - length + 1, loc.getY())))
            {
                for(int i = 0; i < length; i++)
                {
                    if(!isEmpty(new Location(loc.getX() - i, loc.getY())))
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(Location loc)
    {
        if(loc.getX() < 10 && loc.getX() >= 0 && loc.getY() < 10 && loc.getY() >= 0)
        {
            return true;
        }
        return false;
    }

    public void placeShip(Location loc, int length, int direction)
    {
        if(!isValid(loc))
        {
            throw new IndexOutOfBoundsException("Ship is out of grid");
        }
        if(!isValidShipPlacement(loc, length, direction))
        {
            throw new IllegalStateException("Improper Placement");
        }
        if(direction == NORTH)
        {
            for(int i = 0; i < length; i++)
            {
                board[loc.getY() - i][loc.getX()] = new Ship(new Location(loc.getX(), loc.getY() - i));
            }
        }
        else if(direction == EAST)
        {
            for(int i = 0; i < length; i++)
            {
                board[loc.getY()][loc.getX() + i] = new Ship(new Location(loc.getX() + i, loc.getY()));
            }
        }
        else if(direction == SOUTH)
        {
            for(int i = 0; i < length; i++)
            {
                board[loc.getY() + i][loc.getX()] = new Ship(new Location(loc.getX(), loc.getY() + i));
            }
        }
        else if(direction == WEST)
        {
            for(int i = 0; i < length; i++)
            {
                board[loc.getY()][loc.getX() - i] = new Ship(new Location(loc.getX() - i, loc.getY()));
            }
        }
    }

    public void placeShipWithPeg(Location loc)
    {
        if(isValid(loc) && containsShip(loc))
        {
            board[loc.getY()][loc.getX()] = new ShipWithPeg(loc);
        }
        else if(isValid(loc))
        {
            throw new IllegalStateException("Improper placement");
        }
        else
        {
            throw new IndexOutOfBoundsException("Ship is out of grid");
        }
    }

    public void placeEmptySquareWithWhitePeg(Location loc, boolean isEnemy)
    {
        if(isValid(loc) && isEmpty(loc))
        {
            board[loc.getY()][loc.getX()] = new EmptySquareWithWhitePeg(loc, isEnemy);
        }
        else if(isValid(loc))
        {
            throw new IllegalStateException("Improper placement");
        }
        else
        {
            throw new IndexOutOfBoundsException("Ship is out of grid");
        }
    }

    public void placeEmptySquareWithRedPeg(Location loc, boolean isEnemy)
    {
        if(isValid(loc) && isEmpty(loc))
        {
            board[loc.getY()][loc.getX()] = new EmptySquareWithRedPeg(loc, isEnemy);
        }
        else if(isValid(loc))
        {
            throw new IllegalStateException("Improper placement");
        }
        else
        {
            throw new IndexOutOfBoundsException("Ship is out of grid");
        }
    }

    public BoardPiece getPiece(Location loc)
    {
        return board[loc.getY()][loc.getX()];
    }

    public boolean containsPeg(Location loc)
    {
        if(!isValid(loc))
        {
            throw new IndexOutOfBoundsException("Location is out of grid");
        }
        if(board[loc.getY()][loc.getX()] instanceof EmptySquareWithPeg || board[loc.getY()][loc.getX()] instanceof ShipWithPeg)
        {
            return true;
        }
        return false;
    }

    public boolean containsShip(Location loc)
    {
        if(!isValid(loc))
        {
            throw new IndexOutOfBoundsException("Location is out of grid");
        }
        if(board[loc.getY()][loc.getX()] instanceof Ship)
        {
            return true;
        }
        return false;
    }

    public boolean isEmpty(Location loc)
    {
        if(!isValid(loc))
        {
            throw new IndexOutOfBoundsException("Location is out of grid");
        }
        if(board[loc.getY()][loc.getX()] == null)
        {
            return true;
        }
        return false;
    }
}