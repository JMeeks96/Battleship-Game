import javax.swing.JComponent;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class Ship extends BoardPiece
{
    
    private Location loc;
    
    public Ship(Location loc)
    {
        this.loc = loc;
    }
    public Location getLoc()
     {
        return loc;
     }
    
     public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        
       g2.setColor(new Color(84,84,84,129));
        Rectangle ship = new Rectangle((loc.getX() + 1) * 50,(loc.getY() + 1) * 50,50,50);
        g2.fill(ship);
       
    }
}