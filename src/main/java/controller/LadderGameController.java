package controller;

import domain.CountOfLine;
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
        CountOfLine countOfLine = getcountOfLine();
        Height height = getHeight();

        Ladder ladder = laddersService.createLadder(countOfLine, height);
        outputView.printStatusOfLadders(ladder.getRightRungStatus(), height.value());
        outputView.printResult(ladder.getResult());
    }

    private CountOfLine getcountOfLine() {
        outputView.printInputCountOfLineGuide();
        final int valueOfCountOfLine = inputView.getUserIntegerInput();
        return new CountOfLine(valueOfCountOfLine);
    }

    private Height getHeight() {
        outputView.printInputHeightGuide();
        final int valueOfHeight = inputView.getUserIntegerInput();
        return new Height(valueOfHeight);
    }

}
