import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        handle();
        root.getChildren().addListener((ListChangeListener.Change<? extends Object> change) -> {
            System.out.println("changed");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    private void handle() {

        sc.setOnMousePressed(event -> {
            if(event.getTarget().getClass().equals(sc.getClass())) {
                if (event.isPrimaryButtonDown()) {
                    Vertex node = new Vertex(event.getX(), event.getY());
                    root.getChildren().addAll(node);
                    nodes.add(node);
                }
            }
            else {
                System.out.println(event.getSource().getClass().toString());
                System.out.println(event.getSource());
                System.out.println(event.getTarget().getClass().toString());
                System.out.println(event.getTarget());
                AbstractNode node = (AbstractNode)((javafx.scene.Node)event.getTarget()).getParent();
                System.out.println(((javafx.scene.Node)event.getTarget()).getParent().getClass().toString());
                //node.select();
                //node.getClass().toString();
            }
        });
        /*
        sc.setOnMouseClicked(event -> {
            AbstractNode node = findNode(event.getX(), event.getY());
            if (node != null) {
                if (startNode != null&& !node.equals(startNode)) {
                    root.getChildren().addAll(new DirectedEdge(startNode, node));
                }
                else {
                    startNode = node;
                    startNode.select();
                }
            } else if (node == null&&startNode!=null) {
                startNode.deselect();
                startNode = null;
            }
        });
        */
    }

    public AbstractNode findNode(double x, double y) {
        for (AbstractNode node : nodes) {
            if (node.contains(x, y)) {
                return node;
            }
        }
        return null;
    }

    public void addEdge(AbstractNode start, AbstractNode end) {
        root.getChildren().addAll(new DirectedEdge(start, end));
        start.toFront();
        end.toFront();
    }
    private Group root;
    private Scene sc;
    private ArrayList<AbstractNode> nodes = new ArrayList<>();
    private AbstractNode startNode;
    /*
    private ToolBar toolbar = new ToolBar(){
        {

            Button edge = new Button("Add edge"){
                {
                    setOnAction(event -> {
                        mode.set(MODE.EDGE);
                    });
                }
            };
            Button node = new Button("Add node"){
                {
                    setOnAction(event -> {
                        mode.set(MODE.NODE);
                    });
                }
            };
            Button vertex = new Button("Add vertex"){
            {
                setOnAction(event -> {

                });
            }
        };
            this.getItems().addAll(edge, node, vertex);
            relocate(0,0);
        }
    };
    */
}
