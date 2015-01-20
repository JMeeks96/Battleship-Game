public class Location
{
    private int x;
    private int y;
    public Location()
    {
        x = 0;
        y = 0;
    }

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Location loc)
    {
        if(loc.getX() == x && loc.getY() == y)
        {
            return true;
        }
        return false;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
}
