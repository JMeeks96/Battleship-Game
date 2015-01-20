import javax.swing.JComponent;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
public class EmptySquareWithRedPeg extends EmptySquareWithPeg
{

    public EmptySquareWithRedPeg(Location loc, boolean enemy)
    {
        super(loc);
        this.enemy = enemy;
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        Ellipse2D.Double peg = null;
        if(enemy){
            peg = new Ellipse2D.Double((loc.getX() + 1) * 50 + EXTRA , (loc.getY() + 1) * 50, 50, 50);
        }
        else{
            peg = new Ellipse2D.Double((loc.getX() + 1) * 50 , (loc.getY() + 1) * 50, 50, 50);
        }
        g2.draw(peg);
        g2.fill(peg);
    }
}