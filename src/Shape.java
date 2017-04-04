import java.awt.*;

public class Shape {
    public static final int SOUTH = 270, WEST = 180, EAST = 0;

    Block[] component = new Block[4];
    int orientation, x, y;
    int checkL = Integer.MAX_VALUE, checkR, checkB;

    public Shape(int xx, int yy) {
        x = xx;
        y = yy;
        orientation = 1;
    }

    public void draw(Graphics2D g2) {
        if(component.length != 0) {
            for (Block b : component) {
                b.draw(g2);
            }
        }
    }

    public void build(int orientation){}

    public void update(int dir) {
        if(dir == WEST) {
            x -= 20;
            for(Block b : component) {
                b.update(WEST);
            }
        }else if(dir == EAST) {
            x += 20;
            for(Block b : component) {
                b.update(EAST);
            }
        }else if(dir == SOUTH) {
            y += 20;
            for(Block b : component) {
                b.update(SOUTH);
            }
        }
    }

    public boolean hitShape(int dir) {
        for(Block b : Main.map) {
            for(Block a : component) {
                if(dir == WEST) {
                    Rectangle r1 = new Rectangle(a.getLoc().x - 20, a.getLoc().y, 20, 20);
                    Rectangle r2 = new Rectangle(b.getLoc().x, b.getLoc().y, 20, 20);
                    if(r1.intersects(r2)) {
                        return true;
                    }
                }else if(dir == EAST) {
                    Rectangle r1 = new Rectangle(a.getLoc().x + 20, a.getLoc().y, 20, 20);
                    Rectangle r2 = new Rectangle(b.getLoc().x, b.getLoc().y, 20, 20);
                    if (r1.intersects(r2)) {
                        return true;
                    }
                }else if(dir == SOUTH) {
                    Rectangle r1 = new Rectangle(a.getLoc().x, a.getLoc().y+20, 20, 20);
                    Rectangle r2 = new Rectangle(b.getLoc().x, b.getLoc().y, 20, 20);
                    if(r1.intersects(r2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hitBounds(int dir) {
        setPolars();
        if(dir == WEST) {
            if(checkL <= 80) {
                return true;
            }
        }else if(dir == EAST) {
            if(checkR >= 480) {
                return true;
            }
        }else if(dir == SOUTH) {
            if(checkB >= 680) {
                return true;
            }
        }
        return false;
    }

    public void setPolars() {
        checkL = Integer.MAX_VALUE;
        checkR = 0;
        checkB = 0;
        for(Block b : component) {
            checkL = Math.min(checkL, b.getLoc().x);
            checkR = Math.max(checkR, b.getLoc().x+20);
            checkB = Math.max(checkB, b.getLoc().y+20);
        }
    }
    
    public void rotate() {
        if(orientation < 4) {
            orientation++;
        }else {
            orientation = 1;
        }
        build(orientation);
        adjustEdge();
    }

    public void adjustEdge() {
        setPolars();
        while(checkL < 80) {
            update(EAST);
            setPolars();
        }
        while(checkR > 480) {
            update(WEST);
            setPolars();
        }
        while(checkB > 680) {
            y -= 20;
            for(Block b : component) {
                b.setY(-20);
            }
            setPolars();
        }
    }
    
    public Block[] getComponent() {
    	return component;
    }
    
    public int getOrientation() {
    	return orientation;
    }
    
    public Point getLoc() {
    	return new Point(x, y);
    }
}
