import bos.GameBoard;

public class CareTaker implements KeyObserver {
    private Stage stage;
    private Memento m;

    public CareTaker(Stage s){
        this.stage = s;
    }
    @Override
    public void notify(char c, GameBoard<Cell> gb) {
        if (c == ' ') {
            this.m = stage.getMemento();
        }
        else if (c == 'r') {
            try {
                stage.setMemento(m);
            }
            catch (NullPointerException e) {
                System.out.println("Previous state not found: Please save the game first");
                System.out.println(e.toString());
            }
        }
    }
}
