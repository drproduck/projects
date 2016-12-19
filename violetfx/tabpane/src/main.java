import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by Khiem on 11/17/2016.
 */
public class main extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        DragBox box = new DragBox();
        box.setPrefHeight(100);
        box.setPrefWidth(100);
        Sun sun = new Sun(200, 300);
        Pane root = new Pane();
        root.setStyle("-fx-background-color: red");
        root.getChildren().addAll(sun);
        root.setOnMouseClicked(event -> {
            root.getChildren().addAll(new Actor(event.getX(), event.getY()));
        });
        Scene scene = new Scene(root, 1366, 768);
        scene.setOnMouseClicked(event -> {
            root.getChildren().addAll(new Actor(event.getX(), event.getY()));
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class Sun extends Circle {
        private class Delta {
            double x;
            double y;
        }
        Delta d;
        public Sun(double x, double y) {
            relocate(x, y);
            setRadius(200);
            setFill(Color.YELLOW);
            this.d = new Delta();
            final Effect glow = new Glow(3.0);
            setEffect(glow);
            Bloom bloom = new Bloom();
            bloom.setThreshold(1.0);
            setEffect(bloom);
            handle();
        }
        /**
        public void makeShadow(GraphicsContext gc, Bounds b) {
            gc.save();
            gc.translate(SHADOW_GAP, SHADOW_GAP);
            gc.setFill(SHADOW_COLOR);
            double sx = (b.getMinX()-sun.getX())*distRatio + b.getMinX();
            double sy = (b.getMinY()-sun.getY())*distRatio + b.getMinY();
            double sw = b.getWidth() * (1 + distRatio);
            double sh = b.getHeight() * (1 + distRatio);
            gc.fillRect(sx,sy,sw,sh);
            gc.restore();
        }
         */

        private void handle() {
            setOnMousePressed(event -> {
                d.x = event.getX();
                d.y = event.getY();
            });
            setOnMouseDragged(event -> {
                setLayoutX(getLayoutX() + event.getX() - d.x);
                setLayoutY(getLayoutY() + event.getY() - d.y);
            });
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
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
    class DragBox extends StackPane {
        private TextArea t = new TextArea("hi");
        public DragBox() {
            getChildren().addAll(t);
        }

        public void enableDrag() {
            final Delta dragDelta = new Delta();
            t.setOnMouseEntered(event -> {
                getScene().setCursor(Cursor.CROSSHAIR);
            });
            t.setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.toFront();
                    dragDelta.x = event.getX();
                    dragDelta.y = event.getY();
                }
            });
            t.setOnMouseDragged(event -> {
                double newX = getLayoutX() + event.getX() - dragDelta.x;
                setLayoutX(newX);
                double newY = getLayoutY() + event.getY() - dragDelta.y;
                setLayoutY(newY);
            });
        }
        private class Delta {
            double x, y;

        }
    }
}


