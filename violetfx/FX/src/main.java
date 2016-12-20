import com.sun.javafx.geom.Edge;
import com.sun.org.apache.bcel.internal.generic.POP;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Created by Khiem on 12/18/2016.
 */
public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        root = new Group();
        sc = new Scene(root, 800, 600, Color.ANTIQUEWHITE);
        stage.setScene(sc);
        stage.show();
        Text text = new Text("This is a test");
        text.setX(10);
        text.setY(50);
        text.setFont(new Font(20));
        text.getTransforms().add(new Rotate(60, 60, 60));
        Node node1 = new Node(200, 200);
        Node node2 = new Node(400, 400);
        StraightEdge edge = new StraightEdge(node1, node2);
        root.getChildren().addAll(node1, node2, edge);
        node1.toFront();
        node2.toFront();
        sceneHandle();
    }
    private void sceneHandle() {
        sc.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                root.getChildren().add(new Node(event.getX(), event.getY()));
                System.out.println("clicked");
                System.out.println(root.getChildren().size());
            }
        });
    }

    public void addEdge(Node start, Node end) {
        root.getChildren().addAll(new StraightEdge(start, end));
        start.toFront();
        end.toFront();
    }
    private Group root;
    private Scene sc;
}
