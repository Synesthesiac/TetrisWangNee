public class TShape extends Shape {
    public TShape(int xx, int yy) {
        super(xx, yy);
        component[0] = new Block(xx, yy);
        component[1] = new Block(xx-20, yy+20);
        component[2] = new Block(xx, yy+20);
        component[3] = new Block(xx+20, yy+20);
    }
    
    @Override
    public void update(int dir) {
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
            if(checkL <= 80) {
                x = 100;
            }else if(checkR >= 480) {
                x = 440;
            }
            if(checkB >= 680) {
                y = 640;
            }
        	component[0] = new Block(x, y);
            component[1] = new Block(x-20, y+20);
            component[2] = new Block(x, y+20);
            component[3] = new Block(x+20, y+20);
        }else if(orientation == 2) {
            if(checkL <= 80) {
                x = 100;
            }else if(checkR >= 480) {
                x = 460;
            }
            if(checkB >= 680) {
                y = 600;
            }
            component[0] = new Block(x, y);
            component[1] = new Block(x-20, y+20);
            component[2] = new Block(x, y+20);
            component[3] = new Block(x, y+40);
        }else if(orientation == 3) {
            if(checkL <= 80) {
                x = 80;
            }else if(checkR >= 480) {
                x = 420;
            }
            if(checkB >= 680) {
                y = 640;
            }
            component[0] = new Block(x, y);
            component[1] = new Block(x+20, y);
            component[2] = new Block(x+40, y);
            component[3] = new Block(x+20, y+20);
        }else if(orientation == 4) {
            if(checkL <= 80) {
                x = 80;
            }else if(checkR >= 480) {
                x = 440;
            }
            if(checkB >= 680) {
                y = 600;
            }
            component[0] = new Block(x, y);
            component[1] = new Block(x, y+20);
            component[2] = new Block(x+20, y+20);
            component[3] = new Block(x, y+40);
        }
    }
}
