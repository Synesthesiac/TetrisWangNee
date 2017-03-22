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

