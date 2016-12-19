package violetFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


/**
 * a Node that contains a menu bar and a canvas
 * Created by Khiem on 11/16/2016.
 */
public class ClassDiagram extends StackPane {
    private HBox menu;
    ArrayList<ClassNode> nodes;
    ArrayList<StraightEdge> edges;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    private int mode;
    private static final int GENERAL = 0;
    private static final int ACTOR_NODE = 1;
    private static final int ARROW_NODE = 2;


    /**
     * initialize, create a menu and a canvas, add action listeners
     *
     *
     */
    public ClassDiagram(Scene scene) {
        setPickOnBounds(false);
        ;
        menu = new HBox();
        menu.setPadding(new Insets(5, 5, 5, 5));
        menu.setSpacing(10);
        menu.setMaxHeight(70);
        menu.setAlignment(Pos.CENTER);
        menu.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            menu.setOpacity(1);
        });
        menu.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            menu.setOpacity(0);
        });

        Button addNode = new Button("Node");
        addNode.setPrefWidth(100);
        addNode.setPrefHeight(60);
        Button addEdge = new Button("Edge");
        addEdge.setPrefWidth((100));
        addEdge.setPrefHeight(60);
        menu.getChildren().addAll(addNode, addEdge, getActorButton());

        getChildren().addAll(menu);
        setAlignment(menu, Pos.TOP_CENTER);
        handle(scene);
    }
    private Button getActorButton() {
        Rectangle rect = new Rectangle(40, 30);
        Button b = new Button("actor", rect);
        Tooltip.install(b, new Tooltip("actor"));
        b.setOnAction(event -> {
            setMode(1);
            System.out.println("armed");
        });
        return b;
    }
    public void handle(Scene scene) {
        /**
        if (mode==GENERAL) {
            setOnMouseClicked(event -> {
                    ClassNode node = new ClassNode(event.getX(), event.getY());
                    getChildren().addAll(node);
            });
*/
        if (mode == 1) {
            scene.setOnMousePressed(event -> {
                Actor a = new Actor(event.getX(), event.getY());
                getChildren().add(a);
                System.out.println("fired");
            });
        }
    }
    /**
     * name of tab
     *
     * @return name of tab
     */
    public String getTypeName() {
        return "Class diagram";
    }
}

