package controller;

import domain.CountOfLadders;
import domain.Height;
import domain.Ladder;
import domain.RungsBuilder;
import service.LadderService;
import view.InputView;
import view.OutputView;

public class LadderGameController {

    private final LadderService laddersService;
    private final OutputView outputView;
    private final InputView inputView;

    public LadderGameController(RungsBuilder rungsBuilder) {
        this.laddersService = new LadderService(rungsBuilder);
        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void start() {
        CountOfLadders countOfLadders = getCountOfLadders();
        Height height = getHeight();

        Ladder ladder = laddersService.createLadder(countOfLadders, height);
        outputView.printStatusOfLadders(ladder.getRightRungStatus(), height.value());
        System.out.println(ladder);
    }

    private CountOfLadders getCountOfLadders() {
        outputView.printInputCountOfLaddersGuide();
        final int valueOfCountOfLadders = inputView.getUserIntegerInput();
        return new CountOfLadders(valueOfCountOfLadders);
    }

    private Height getHeight() {
        outputView.printInputHeightGuide();
        final int valueOfHeight = inputView.getUserIntegerInput();
        return new Height(valueOfHeight);
    }

}
