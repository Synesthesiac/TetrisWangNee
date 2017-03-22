
public class Rod extends Shape {
    public Rod(int xx, int yy) {
        super(xx, yy, 1);
    }

    public void build() {
        if(orientation == 1) {
            component[0] = new Block(x, y);
            component[1] = new Block(x, y+20);
            component[2] = new Block(x, y+40);
            component[3] = new Block(x, y+60);
        }else if(orientation == 2) {
            component[0] = new Block(x, y);
            component[1] = new Block(x+20, y);
            component[2] = new Block(x+40, y);
            component[3] = new Block(x+60, y);
        }else if(orientation == 3) {
            component[0] = new Block(x, y);
            component[1] = new Block(x, y+20);
            component[2] = new Block(x, y+40);
            component[3] = new Block(x, y+60);
        }else if(orientation == 4) {
            component[0] = new Block(x, y);
            component[1] = new Block(x+20, y);
            component[2] = new Block(x+40, y);
            component[3] = new Block(x+60, y);
        }
    }
}