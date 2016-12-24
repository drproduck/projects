import javafx.scene.shape.Line;

/**
 * Created by Khiem on 12/19/2016.
 */
public class MiddleEdge extends Line {
    private AbstractNode startNode;
    private AbstractNode endNode;

    public MiddleEdge(AbstractNode startNode, AbstractNode endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

}
