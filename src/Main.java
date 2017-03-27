import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel {
    static final int FRAMEWIDTH = 800, FRAMEHEIGHT = 800;
    static final int[][] map = new int[30][20];
    static Block[][] fill = new Block[30][20];
    private Timer timer;
    static boolean[] keys;

    private static Shape curr;
    public Main() {
        keys = new boolean[512];
        curr = new TShape(15*20, 100);
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	repaint();
                controls();
            }
        });
        timer.start();

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(curr.checkL);
                if(curr.hitBounds(Shape.SOUTH)) {
            		addedPiece();
            		removeRows();
            		curr = newShape();
//            		viewMap();
            	}else {
            		curr.update(Shape.SOUTH);
            	}
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
    
    public void viewMap() {
    	for(Block[] b : fill) {
    		String strt = "[";
    		for(int i = 0; i < b.length; i++) {
    			if(b[i] != null) {
    				strt += b[i].toString() + ", ";
    			}else {
    				strt += "O, ";
    			}
    		}
    		strt += "]";
    		System.out.println(strt);
    	}
    }
    
    public Shape newShape() {
    	return new TShape(15*20, 100);
    }
    
    public void removeRows() {
    	for(int i = 0; i < map.length; i++) {
    		if(checkRows(i)) {
    			reorg(i);
    		}
    	}
    }
    
    public void reorg(int row) {
    	for(int i = row; i > 0; i--) {
    		for(int j = 0; j < fill[i].length; j++) {
    			Block b = fill[i-1][j];
                if(b != null) {
                    b.update(Shape.SOUTH);
                }
    			fill[i][j] = b;
    		}
    		for(int k = 0; k < fill[i-1].length; k++) {
				fill[i-1][k] = null;
			}
    	}
    	
    	for(int i = 0; i < fill[0].length; i++) {
    		fill[0][i] = null;
    	}
    	
    	repaint();
    }
    
    public boolean checkRows(int row) {
    	for(int i = 0; i < fill[row].length; i++) {
    		if(fill[row][i] == null) {
    			return false;
    		}
    	}
    	return true;
    }

    public void controls() {
        if(keys[KeyEvent.VK_A]) {
        	curr.update(Shape.WEST);
            keys[KeyEvent.VK_A] = false;
        }else if(keys[KeyEvent.VK_W]) {
        	curr.rotate();
            keys[KeyEvent.VK_W] = false;
        }else if(keys[KeyEvent.VK_S]) {
        	curr.update(Shape.SOUTH);
        }else if(keys[KeyEvent.VK_D]) {
        	curr.update(Shape.EAST);
            keys[KeyEvent.VK_D] = false;
        }else if(keys[KeyEvent.VK_ESCAPE]) {
        	System.exit(0);
        }
    }
    
    public void addedPiece() {
    	for(Block b : curr.getComponent()) {
    		Point p = b.getLoc();
//    		System.out.println("X: " + (int)(p.getX()/20) + ", Y: " + (int)(p.getY()/20));
    		fill[(int)((p.getY() - 80)/20)][(int)((p.getX() - 80)/20)] = new Block(p.x, p.y);
    	}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
        
        //MAP GRID
        g2.setColor(Color.BLACK);
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[r].length; c++) {
                g2.drawRect(c*20 + 80, r*20 + 80, 20, 20);
            }
        }
        
        curr.draw(g2);
        for(int r = 0; r < fill.length; r++) {
        	for(int c = 0; c < fill[r].length; c++) {
        		if(fill[r][c] != null) {
        			fill[r][c].draw(g2);
        		}
        	}
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
