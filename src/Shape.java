import java.awt.*;

public class Shape {
    Block[] component = new Block[4];
    int orientation, x, y;
    public Shape(int xx, int yy, int o) {
        x = xx;
        y = yy;
        orientation = o;
    }

    public void build(){
    }

    public void draw(Graphics2D g2) {
        if(component.length != 0) {
            for (Block b : component) {
                b.draw(g2);
            }
        }
    }

    public void setOrientation() {
        if(orientation >= 4) {
            orientation = 1;
        }else {
            orientation++;
        }
        build();
    }

    public void setX(int i) {
        x += i;
    }

    public void setY(int i) {
        y += i;
    }
}
