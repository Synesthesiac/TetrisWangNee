import java.awt.*;

public class Rod extends Shape {
    public Rod(int xx, int yy, int o, Color c) {
        super(xx, yy, c);
        build(o, c);
    }

    @Override
    public void build(int orientation, Color c) {
        if(orientation == 1) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x, y+20, c);
            component[2] = new Block(x, y+40, c);
            component[3] = new Block(x, y+60, c);
        }else if(orientation == 2) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x+20, y, c);
            component[2] = new Block(x+40, y, c);
            component[3] = new Block(x+60, y, c);
        }else if(orientation == 3) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x, y+20, c);
            component[2] = new Block(x, y+40, c);
            component[3] = new Block(x, y+60, c);
        }else if(orientation == 4) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x+20, y, c);
            component[2] = new Block(x+40, y, c);
            component[3] = new Block(x+60, y, c);
        }
    }

}
