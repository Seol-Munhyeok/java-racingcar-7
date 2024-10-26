package racingcar;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputHandlerTest {
    private final InputHandler inputHandler = new InputHandler();
    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @Test
    @DisplayName("올바른 입력을 받았을 때 자동차 이름 리스트를 반환한다")
    void parseCarNames_Test() {
        String carNames = "pobi,woni,jun";  // given
        List<String> result = inputHandler.parseCarNames(carNames);  // when
        assertThat(result).containsExactly("pobi", "woni", "jun");  // then
    }

    @Test
    @DisplayName("공백 문자 또는 빈 문자열을 입력받으면 예외 처리한다")
    void parseCarNames_Test2() {
        String blankInput = " ";
        assertThatThrownBy(() -> inputHandler.parseCarNames(blankInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 횟수가 1 이상 1000 이하가 아니면 예외 처리한다")
    void getMoveCount_Test() {
        String outOfRangeInput = "1004";

        assertThatThrownBy( () -> {
            System.setIn(new java.io.ByteArrayInputStream(outOfRangeInput.getBytes()));
            inputHandler.getMoveCount();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 횟수로 숫자가 아닌 값을 받으면 예외 처리한다")
    void getMoveCount_Test2() {
        String nonNumberInput = "pobi";

        assertThatThrownBy( () -> {
            System.setIn(new java.io.ByteArrayInputStream(nonNumberInput.getBytes()));
            inputHandler.getMoveCount();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
