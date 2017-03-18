public class TShape extends Shape {

    public TShape(int xx, int yy, int o) {
        super(xx, yy, o);
        build();
    }

    @Override
    public void build() {
        //hello
        /*  1       2      3         4
            x       x      x
           xxx     xx      xx       xxx
                    x      x         x
        */
        if(orientation == 1) {
            component[0] = new Block(x, y);
            component[1] = new Block(x-20, y+20);
            component[2] = new Block(x, y+20);
            component[3] = new Block(x+20, y+20);
        }else if(orientation == 2) {
            component[0] = new Block(x, y);
            component[1] = new Block(x-20, y+20);
            component[2] = new Block(x, y+20);
            component[3] = new Block(x, y+40);
        }else if(orientation == 4) {
            component[0] = new Block(x, y);
            component[1] = new Block(x, y+20);
            component[2] = new Block(x+20, y+20);
            component[3] = new Block(x, y+40);
        }else if(orientation == 3) {
            component[0] = new Block(x, y);
            component[1] = new Block(x+20, y);
            component[2] = new Block(x+40, y);
            component[3] = new Block(x+20, y+20);
        }
    }
}
