import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by Khiem on 12/23/2016.
 */
public class RectangularNode extends AbstractNode {
    private int DEF_WIDTH = 50;
    private int DEF_HEIGHT = 50;

    private Rectangle rect = new Rectangle(0,0,50,50){
        {
            setFill(Color.BEIGE);
            setStroke(Color.BLACK);
            DropShadow ds = new DropShadow(5.0, 3.0, 3.0, Color.color(0.4, 0.5, 0.5));
            setEffect(ds);
            setArcHeight(15);
            setArcWidth(15);
        }
    };
    public RectangularNode(double cx, double cy) {
        handle();
        relocate(cx - DEF_WIDTH / 2,cy - DEF_HEIGHT / 2);
        getChildren().addAll(rect, text);
        connectionXProperty = new DoubleBinding() {
            {
                super.bind(layoutXProperty(), rect.widthProperty());
            }
            @Override
            protected double computeValue() {
                return layoutXProperty().doubleValue()+ rect.widthProperty().doubleValue()/2;
            }
        };
        connectionYProperty = new DoubleBinding() {
            {
                super.bind(layoutYProperty(), rect.heightProperty());
            }
            @Override
            protected double computeValue() {
                return layoutYProperty().doubleValue()+rect.heightProperty().doubleValue()/2;
            }
        };
        rect.widthProperty().bind(Bindings.when(text.widthProperty().lessThanOrEqualTo(50)).then(50).otherwise(text.widthProperty()));
        rect.heightProperty().bind(Bindings.when(text.heightProperty().lessThanOrEqualTo(50)).then(50).otherwise(text.heightProperty()));
        // hanger
        getChildren().addAll(new Anchor(new SimpleDoubleProperty(0), rect.heightProperty().divide(2)), new Anchor(Bindings.divide(rect.widthProperty(),2), new SimpleDoubleProperty(0)),
                new Anchor(rect.widthProperty(), Bindings.divide(rect.heightProperty(),2)),new Anchor(Bindings.divide(rect.widthProperty(),2), rect.heightProperty()));
    }

    @Override
    public Shape getShape() {
        return rect;
    }
}
