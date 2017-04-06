import java.awt.*;

public class TShape extends Shape {
    public TShape(int xx, int yy, int o, Color c) {
        super(xx, yy, c);
        build(o, c);
    }

    @Override
    public void build(int orientation, Color c) {
        /*  1       2      4         3
            x       x      x
           xxx     xx      xx       xxx
                    x      x         x
        */
        if(orientation == 1) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x-20, y+20, c);
            component[2] = new Block(x, y+20, c);
            component[3] = new Block(x+20, y+20, c);
        }else if(orientation == 2) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x+20, y-20, c);
            component[2] = new Block(x+20, y, c);
            component[3] = new Block(x+20, y+20, c);
        }else if(orientation == 3) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x-20, y-20, c);
            component[2] = new Block(x, y-20, c);
            component[3] = new Block(x+20, y-20, c);
        }else if(orientation == 4) {
            component[0] = new Block(x, y, c);
            component[1] = new Block(x-20, y, c);
            component[2] = new Block(x-20, y-20, c);
            component[3] = new Block(x-20, y+20, c);
        }
    }
}
