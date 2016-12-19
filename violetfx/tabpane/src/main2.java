import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Khiem on 11/17/2016.
 */
public class main2 extends Application {


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
        Scene scene = new Scene(root, 1000, 1000, Color.WHEAT);

        Pane pane = new Pane();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(pane);
        VBox box = new VBox();
        box.setPrefHeight(150);
        box.setPrefWidth(50);
        TextArea name = new TextArea("name");
        TextArea methods = new TextArea("method");
        TextArea attributes = new TextArea("attributes");
        name.prefWidthProperty().bind(box.widthProperty());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ScrollPane scrollPane = (ScrollPane)name.lookup(".scroll-pane");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });
        name.setWrapText(true);
        methods.prefWidthProperty().bind(box.widthProperty());
        attributes.prefWidthProperty().bind(box.widthProperty());
        box.getChildren().addAll(name, methods, attributes);
        Bounds bound = box.getLayoutBounds();
        name.setFont(Font.font ("Verdana", 10));
        methods.setFont(Font.font ("Verdana", 10));
        attributes.setFont(Font.font ("Verdana", 10));
        ScrollFreeTextArea area1 = new ScrollFreeTextArea();
        ScrollFreeTextArea area2 = new ScrollFreeTextArea();
        Rectangle rec = new Rectangle(100, 100);
        rec.setFill(Color.AZURE);
        rec.setOnMouseDragged(event -> {
            rec.setLayoutX(event.getSceneX()-rec.getWidth()/2);
            rec.setLayoutY(event.getSceneY()-rec.getHeight()/2);
        });
        VBox vbox = new VBox();
        vbox.getChildren().addAll(area1, area2);
        vbox.setOnMouseDragged(event -> {
            vbox.setLayoutX(event.getX());
            vbox.setLayoutY(event.getY());
        });
        pane.getChildren().addAll(box, vbox, rec);
        box.setTranslateX(100);
        box.setTranslateY(100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}


