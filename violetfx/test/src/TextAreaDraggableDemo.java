import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TextAreaDraggableDemo extends Application {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextArea textarea = new TextArea();
        DragText t = new DragText();
        t.setLayoutX(100);
        t.setLayoutY(100);
        Group group = new Group();
        group.getChildren().addAll(textarea, t);

        Scene scene = new Scene(group, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        Node textAreaContent = textarea.lookup(".content");
        textAreaContent.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

            System.out.println("is clicked");

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = textarea.getTranslateX();
            orgTranslateY = textarea.getTranslateY();

            textarea.toFront();
        });

        textAreaContent.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {

            System.out.println("is dragged");

            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            textarea.setTranslateX(newTranslateX);
            textarea.setTranslateY(newTranslateY);
        });

    }
}

class DragText extends TextArea {
    private Node content;
    private double orgSceneX, orgSceneY,orgTranslateX, orgTranslateY;
    public DragText() {
        super();
        content = lookup(".content");
    }

    public void config() {
        content.setOnMousePressed(e -> {
            System.out.println("is clicked");

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = getTranslateX();
            orgTranslateY = getTranslateY();

            toFront();
        });
        content.setOnMouseDragged(e -> {
            System.out.println("is dragged");

            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            setTranslateX(newTranslateX);
            setTranslateY(newTranslateY);
        });
    }
}
