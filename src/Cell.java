import java.awt.*;
import java.util.Random;

public class Cell extends Rectangle {

    private static Random rand = new Random();
    Color c;
    boolean isBlocked;
    public Cell(int x, int y) {
        super(x, y, 35, 35);
        c = new Color(rand.nextInt(30), rand.nextInt(155)+100, rand.nextInt(30));
        isBlocked = false;
    }

    public void setBlock(){
        isBlocked = true;

    }

    public void drawPattern(Graphics g){
        for(int i = 0, x1 = x , y1=y , x2 = x + this.width  , y2 = y + this.height ;
        i < height; i+=5) {
            g.drawLine(x1, y1 + i, x2 - i, y2);
            g.drawLine(x1 + i, y1, x2, y2-i);
        }
    }

    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(c);
        g.fillRect(x, y, 35, 35);
        g.setColor(Color.BLACK);
        g.drawRect(x,y, 35, 35);

        if (highlighted) {
            g.setColor(Color.LIGHT_GRAY);
            for(int i = 0; i < 10; i++){
                g.drawRoundRect(x+1, y+1, 33, 33, i, i);
            }
        }
    }

    @Override
    public boolean contains(Point target){
        if (target == null)
            return false;
        return super.contains(target);
    }

    public int getGrassHeight(){
        return c.getGreen()/50;
    }
}
