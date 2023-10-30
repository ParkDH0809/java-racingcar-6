package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @ValueSource(strings = {"pobi,javaji", ""})
    @ParameterizedTest
    void 이름에_대한_예외_처리_테스트(String names) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(names, "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ValueSource(strings = {"1번", " "})
    @ParameterizedTest
    void 시도횟수에_대한_예외_처리_테스트(String numbers) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", numbers))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
