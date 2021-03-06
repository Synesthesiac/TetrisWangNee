import java.awt.*;

public class Square extends Shape {
    public Square(int xx, int yy, int o, Color c) {
        super(xx, yy, c);
        build(o, c);
    }

    @Override
    public void build(int orientation, Color c) {
        component[0] = new Block(x, y, c);
        component[1] = new Block(x+20, y, c);
        component[2] = new Block(x, y+20, c);
        component[3] = new Block(x+20, y+20, c);
    }

}
