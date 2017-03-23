import java.awt.*;

public class Block {
    private int x, y;
    public Block(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, 20, 20);
    }

    public boolean intersects(Shape s) {
        for(int i = 0; i < s.component.length; i++) {
            Block b = s.component[i];
            Rectangle a = new Rectangle(b.x, b.y+20, 20, 20);
            Rectangle c = new Rectangle(x, y, 20, 20);
            if(c.intersects(a)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
    	return "X";
    }
    
    public void setX(int i) {
    	x += i;
    }
    
    public void setY(int i) {
    	y += i;
    }
    
    public Point getLoc() {
    	return new Point(x, y);
    }
}

