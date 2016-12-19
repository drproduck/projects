import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Created by Khiem on 11/19/2016.
 */
public class main3 extends Application {

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
        Group root = new Group();
        Scene sc = new Scene(root, 800, 600);
        Line line = new Line(0,0,0,0);
        sc.setOnMousePressed(event -> {
            line.setStartY(event.getY());
            line.setStartX(event.getX());
        });
        sc.setOnMouseDragged(event -> {
            line.setEndY(event.getY());
            line.setEndX(event.getX());
        });
        sc.setOnMouseReleased(event -> {
            line.setEndX(line.getStartX());
            line.setEndY(line.getStartY());
        });
        root.getChildren().addAll(line);

        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
