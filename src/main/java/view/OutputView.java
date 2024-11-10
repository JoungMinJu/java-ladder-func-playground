package view;


import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String BLANK = "  ";
    private static final String BLANK_LINE = "|    ";
    private static final String RUNG_LINE = "|----";


    public void printStatusOfLadders(List<String> names, List<String> outcomes, List<List<Boolean>> rungsStatusPerLadder, int height) {
        printFixedWidth(names);
        for (int nowPosition = height - 1; nowPosition >= 0; nowPosition--) {
            printBlank();
            printStatusAtLadderPosition(rungsStatusPerLadder, nowPosition);
            System.out.println();
        }
        printFixedWidth(outcomes);
    }

    public void printFixedWidth(List<String> values) {
        for (String value : values) {
            System.out.printf(" %s ", value);
        }
        System.out.println();
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

    private void printBlank() {
        System.out.print(BLANK);
    }

    private Boolean doesRungExist(List<Boolean> rungPosition, int nowPosition) {
        return rungPosition.get(nowPosition);
    }

    public void printInputHeightGuide() {
        System.out.println("사다리의 높이는 몇 개인가요?");
    }

    public void printInputNamesGuide() {
        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
    }

    public void printInputOutcomesGuid() {
        System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
    }

    public void printInputTargetName() {
        System.out.println("결과를 보고 싶은 사람은?");
    }

    public void printResult(Map<String, String> result) {
        for (String name : result.keySet()) {
            System.out.printf("%s -> %s\n", name, result.get(name));
        }
    }
}
