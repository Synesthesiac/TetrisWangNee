public class TShape extends Shape {
	
    public TShape(int xx, int yy) {
        super(xx, yy, 1);
        getComponent()[0] = new Block(xx, yy);
        getComponent()[1] = new Block(xx-20, yy+20);
        getComponent()[2] = new Block(xx, yy+20);
        getComponent()[3] = new Block(xx+20, yy+20);
    }
    
    @Override
    public void update(String dir) {
    	super.update(dir);
    }

    public void rotate() {
    	super.rotate();
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
