import javax.swing.JComponent;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;

public class GridD extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i<600; i = i + 50)
        {
            Line2D.Double verticalLine = new Line2D.Double(i,0,i,550);
            g2.draw(verticalLine);
        }
        for(int i = 0; i<600; i = i + 50)
        {
            Line2D.Double horizontalLine = new Line2D.Double(0,i,550,i);
            g2.draw(horizontalLine);
        }
        for(int i = 600; i<1200; i = i + 50)
        {
            Line2D.Double verticalLine = new Line2D.Double(i,0,i,550);
            g2.draw(verticalLine);
        }
        for(int i = 0; i<600; i = i + 50)
        {
            Line2D.Double horizontalLine = new Line2D.Double(600,i,1150,i);
            g2.draw(horizontalLine);
        }

        g2.drawString("DJ",20,25);

        g2.drawString("1",25,75);
        g2.drawString("2",25,125);
        g2.drawString("3",25,175);
        g2.drawString("4",25,225);
        g2.drawString("5",25,275);
        g2.drawString("6",25,325);
        g2.drawString("7",25,375);
        g2.drawString("8",25,425);
        g2.drawString("9",25,475);
        g2.drawString("10",25,525);

        g2.drawString("A",75,25);
        g2.drawString("B",125,25);
        g2.drawString("C",175,25);
        g2.drawString("D",225,25);
        g2.drawString("E",275,25);
        g2.drawString("F",325,25);
        g2.drawString("G",375,25);
        g2.drawString("H",425,25);
        g2.drawString("I",475,25);
        g2.drawString("J",525,25);

        g2.drawString("OP",620,25);

        g2.drawString("1",625,75);
        g2.drawString("2",625,125);
        g2.drawString("3",625,175);
        g2.drawString("4",625,225);
        g2.drawString("5",625,275);
        g2.drawString("6",625,325);
        g2.drawString("7",625,375);
        g2.drawString("8",625,425);
        g2.drawString("9",625,475);
        g2.drawString("10",625,525);

        g2.drawString("A",675,25);
        g2.drawString("B",725,25);
        g2.drawString("C",775,25);
        g2.drawString("D",825,25);
        g2.drawString("E",875,25);
        g2.drawString("F",925,25);
        g2.drawString("G",975,25);
        g2.drawString("H",1025,25);
        g2.drawString("I",1075,25);
        g2.drawString("J",1125,25);
    }
}
