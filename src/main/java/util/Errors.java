package util;

public class Errors {

    private Errors() {
    }

    public static final String LADDERS_MUST_CONTAINS_LEAST_ONE_LADDER = "사다리는 한 개 이상 존재해야 합니다.";
    public static final String LADDER_HEIGHT_MUST_BE_POSITIVE = "사다리의 높이는 1 이상이어야 합니다.";
    public static final String ALL_LINE_MUST_HAVE_SAME_HEIGHT = "각 사다리의 높이는 모두 동일해야합니다.";
    public static final String ADJACENT_LADDERS_CANNOT_HAVE_RUNG_AT_SAME_POSITION = "인접한 두 사다리는 같은 위치에 가로줄을 가질 수 없습니다.";
    public static final String INPUT_IS_NOT_INTEGER = "정수가 입력되어야 합니다.";
    public static final String RUNG_STATUS_LENGTH_MUST_MATCH = "Line의 left rung status의 길이와 right rung status의 길이는 일치해야합니다.";


}
