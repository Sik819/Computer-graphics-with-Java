import java.util.ArrayList;
import java.util.List;

public class Memento {

    private Cell sheepLoc;
    private Cell wolfLoc;
    private Cell shepherdLoc;
    private Cell playerLoc;

    public Memento(Cell s, Cell w,Cell shep, Cell p){

        sheepLoc = s;
        wolfLoc = w;
        shepherdLoc = shep;
        playerLoc = p;
    }

    public Cell getSheepLoc() {
        return sheepLoc;
    }

    public Cell getWolfLoc() {
        return wolfLoc;
    }

    public Cell getShepherdLoc() {
        return shepherdLoc;
    }

    public Cell getPlayerLoc() {
        return playerLoc;
    }
}





