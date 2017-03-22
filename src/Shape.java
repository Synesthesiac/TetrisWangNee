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
    	int checkX, checkY;
    	if(dir.equals("RIGHT")) {
    		checkX = 0;
    		for(Block b : component) {
    			checkX = Math.max(checkX, (int)(b.getLoc().getX()) - 80);
    		}
    		if(checkX < Main.map[0].length * 19) {
	    		x += 20;
	    		for(Block b : component) {
	    			b.setX(20);
	    		}
    		}
    	}else if(dir.equals("LEFT")) {
    		checkX = Integer.MAX_VALUE;
    		for(Block b : component) {
    			checkX = Math.min(checkX, (int)(b.getLoc().getX()));
    		}
    		if(checkX > 80) {
				x -= 20;
				for (Block b : component) {
					b.setX(-20);
				}
			}
    	}else {
    		checkY = 0;
    		for(Block b : component) {
    			checkY = Math.max(checkY, (int)(b.getLoc().getY()) - 80);
    		}
    		if(checkY < Main.map.length * 19) {
	    		y += 20;
	    		for(Block b : component) {
	    			b.setY(20);
	    		}
    		}
    	}
    }
    
    public boolean checkLoc() {
    	int checkY = 0;
    	for(Block b : component) {
    		checkY = Math.max(checkY, (int)(b.getLoc().getY()) - 80);
    	}
    	if(checkY >= Main.map.length * 19) {
    		return true;
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
