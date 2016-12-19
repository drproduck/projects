import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * Created by Khiem on 12/18/2016.
 */
public class Node extends Group {
    private double x;
    private double y;
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

    private DoubleBinding centerXProperty = new DoubleBinding() {
        {
            super.bind(layoutXProperty(), rect.widthProperty());
        }
        @Override
        protected double computeValue() {
            return layoutXProperty().doubleValue()+ rect.widthProperty().doubleValue()/2;
        }
    };

    //public SimpleDoubleProperty centerXProperty = new SimpleDoubleProperty();

    public Node(double cx, double cy) {
        super();
        x = cx - DEF_WIDTH / 2;
        y = cy - DEF_HEIGHT / 2;
        localToScene(x, y);
        text.relocate(x,y);
        rect.relocate(x,y);
        getChildren().addAll(rect, text);
        handle();


        layoutXProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("layout x: "+newValue.doubleValue());
        });
        rect.widthProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("width: "+newValue.doubleValue());
        });
        centerXProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("center x: "+newValue.doubleValue());
        });
        text.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            System.out.println("changed " + oldValue + "->" + newValue);
            if (newValue.doubleValue()<50) {
                rect.setWidth(50);
            }
            else rect.setWidth(newValue.doubleValue());
        });
        text.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            System.out.println("changed " + oldValue + "->" + newValue);
            if (newValue.doubleValue()<50) {
                rect.setHeight(50);
            }
            else rect.setHeight(newValue.doubleValue());
        });
        /*
        rect.widthProperty().bind(new DoubleBinding() {
            @Override
            protected double computeValue() {

                if (text.widthProperty().get()<50) {
                    return 50;
                }
                return text.widthProperty().get();
            }
        });
        rect.heightProperty().bind(new DoubleBinding() {
            @Override
            protected double computeValue() {
                if (text.heightProperty().get()<50) {
                    return 50;
                }
                return text.heightProperty().get();
            }
        });
        */
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
            setLayoutX(getLayoutX() + e.getX() - d.x);
            setLayoutY(getLayoutY() + e.getY() - d.y);
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
