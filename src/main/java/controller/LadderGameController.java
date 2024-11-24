package controller;

import domain.CountOfLine;
import domain.Height;
import domain.Ladder;
import domain.RungsBuilder;
import java.util.List;
import java.util.Map;
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
        List<String> names = getNames();
        List<String> outcomes = getOutcomes();
        Height height = getHeight();

        Ladder ladder = laddersService.createLadder(height, names, outcomes);
        outputView.printStatusOfLadders(names, outcomes, ladder.getRightRungStatus(), height.value());
        printResult(ladder.getResult());
    }

    private List<String> getNames() {
        outputView.printInputNamesGuide();
        return inputView.getStringList();
    }

    private List<String> getOutcomes() {
        outputView.printInputOutcomesGuid();
        return inputView.getStringList();
    }


    private Height getHeight() {
        outputView.printInputHeightGuide();
         int valueOfHeight = inputView.getUserIntegerInput();
        return new Height(valueOfHeight);
    }

    private void printResult(Map<String, String> result) {
        outputView.printInputTargetName();
         String targetName = inputView.getString();
         Map<String, String> resultToPrint = laddersService.getResultToPrint(result, targetName);
        outputView.printResult(resultToPrint);
    }

}
