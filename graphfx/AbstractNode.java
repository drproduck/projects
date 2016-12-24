import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Created by Khiem on 12/18/2016.
 */
public abstract class AbstractNode extends Group {

    private Delta d;
    private int PADDING = 5;
    private final SimpleStringProperty t = new SimpleStringProperty();
    protected final SimpleBooleanProperty selected = new SimpleBooleanProperty(false);
    protected Label text = new Label("name"){
        {
            setPadding(new Insets(5,5,5,5));
        }
    };

    //private FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();

    public ObservableValue connectionXProperty;
    public ObservableValue connectionYProperty;

    protected void handle() {
        setOnMousePressed(e -> {
                if (e.isPrimaryButtonDown()) {
                    d = new Delta(e.getX(), e.getY());

                } else if (e.isSecondaryButtonDown() && !e.isPrimaryButtonDown()) {
                    popup.show();
                }
        });
        setOnMouseDragged(e -> {
                setLayoutX(getLayoutX() + e.getX() - d.x);
                setLayoutY(getLayoutY() + e.getY() - d.y);
                e.consume();
        });
    }

    public abstract Shape getShape();
    public void select() {
        if (!selected.get()) {
            selected.set(true);
        }
    }
    public void deselect() {
        if (selected.get()) {
            selected.set(false);
        }
    }

    Stage popup = new Stage() {
        VBox y = new VBox();
        TextArea t = new TextArea();

        {
            t.minWidthProperty().bind(widthProperty());
            t.minHeightProperty().bind(heightProperty());
            setTitle("Editor");
            y.getChildren().addAll(t);
            this.setScene(new Scene(y, 250, 250));
            relocate(1000, 1000);
            t.textProperty().bindBidirectional(text.textProperty());
        }
    };
}
