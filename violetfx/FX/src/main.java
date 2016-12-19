import com.sun.org.apache.bcel.internal.generic.POP;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Created by Khiem on 12/18/2016.
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
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        sc = new Scene(root, 800, 600, Color.ANTIQUEWHITE);
        stage.setScene(sc);
        stage.show();
        Text text = new Text("This is a test");
        text.setX(10);
        text.setY(50);
        text.setFont(new Font(20));

        text.getTransforms().add(new Rotate(60, 60, 60));
        System.out.println(text.getBoundsInParent().toString());
        System.out.println(text.getBoundsInLocal().toString());
        System.out.println(text.getLayoutBounds().toString());
        System.out.println(text.getLayoutX());
        System.out.println(text.getLayoutY());
        System.out.println(text.getX());
        System.out.println(text.getY());
        root.getChildren().addAll(text);
        sceneHandle();
    }
    private void sceneHandle() {
        sc.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                root.getChildren().add(new Node(event.getX(), event.getY()));
                System.out.println("clicked");
                System.out.println(root.getChildren().size());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    private Pane root;
    private Scene sc;
    private PointLight pointLight = new PointLight(Color.RED){
        {
            localToScene(200, 200);
        }
    };
}
