
public class Square extends Shape {
    public Square(int xx, int yy, int o) {
        super(xx, yy);
        build(o);
    }

    @Override
    public void build(int orientation) {
        component[0] = new Block(x, y);
        component[1] = new Block(x+20, y);
        component[2] = new Block(x, y+20);
        component[3] = new Block(x+20, y+20);
    }

}
