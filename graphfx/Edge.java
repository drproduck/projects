import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Created by Khiem on 12/19/2016.
 */
public abstract class Edge extends Group {
    private AbstractNode startNode;
    private AbstractNode endNode;
    private Label label = new Label();
    protected Line line;

    public Edge(AbstractNode startNode, AbstractNode endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        line.startXProperty().bind(startNode.connectionXProperty);
        line.startYProperty().bind(startNode.connectionXProperty);
        line.endXProperty().bind(endNode.connectionXProperty);
        line.endYProperty().bind(endNode.connectionYProperty);
        label.layoutXProperty().bind(line.startXProperty().divide(2).subtract(label.widthProperty()));
        label.layoutYProperty().bind(line.startYProperty().divide(2).subtract(label.heightProperty()));
        getChildren().addAll(line,label);
    }

    public Edge(AbstractNode startNode, AbstractNode endNode, String string) {
        this(startNode, endNode);
        label.setText(string);
    }

    public abstract double getCost();
    private
    Stage popup = new Stage() {
        VBox y = new VBox();
        TextArea t = new TextArea();

        {
            t.minWidthProperty().bind(widthProperty());
            t.minHeightProperty().bind(heightProperty());
            setTitle("Editor");
            y.getChildren().addAll(t);
            this.setScene(new Scene(y, 200, 200));
            t.textProperty().bindBidirectional(label.textProperty());
        }
    };
}
