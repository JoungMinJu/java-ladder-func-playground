package view;


import java.util.List;

public class OutputView {

    private static final String BLANK_LINE = "|    ";
    private static final String RUNG_LINE = "|----";


    public void printStatusOfLadders(List<List<Boolean>> rungsStatusPerLadder, int height) {
        for (int nowPosition = height - 1; nowPosition >= 0; nowPosition--) {
            printStatusAtLadderPosition(rungsStatusPerLadder, nowPosition);
            System.out.println();
        }
    }

    private void printStatusAtLadderPosition(List<List<Boolean>> rungsStatusPerLadder, int nowPosition) {
        for (List<Boolean> rungStatus : rungsStatusPerLadder) {
            System.out.print(createOrSkip(rungStatus, nowPosition));
        }
    }

    private String createOrSkip(List<Boolean> rungPosition, int nowPosition) {
        if (doesRungExist(rungPosition, nowPosition)) {
            return RUNG_LINE;
        }
        return BLANK_LINE;
    }

    private Boolean doesRungExist(List<Boolean> rungPosition, int nowPosition) {
        return rungPosition.get(nowPosition);
    }

    public void printInputCountOfLineGuide() {
        System.out.println("사다리의 넓이는 몇 개인가요?");
    }

    public void printInputHeightGuide() {
        System.out.println("사다리의 높이는 몇 개인가요?");
    }

    public void printResult(List<Integer> result) {
        for (int index = 0; index < result.size(); index++) {
            System.out.printf("%d -> %d%n", index, result.get(index));
        }
    }
}
