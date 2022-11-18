package bridge.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import bridge.model.bridge.Bridge;
import bridge.model.bridge.Node;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    InputView inputView;

    @BeforeEach
    public void setUp() {
        inputView = new InputView();
    }

    @Nested
    @DisplayName("브릿지 사이즈 입력테스트")
    class ReadBridgeSizeTest extends NsTest {


        @DisplayName("유효하지 않은 길이로 입력이 들어올 경우 예외메시지를 출력한다.")
        @ParameterizedTest
        @ValueSource(strings = {"2", "21", "0", "30"})
        public void throwExceptionWhenInvalidBridgeSize(String bridgeSize) {
            String exceptionMessage = "다리 길이는 " + Bridge.MIN_SIZE + "부터 " + Bridge.MAX_SIZE + " 사이의 숫자여야 합니다.";
            assertSimpleTest(() -> {
                run(bridgeSize, "3");
                assertThat(
                        output().contains(exceptionMessage)
                );
            });
        }

        @Override
        protected void runMain() {
            inputView.readBridgeSize();
        }
    }

    @Nested
    @DisplayName("브릿지 위치 이동 테스트")
    class ReadMove {
        @Nested
        @DisplayName("위로 가고싶을 때")
        class ReadMoveToUp extends NsTest {
            @Test
            @DisplayName("U를 입력하면 Node.UP을 반환한다.")
            public void returnUPWhenInputIsU() {
                assertSimpleTest(() -> {
                    run("U");
                });
            }

            @Override
            protected void runMain() {
                Node result = inputView.readMoving();
                assertThat(result).isEqualTo(Node.UP);
            }
        }

        @Nested
        @DisplayName("아래로 가고싶을 때")
        class ReadMoveToDown extends NsTest {
            @Test
            @DisplayName("D를 입력하면 Node.DOWN을 반환한다.")
            public void returnUPWhenInputIsU() {
                assertSimpleTest(() -> {
                    run("D");
                });
            }

            @Override
            protected void runMain() {
                Node result = inputView.readMoving();
                assertThat(result).isEqualTo(Node.DOWN);
            }
        }
    }

    @Nested
    @DisplayName("게임 다시시도 여부 입력 테스트")
    class ReadGameCommand {
        @Nested
        @DisplayName("종료하고 싶을 때")
        class ReadToQuit extends NsTest {

            @Test
            @DisplayName("Q를 입력하면 GameCommand.QUIT을 반환한다.")
            public void returnQUIT() {
                assertSimpleTest(() -> {
                    run("Q");
                });
            }

            @Override
            protected void runMain() {
                GameCommand command = inputView.readGameCommand();
                assertThat(command).isEqualTo(GameCommand.QUIT);
            }
        }
    }

    @Nested
    @DisplayName("다시 시도하고 싶을 때")
    class ReadToReplay extends NsTest {

        @Test
        @DisplayName("R을 입력하면 GameCommand.REPLAY를 반환한다.")
        public void returnREPLAY() {
            assertSimpleTest(() -> {
                run("R");
            });
        }

        @Override
        protected void runMain() {
            GameCommand command = inputView.readGameCommand();
            assertThat(command).isEqualTo(GameCommand.REPLAY);
        }
    }
}