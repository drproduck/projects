package violetFX;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by Khiem on 12/3/2016.
 */
public class Actor extends Canvas {
    private Delta delta;
    public Actor(double x, double y) {
        super(80, 60);
        relocate(x, y);
        delta = new Delta();
        draw();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.YELLOW);
        gc.strokeLine(30, 10, 30, 40);
        gc.strokeLine(20, 20, 20 ,40);
        gc.rect(0, 0, 80, 60);
        gc.strokeText("Actor",5,6);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.stroke();
        gc.fill();
    }
    private void setup() {
        setOnMousePressed(event -> {
        Delta delta = new Delta();
            delta.x = event.getX();
            delta.y = event.getY();
        });
        setOnMouseDragged(event -> {
            double newX = getLayoutX() + event.getX() - delta.x;
            double newY = getLayoutY() + event.getY() - delta.y;
            if (newX > 0 && newX < getScene().getWidth()) {
                setLayoutX(newX);
            }
            if (newY > 0 && newY < getScene().getHeight()) {
                setLayoutY(newY);
            }
        });
    }

    class Delta{
        double x;
        double y;
    }
}


