import java.awt.*;
import javax.swing.*;

public class ParallelLines extends JPanel {
  int offset = 0;
  int row;
  int colum;

  public void paintComponent(Graphics g) {
    super.paintComponent(g); // Call JPanel's paintComponent method
                             // to paint the background

    int width = getWidth();
    int height = getHeight();

    drawIllusion(g, width, height);
  }

  /**
   * 
   * @param g
   * @param width
   * @param height
   * @param size
   */
  public void drawIllusion(Graphics g, int width, int height) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);

    for ( row = 0; row < 8; row++ )
    {
      g.drawLine(0, (row + 1) * 50, width, (row + 1) * 50);
      
      if (row < 2)
      {
        offset = offset + 10;
      } 
      else if (row >= 2 && row < 4) 
      {
        offset = offset - 10;
      } 
      else if (row >= 4 && row < 6) 
      {
        offset = offset + 10;
      } 
      else 
      {
        offset = offset - 10;
      }

      for (colum = 0; colum < 11; colum++) 
      {
        if (colum % 2 == 0) 
        {
          g.fillRect((colum + 1) * 50 + offset, (row + 1) * 50, 50, 50);
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame w = new JFrame("ParallelLines");
    w.setBounds(100, 100, 640, 480);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ParallelLines panel = new ParallelLines();
    panel.setBackground(Color.WHITE);
    Container c = w.getContentPane();
    c.add(panel);
    w.setResizable(true);
    w.setVisible(true);
  }
}
