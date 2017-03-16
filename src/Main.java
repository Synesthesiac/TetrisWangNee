import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    static final int FRAMEWIDTH = 500, FRAMEHEIGHT = 500;
    static Block test = new Block(250, 250);

    public Main() {
        repaint();
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        test.draw(g2);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();


        window.add(panel);
        window.setVisible(true);
        window.setResizable(true);

    }


}
