import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {
    static final int FRAMEWIDTH = 801, FRAMEHEIGHT = 800;
    static final int[][] map = new int[30][20];
    static boolean[][] fill = new boolean[30][20];
    static final int blockSize = 20;
    private Timer timer;
    static boolean[] keys;

    private static final ArrayList<Shape> pieces = new ArrayList<>();
    private static Shape curr;
    public Main() {
        keys = new boolean[512];
        curr = new TShape(15*20, 100, 1);
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controls();
                System.out.println("lmao");
                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
        });
    }

    public void controls() {
        if(keys[KeyEvent.VK_A]) {
            for(Block b : curr.component) {
                b.setX(-20);
            }
        }else if(keys[KeyEvent.VK_W]) {
            for(Block b : curr.component) {
                b.setY(-20);
            }
        }else if(keys[KeyEvent.VK_S]) {
            for(Block b : curr.component) {
                b.setY(20);
            }
        }else if(keys[KeyEvent.VK_D]) {
            for(Block b : curr.component) {
                b.setX(20);
            }
        }
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
        curr.draw(g2);
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
