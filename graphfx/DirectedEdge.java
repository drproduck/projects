import javafx.scene.shape.Line;

/**
 * Created by Khiem on 12/23/2016.
 */
public class DirectedEdge extends Edge {
    private Line l1 = new Line();
    private Line l2 = new Line();
    public DirectedEdge(AbstractNode startNode, AbstractNode endNode) {
        super(startNode, endNode);
        l1.startXProperty().bind(line.endXProperty());
        l1.startYProperty().bind(line.endYProperty());
    }

    @Override
    public double getCost() {
        return 0;
    }
}
