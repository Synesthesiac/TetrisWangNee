import java.awt.*;

public class Block {
    private int x, y;
    Color color;
    public Block(int xx, int yy, Color c) {
        x = xx;
        y = yy;
        color = c;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.drawRect(x, y, 20, 20);
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

    public Color getColor() {
        return color;
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

