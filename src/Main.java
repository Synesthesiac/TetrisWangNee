import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JPanel {
    static final int FRAMEWIDTH = 801, FRAMEHEIGHT = 800;
    static final int[][] map = new int[30][20];
    static boolean[][] fill = new boolean[30][20];
    static final int blockSize = 20;
    Timer timer;

    private static final ArrayList<Shape> pieces = new ArrayList<>();
    public Main() {
        pieces.add(new TShape(250, 250, 1));
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("lmao");
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //MAP GRID
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[r].length; c++) {
                g2.drawRect(c*blockSize + 80, r*blockSize + 80, blockSize, blockSize);
            }
        }

        for(Shape p : pieces) {
            p.draw(g2);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris!");
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
