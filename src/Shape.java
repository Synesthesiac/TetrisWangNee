import java.awt.*;

public class Shape {
    Block[] component = new Block[4];
    int orientation, x, y;
    public Shape(int xx, int yy, int o) {
        x = xx;
        y = yy;
        orientation = o;
    }

    public void draw(Graphics2D g2) {
        if(component.length != 0) {
            for (Block b : component) {
                b.draw(g2);
            }
        }
    }

    public void update(String dir) {
    	if(dir.equals("RIGHT")) {
    		if(!checkSides()) {
	    		x += 20;
	    		for(Block b : component) {
	    			b.setX(20);
	    		}
    		}
    	}else if(dir.equals("LEFT")) {
    		if(!checkSides()) {
				x -= 20;
				for (Block b : component) {
					b.setX(-20);
				}
			}
    	}else {
    		if(!checkIntersect()) {
	    		y += 20;
	    		for(Block b : component) {
	    			b.setY(20);
	    		}
    		}
    	}
    }

    public boolean checkSides() {
        for(Block b : component) {
            Rectangle r = new Rectangle(b.getLoc().x, b.getLoc().y, 20, 20);
            if(r.intersects(Main.LEFTBOUND) || r.intersects(Main.RIGHTBOUND)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkIntersect() {
        for(Block b : component) {
            Rectangle r = new Rectangle(b.getLoc().x, b.getLoc().y, 20, 20);
            if(r.intersects(Main.BOTTOMBOUND)) {
                return true;
            }
        }

    	for(int r = 0; r < Main.fill.length; r++) {
    		for(int c = 0; c < Main.fill[r].length - 1; c++) {
    		    if(Main.fill[r][c] != null && Main.fill[r][c].intersects(this)) {
    		        return true;
                }
            }
		}
    	return false;
    }
    
    public void rotate() {
    	if(orientation < 4) {
    		orientation++;
    	}else {
    		orientation = 1;
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
