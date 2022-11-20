package bridge.view;

import bridge.model.bridge.Node;
import bridge.view.inputCallback.ReadBridgeSizeCallback;
import bridge.view.inputCallback.ReadGameCommandCallback;
import bridge.view.inputCallback.ReadLineTemplate;
import bridge.view.inputCallback.ReadNodeToMoveCallback;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        ReadLineTemplate<Integer> readInt = new ReadLineTemplate() {};
        return readInt.repeatToReadWhileValid(new ReadBridgeSizeCallback());
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public Node readMoving() {
        ReadLineTemplate<Node> readNode = new ReadLineTemplate() {};
        return readNode.repeatToReadWhileValid(new ReadNodeToMoveCallback());
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public GameCommand readGameCommand() {
        ReadLineTemplate<GameCommand> readGameCommand = new ReadLineTemplate() {};
        return readGameCommand.repeatToReadWhileValid(new ReadGameCommandCallback());
    }
}
