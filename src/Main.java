import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel {
    static final int FRAMEWIDTH = 800, FRAMEHEIGHT = 800;
    static Block[][] fill = new Block[30][20];
    private Timer timer;
    static boolean[] keys;
    static ArrayList<Block> map = new ArrayList<>();
    static int state = 1;

    private static Shape curr;
    private static Shape disp;

    public Main() {
        keys = new boolean[512];
        curr = newShape(1);
        disp = newShape(2);
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(state);
                if(state == 1) {
                    controls();
                }
                repaint();
            }
        });
        timer.start();

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(curr.checkL);
                if(state == 1) {
                    if((curr.hitBounds(Shape.SOUTH) || curr.hitShape(Shape.SOUTH)) && curr.toppedOut()) {
                        state++;
                    }else if ((curr.hitBounds(Shape.SOUTH) || curr.hitShape(Shape.SOUTH)) && !curr.toppedOut()) {
                        addedPiece();
                        removeRows();
                        newPiece();
                        viewMap();
                    } else {
                        curr.update(Shape.SOUTH);
                    }
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

    public void newPiece() {
        if (disp instanceof TShape) {
            curr = new TShape(280, 80, 1);
        } else if (disp instanceof Rod) {
            curr = new Rod(280, 80, 1);
        } else if (disp instanceof Square) {
            curr = new Square(280, 80, 1);
        } else if (disp instanceof RightL) {
            curr = new RightL(280, 120, 1);
        }
        disp = newShape(2);
    }
    
    public void viewMap() {
    	for(int r = 0; r < fill.length; r++) {
    	    for(int c = 0; c < fill[r].length; c++) {
    	        if(fill[r][c] != null) {
                    System.out.print(fill[r][c].toString());
                }else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
    
    public Shape newShape(int t) {
        int rando = (int) (Math.random() * 4) + 1;
        if(t == 1) {
            if (rando == 1) {
                return new TShape(280, 80, 1);
            } else if (rando == 2) {
                return new Rod(280, 80, 1);
            } else if (rando == 3) {
                return new Square(280, 80, 1);
            } else if (rando == 4) {
                return new RightL(280, 120, 1);
            }
        }else if(t == 2) {
            if (rando == 1) {
                return new TShape(610, 160, 1);
            } else if (rando == 2) {
                return new Rod(610, 140, 1);
            } else if (rando == 3) {
                return new Square(600, 160, 1);
            } else if (rando == 4) {
                return new RightL(630, 180, 1);
            }
        }
        return new TShape(0, 0, 1);
    }
    
    public void removeRows() {
    	for(int i = 0; i < fill.length; i++) {
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
                fill[i-1][j] = null;
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
        if(keys[KeyEvent.VK_A] && !curr.hitBounds(Shape.WEST) && !curr.hitShape(Shape.WEST)) {
        	curr.update(Shape.WEST);
            keys[KeyEvent.VK_A] = false;
        }else if(keys[KeyEvent.VK_W]) {
        	curr.rotate();
            keys[KeyEvent.VK_W] = false;
        }else if(keys[KeyEvent.VK_S] && !curr.hitBounds(Shape.SOUTH) && !curr.hitShape(Shape.SOUTH)) {
        	curr.update(Shape.SOUTH);
        }else if(keys[KeyEvent.VK_D] && !curr.hitBounds(Shape.EAST) && !curr.hitShape(Shape.EAST)) {
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
            map.add(b);
    	}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(state == 1) {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);

            //Actual Map
            g2.setColor(Color.BLACK);
            for (int r = 0; r < fill.length; r++) {
                for (int c = 0; c < fill[r].length; c++) {
                    g2.drawRect(c * 20 + 80, r * 20 + 80, 20, 20);
                }
            }

            curr.draw(g2);

            for (int r = 0; r < fill.length; r++) {
                for (int c = 0; c < fill[r].length; c++) {
                    if (fill[r][c] instanceof Block) {
                        fill[r][c].draw(g2);
                    }
                }
            }

            //Sidebar
            disp.draw(g2);
            g2.drawRect(520, 80, 200, 200);

        }else if(state == 2) {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
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
