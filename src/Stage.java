import java.awt.*;
import java.util.*;
import java.time.*;
import java.util.List;

import bos.Pair;
import bos.RelativeMove;


public class Stage extends KeyObservable {
    protected Grid grid;
    protected Character sheep;
    protected Character shepherd;
    protected Character wolf;
    private List<Character> allCharacters;
    protected Player player;
    final List<Block> blocks = new ArrayList<Block>();

    private Instant timeOfLastMove = Instant.now();

    public Stage() {
        SAWReader sr = new SAWReader("data/stage1.saw");
        grid     = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAtRowCol(sr.getShepherdLoc().first, sr.getShepherdLoc().second), new StandStill());
        sheep    = new Sheep(grid.cellAtRowCol(sr.getSheepLoc().first, sr.getSheepLoc().second), new MoveTowards(shepherd));
        wolf     = new Wolf(grid.cellAtRowCol(sr.getWolfLoc().first, sr.getWolfLoc().second), new MoveTowards(sheep));
        player = new Player(grid.getRandomCell());
        this.register(player);

        allCharacters = new ArrayList<Character>();
        allCharacters.add(sheep); allCharacters.add(shepherd); allCharacters.add(wolf);

        List<Pair<Integer, Integer>> positions = sr.getBlockLocs();
        for(Pair<Integer, Integer> c : positions){
            blocks.add(new Block(grid.cellAtRowCol(c.first, c.second)));
        }
        grid.blockCells(blocks);    }

    public void update(){
        if (!player.inMove()) {

            if (sheep.location == shepherd.location) {
                System.out.println("The sheep is safe :)");
                System.exit(0);
            } else if (sheep.location == wolf.location) {
                System.out.println("The sheep is dead :(");
                System.exit(1);
            } else {
                if (sheep.location.x == sheep.location.y ) {
                    sheep.setBehaviour(new StandStill());
                    shepherd.setBehaviour(new MoveTowards(sheep));
                }
                allCharacters.forEach((c) -> c.aiMove(this).perform());
                player.startMove();
                timeOfLastMove = Instant.now();
               // allCharacters.forEach((c) -> this.blockCharacter(c,blocks));
//                this.blockCharacter(wolf,blocks);
//                this.blockCharacter(shepherd,blocks);

            }
    }}

    public void setMemento(Memento m){
        sheep.setLocationOf(m.getSheepLoc());
        wolf.setLocationOf(m.getWolfLoc());
        shepherd.setLocationOf(m.getShepherdLoc());
        player.setLocationOf(m.getPlayerLoc());
    }

    public Memento getMemento(){
        Memento state = new Memento(sheep.getLocationOf(),wolf.getLocationOf(),shepherd.getLocationOf(),player.getLocationOf());
        return state;
    }

    public void paint(Graphics g, Point mouseLocation) {
        grid.paint(g, mouseLocation);
        for(Block b : blocks)
            b.paint(g);
        sheep.paint(g);
        shepherd.paint(g);
        wolf.paint(g);
        player.paint(g);
    }
}