import java.awt.*;
import java.util.Optional;

public class Block {
   private Cell location;
    Optional<Color> display;

    public Block(Cell location){
        this.location = location;
        display = Optional.of(new Color(222,184,135));
    }

    public Cell getLocation() {
        return location;
    }


    public void paint(Graphics g){
        if(display.isPresent()) {
            g.setColor(Color.BLACK);
            g.fillRoundRect(location.x,location.y,location.width,location.height,3,3);
            g.setColor(display.get());
            g.fillRect(location.x , location.y , location.width , location.height );
        }
        g.setColor(Color.black);
        location.drawPattern(g);

    }



}
