import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel {
    static final int FRAMEWIDTH = 800, FRAMEHEIGHT = 800;
    static Block[][] fill = new Block[30][20];
    private Timer timer;
    private Timer timer2;
    static boolean[] keys;
    static ArrayList<Block> map = new ArrayList<>();
    static int state = 1;

    static Color[] colors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};

    private static Shape curr;
    private static Shape disp;
    private int score;
    private int delay;
    private int level;


    public Main() {
        score = 0;
        delay = 500;
        level = 1;
        keys = new boolean[512];
        curr = newShape(1);
        disp = newShape(2);
        timer2 = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(state);
                if(state == 1) {
                    controls();
                }
                repaint();
            }
        });
        timer2.start();

        timer = new Timer(delay, new ActionListener() {
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
                    } else {
                        curr.update(Shape.SOUTH);
                    }
                    if(score >= 500*level){
                       delay -= 25;
                        timer.setDelay(delay);
                        level++;
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
            curr = new TShape(280, 80, 1, disp.getColor());
        } else if (disp instanceof Rod) {
            curr = new Rod(280, 80, 1, disp.getColor());
        } else if (disp instanceof Square) {
            curr = new Square(280, 80, 1, disp.getColor());
        } else if (disp instanceof RightL) {
            curr = new RightL(280, 120, 1, disp.getColor());
        } else if (disp instanceof LeftL) {
            curr = new LeftL(280, 120, 1, disp.getColor());
        } else if (disp instanceof ZigZagR) {
            curr = new ZigZagR(280, 120, 1, disp.getColor());
        } else if (disp instanceof ZigZagL) {
            curr = new ZigZagL(280, 120, 1, disp.getColor());
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
        int rando = (int) (Math.random() * 7) + 1;
        System.out.println(rando);
        Color c = colors[(int)(Math.random() * 6)];
        if(t == 1) {
            if (rando == 1) {
                return new TShape(280, 80, 1, c);
            } else if (rando == 2) {
                return new Rod(280, 80, 1, c);
            } else if (rando == 3) {
                return new Square(280, 80, 1, c);
            } else if (rando == 4) {
                return new RightL(280, 120, 1, c);
            } else if (rando == 5) {
                return new LeftL(280, 120, 1, c);
            } else if (rando == 6) {
                return new ZigZagL(280, 120, 1, c);
            } else if (rando == 7) {
                return new ZigZagR(280, 120, 1, c);
            }
        } else if(t == 2) {
            if (rando == 1) {
                return new TShape(610, 160, 1, c);
            } else if (rando == 2) {
                return new Rod(610, 140, 1, c);
            } else if (rando == 3) {
                return new Square(600, 160, 1, c);
            } else if (rando == 4) {
                return new RightL(630, 180, 1, c);
            } else if(rando == 5) {
                return new LeftL(610, 180, 1, c);
            }else if (rando == 6) {
                return new ZigZagL(610, 180, 1, c);
            }else if (rando == 7) {
                return new ZigZagR(610, 180, 1, c);
            }
        }
        return new TShape(0, 0, 1, Color.BLACK);
    }
    
    public void removeRows() {
        int numRemoved = 0;
    	for(int i = 0; i < fill.length; i++) {
    		if(checkRows(i)) {
    			reorg(i);
                numRemoved++;
    		}
    	}
    	if(numRemoved > 0) {
            score += 100 * Math.pow(2, numRemoved);
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
    		fill[(int)((p.getY() - 80)/20)][(int)((p.getX() - 80)/20)] = new Block(p.x, p.y, b.getColor());
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
            g2.drawString("Score: " + score + " Level: " + level, 80,70);
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
            g2.setColor(Color.BLACK);
            g2.drawString("NEXT SHAPE", 580, 100);
            g2.drawRect(520, 80, 200, 200);

        }else if(state == 2) {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
            g2.setColor(Color.BLACK);
            g2.drawString("Score:" + score, 350,380);
            g2.drawString("You Suck. Made by the Glorious Andy Wang and his sidekick Daniel Nee", 200,400);
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
