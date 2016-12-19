package violetFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Main display
 *
 * @author Ly, Khiem
 */
public class MainStage extends Application {

    private Scene scene;
    private BorderPane root;
    private Stage stage;
    private TabPane tabs;
    private int numTabs;

    /**
     * start program
     * @param stage primary stage
     */
    public void start(Stage stage) throws Exception {
        root = new BorderPane();
        scene = new Scene(root, 1600, 900);
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());
        stage.setTitle("VioletFX");
        tabs = new TabPane();
        tabs.prefWidthProperty().bind(scene.widthProperty());
        root.setCenter(tabs);
        stage.setScene(scene);
        stage.show();
        setMenuBar();
        this.stage = stage;
    }

    /**
     * main method
     * @param args arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * helper method to set up menu bar
     */
    private void setMenuBar() {
        MenuBar menu = new MenuBar();
        // -------------File menu
        Menu file = new Menu("File");

        // ---exit
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(t -> System.exit(0));
        // ---save
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        // ...

        // ---new file
        Menu newDiagram = new Menu("New");
        MenuItem classDiagram = new MenuItem("Class Diagram");
        classDiagram.setOnAction(t -> {
            ClassDiagram cd = new ClassDiagram(scene);
            newTab(cd);
        });

        // ...
        MenuItem sequenceDiagram = new MenuItem("Sequence Diagram");
        // ...
        newDiagram.getItems().addAll(classDiagram, sequenceDiagram);

        // ---open file
        MenuItem open = new MenuItem("Open");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new FileChooser().showOpenDialog(stage);
            }
        });

        file.getItems().addAll(newDiagram, open, save, exit);

        menu.getMenus().addAll(file);
        root.setTop(menu);
    }

    private void newTab(ClassDiagram d) {
        Tab tab = new Tab(d.getTypeName() + " " + numTabs);
        tab.setContent(d);
        tab.setOnClosed(e -> {
            tabs.getTabs().removeAll(tab);
            numTabs--;
        });

        tabs.getTabs().add(tab);

        numTabs++;

    }
}
