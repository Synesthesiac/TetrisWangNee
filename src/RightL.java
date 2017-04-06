
public class RightL extends Shape {
    public RightL(int xx, int yy, int o) {
        super(xx, yy);
        build(o);
    }

    @Override
    public void build(int orientation) {
        if(orientation == 1) {
            component[0] = new Block(x, y);
            component[1] = new Block(x-20, y);
            component[2] = new Block(x-20, y-20);
            component[3] = new Block(x-20, y-40);
        }else if(orientation == 2) {
            component[0] = new Block(x, y);
            component[1] = new Block(x, y-20);
            component[2] = new Block(x+20, y-20);
            component[3] = new Block(x+40, y-20);
        }else if(orientation == 3) {
            component[0] = new Block(x, y);
            component[1] = new Block(x+20, y);
            component[2] = new Block(x+20, y+20);
            component[3] = new Block(x+20, y+40);
        }else if(orientation == 4) {
            component[0] = new Block(x, y);
            component[1] = new Block(x, y+20);
            component[2] = new Block(x-20, y+20);
            component[3] = new Block(x-40, y+20);
        }
    }

}
