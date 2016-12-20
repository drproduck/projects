import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by Khiem on 12/18/2016.
 */
public class Node extends Group {

    private Delta d;
    private int DEF_WIDTH = 50;
    private int DEF_HEIGHT = 50;
    private int PADDING = 5;
    private final SimpleStringProperty t = new SimpleStringProperty();
    private Label text = new Label("I THINK\nTHEREFORE I AM"){
        {
            setPadding(new Insets(5,5,5,5));
        }
    };
    private Rectangle rect = new Rectangle(0,0,50,50){
        {
            setFill(Color.BEIGE);
            setStroke(Color.BLACK);
            DropShadow ds = new DropShadow(5.0, 3.0, 3.0, Color.color(0.4, 0.5, 0.5));
            setEffect(ds);
        }
    };

    //private FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();

    public DoubleBinding centerXProperty;
    public DoubleBinding centerYProperty;

    public Node(double cx, double cy) {
        super();

        relocate(cx - DEF_WIDTH / 2,cy - DEF_HEIGHT / 2);
        getChildren().addAll(rect, text);
        handle();
        centerXProperty = new DoubleBinding() {
            {
                super.bind(layoutXProperty(), rect.widthProperty());
            }
            @Override
            protected double computeValue() {
                return layoutXProperty().doubleValue()+ rect.widthProperty().doubleValue()/2;
            }
        };
        centerYProperty = new DoubleBinding() {
            {
                super.bind(layoutYProperty(), rect.heightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutYProperty().doubleValue()+rect.heightProperty().doubleValue()/2;
            }
        };
        rect.widthProperty().bind(new DoubleBinding() {
            {
                super.bind(text.widthProperty());
            }
            @Override
            protected double computeValue() {

                if (text.widthProperty().get()<50) {
                    return 50;
                }
                return text.widthProperty().get();
            }
        });
        rect.heightProperty().bind(new DoubleBinding() {
            {
                super.bind(text.heightProperty());
            }
            @Override
            protected double computeValue() {
                if (text.heightProperty().get()<50) {
                    return 50;
                }
                return text.heightProperty().get();
            }
        });
        // hanger
        getChildren().addAll(new Anchor(new SimpleDoubleProperty(0), rect.heightProperty().divide(2)), new Anchor(Bindings.divide(rect.widthProperty(),2), new SimpleDoubleProperty(0)),
                new Anchor(rect.widthProperty(), Bindings.divide(rect.heightProperty(),2)),new Anchor(Bindings.divide(rect.widthProperty(),2), rect.heightProperty()));
    }
    private void handle() {
        setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()&&!e.isSecondaryButtonDown()) {
                d = new Delta(e.getX(), e.getY());
                e.consume();
            }
            else if (e.isSecondaryButtonDown()&&!e.isPrimaryButtonDown()) {
                popup.show();
            }
        });
        setOnMouseDragged(e -> {
            setLayoutX(getLayoutX()+e.getX() - d.x);
            setLayoutY(getLayoutY()+e.getY() - d.y);
            e.consume();
        });
    }

    public static void main(String[] args) {
    }
    /*
    private Button popup = new Button() {
        {
            setText("Open Dialog");
            setOnAction(
                    new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent event) {
                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);
                            dialogVbox.getChildren().add(new Text("This is a Dialog"));
                            Scene dialogScene = new Scene(dialogVbox, 300, 200);
                            dialog.setScene(dialogScene);
                            dialog.show();
                        }
                    });
        }
    };
    */

    Stage popup = new Stage() {
        VBox y = new VBox();
        TextArea t = new TextArea();

        {
            t.minWidthProperty().bind(widthProperty());
            t.minHeightProperty().bind(heightProperty());
            setTitle("Editor");
            y.getChildren().addAll(t);
            this.setScene(new Scene(y, 300, 300));
            t.textProperty().bindBidirectional(text.textProperty());
        }
    };
}
