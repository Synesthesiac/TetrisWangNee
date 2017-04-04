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

    public void update(int dir) {
        if(dir == Shape.WEST) {
            x -= 20;
        }else if(dir == Shape.EAST) {
            x += 20;
        }else if(dir == Shape.SOUTH) {
            y += 20;
        }
    }
    
    @Override
    public String toString() {
    	return "X";
    }

    public void setY(int i) {
        y += i;
    }
    
    public Point getLoc() {
    	return new Point(x, y);
    }
}

