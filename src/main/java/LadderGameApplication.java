import controller.LadderGameController;
import domain.RandomRungsBuilder;

public class LadderGameApplication {

    public static void main(String[] args) {
        final RandomRungsBuilder randomRungsBuilder = new RandomRungsBuilder();

        LadderGameController ladderGameController = new LadderGameController(randomRungsBuilder);
        ladderGameController.start();
    }

}
