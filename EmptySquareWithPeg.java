import javax.swing.JComponent;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
public class EmptySquareWithPeg extends  BoardPiece
{
    protected Location loc;
    protected boolean enemy;
    protected final int EXTRA = 600;
    public EmptySquareWithPeg(Location loc)
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
        g2.setColor(Color.GRAY);
        Ellipse2D.Double peg = new Ellipse2D.Double(300, 300, 50, 50);
        g2.draw(peg);
        g2.fill(peg);
    }
}