import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by Khiem on 11/16/2016.
 */
public class main1 {
    public static void main(String args[]) throws Exception {
        JFrame f = new JFrame("Draw a Red Line");
        f.setSize(300, 300);
        f.setLocation(300, 300);
        f.setResizable(false);
        JPanel p = new JPanel() {
            Point pointStart = null;
            Point pointEnd   = null;
            {
                addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        pointStart = e.getPoint();
                    }

                    public void mouseReleased(MouseEvent e) {
                        pointStart = null;
                    }
                });
                addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseMoved(MouseEvent e) {
                        pointEnd = e.getPoint();
                    }

                    public void mouseDragged(MouseEvent e) {
                        pointEnd = e.getPoint();
                        repaint();
                    }
                });
            }
            public void paint(Graphics g) {
                super.paint(g);
                if (pointStart != null) {
                    g.setColor(Color.RED);
                    g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                }
            }
        };
        f.add(p);
        f.setVisible(true);
    }
}
