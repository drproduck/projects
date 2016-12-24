import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Khiem on 12/20/2016.
 */
public class Anchor extends Circle {
    public Anchor(ObservableValue x, ObservableValue y) {
        super(5);
        centerXProperty().bind(x);
        centerYProperty().bind(y);
        setStroke(Color.BLACK);
        setFill(Color.WHITE);
    }

}
