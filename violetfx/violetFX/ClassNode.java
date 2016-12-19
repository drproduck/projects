package violetFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Khiem on 11/17/2016.
 */
public class ClassNode extends VBox {
    private DragDelta delta;
    private Label name = new Label("name");
    private Label attributes = new Label("attributes");
    private Label methods = new Label("methods");
    public SimpleBooleanProperty draggable = new SimpleBooleanProperty(false);
    public SimpleBooleanProperty popped = new SimpleBooleanProperty(false);
    public Pop pop;


    public boolean isDraggable() {
        return draggable.get();
    }

    public SimpleBooleanProperty draggableProperty() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable.set(draggable);
    }

    public boolean isPopped() {
        return popped.get();
    }

    public SimpleBooleanProperty poppedProperty() {
        return popped;
    }

    public void setPopped(boolean popped) {
        this.popped.set(popped);
    }

    private final EventHandler pressed = new EventHandler() {
        @Override
        public void handle(Event event) {
            MouseEvent e = (MouseEvent) event;
            if (e.getButton() == MouseButton.PRIMARY) {
                if (draggable.get()) {
                    delta.x = e.getX();
                    delta.y = e.getY();
                    event.consume();
                }
            } else if (e.getButton() == MouseButton.SECONDARY) {
                setPopped(true);
            }
        }
    };

    EventHandler dragged = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (draggable.get()) {
                MouseEvent e = (MouseEvent) event;
                double newX = getLayoutX() + e.getX() - delta.x;
                double newY = getLayoutY() + e.getY() - delta.y;
                if (newX > 0 && newX < getScene().getWidth()) {
                    setLayoutX(newX);
                }
                if (newY > 0 && newY < getScene().getHeight()) {
                    setLayoutY(newY);
                }
                event.consume();
            }
        }
    };

    public ClassNode(double x, double y) {
        relocate(x, y);
        effect();

        setPrefWidth(90);
        setPrefHeight(60);
        name.setPrefWidth(30);
        name.setPrefHeight(20);
        attributes.setPrefWidth(30);
        attributes.setPrefHeight(20);
        methods.setPrefWidth(30);
        methods.setPrefHeight(20);


        getChildren().addAll(name, attributes, methods);
        prefHeightProperty().bind(name.heightProperty().add(attributes.heightProperty()).add(methods.heightProperty()));
        setPadding(new Insets(0, 0, 0, 0));
        delta = new DragDelta();

        name.addEventFilter(MouseEvent.MOUSE_PRESSED, pressed);
        name.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragged);
        attributes.addEventFilter(MouseEvent.MOUSE_PRESSED, pressed);
        attributes.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragged);
        methods.addEventFilter(MouseEvent.MOUSE_PRESSED, pressed);
        methods.addEventFilter(MouseEvent.MOUSE_DRAGGED, dragged);

        getStyleClass().add("text-input");
    }

    private void effect() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        setEffect(dropShadow);
    }

}


