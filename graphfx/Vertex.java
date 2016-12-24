import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Created by Khiem on 12/23/2016.
 */
public class Vertex extends AbstractNode {
    private static final double DEF_RADIUS = 10;
    private Delta d = new Delta(0,0);
    private Circle circle = new Circle(){
        {
            setStroke(Color.BLACK);
            setFill(Color.WHITE);
            radiusProperty().bind (new DoubleBinding(){
            {
                super.bind(text.widthProperty(), text.heightProperty());
            }

                @Override
                protected double computeValue() {
                    double diag = 0.5*Math.sqrt(Math.pow(text.widthProperty().get(), 2) + Math.pow(text.heightProperty().get(), 2));
                    if (diag < DEF_RADIUS) {
                        return DEF_RADIUS;
                    } else return diag;
                }
            });
        }
    };

    protected void handle() {
        setOnMousePressed(e -> {
            circle.toFront();
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
        setOnMouseReleased(event -> {
            text.toFront();
        });
    }
    public Vertex(double x, double y) {
        text.layoutXProperty().bind(circle.centerXProperty().subtract(text.widthProperty().divide(2)));
        text.layoutYProperty().bind(circle.centerYProperty().subtract(text.heightProperty().divide(2)));
        getChildren().addAll(circle,text);
        handle();
        getShape().strokeWidthProperty().bind(Bindings.when(selected).then(5).otherwise(1));
        getShape().strokeProperty().bind(Bindings.when(selected).then(Color.ORANGE).otherwise(Color.BLACK));
        connectionXProperty = circle.centerXProperty();
        connectionYProperty = circle.centerYProperty();
        relocate(x-circle.getRadius(), y-circle.getRadius());
    }

    @Override
    public Shape getShape() {
        return circle;
    }
}
