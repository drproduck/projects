/// ## CAUTION: beware the com.sun imports...
import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Displays a map of the lonely mountain upon which draggable, editable labels can be overlaid.
 */
public class TextCreator extends Application {
    private static final String MAP_IMAGE_LOC =
            "http://images.wikia.com/lotr/images/archive/f/f6/20130209175313!F27c_thorins_map_from_the_hobbit.jpg";

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        Pane pane = new Pane();

        pane.setOnMouseClicked(event -> {
            if (event.getTarget() == pane) {
                pane.getChildren().add(
                        new EditableDraggableText(event.getX(), event.getY())
                );
            }
        });

        EditableDraggableText cssStyled =
                new EditableDraggableText(439, 253, "Style them with CSS");
        cssStyled.getStyleClass().add("highlighted");

        pane.getChildren().addAll(
                new EditableDraggableText(330, 101, "Click to add a label"),
                new EditableDraggableText(318, 225, "You can edit your labels"),
                cssStyled,
                new EditableDraggableText(336, 307, "And drag them"),
                new EditableDraggableText(309, 346, "Around The Lonely Mountain")
        );

        StackPane layout = new StackPane(
                new ImageView(
                        new Image(
                                MAP_IMAGE_LOC
                        )
                ),
                pane
        );

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource(
                "editable-text.css"
        ).toExternalForm());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * A text field which has no special decorations like background, border or focus ring.
     *   i.e. the EditableText just looks like a vanilla Text node or a Label node.
     */
    class EditableText extends TextField {
        // The right margin allows a little bit of space
        // to the right of the text for the editor caret.
        private final double RIGHT_MARGIN = 5;

        EditableText(double x, double y) {
            relocate(x, y);
            getStyleClass().add("editable-text");

            //** CAUTION: this uses a non-public API (FontMetrics) to calculate the field size
            //            the non-public API may be removed in a future JavaFX version.
            // see: https://javafx-jira.kenai.com/browse/RT-8060
            //      Need font/text measurement API
            FontMetrics metrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(getFont());
            setPrefWidth(RIGHT_MARGIN);
            textProperty().addListener((observable, oldTextString, newTextString) ->
                    setPrefWidth(metrics.computeStringWidth(newTextString) + RIGHT_MARGIN)
            );

            Platform.runLater(this::requestFocus);
        }
    }

    /**
     * An EditableText (a text field which looks like a label), which can be dragged around
     * the screen to reposition it.
     */
    class EditableDraggableText extends StackPane {
        private final double PADDING = 5;
        private EditableText text = new EditableText(PADDING, PADDING);

        EditableDraggableText(double x, double y) {
            relocate(x - PADDING, y - PADDING);
            getChildren().add(text);
            getStyleClass().add("editable-draggable-text");

            // if the text is empty when we lose focus,
            // the node has no purpose anymore
            // just remove it from the scene.
            text.focusedProperty().addListener((observable, hadFocus, hasFocus) -> {
                if (!hasFocus && getParent() != null && getParent() instanceof Pane &&
                        (text.getText() == null || text.getText().trim().isEmpty())) {
                    ((Pane) getParent()).getChildren().remove(this);
                }
            });

            enableDrag();
        }

        public EditableDraggableText(int x, int y, String text) {
            this(x, y);
            this.text.setText(text);
        }

        // make a node movable by dragging it around with the mouse.
        private void enableDrag() {
            final Delta dragDelta = new Delta();
            setOnMousePressed(mouseEvent -> {
                this.toFront();
                // record a delta distance for the drag and drop operation.
                dragDelta.x = mouseEvent.getX();
                dragDelta.y = mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
            });
            setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));
            setOnMouseDragged(mouseEvent -> {
                double newX = getLayoutX() + mouseEvent.getX() - dragDelta.x;
                if (newX > 0 && newX < getScene().getWidth()) {
                    setLayoutX(newX);
                }
                double newY = getLayoutY() + mouseEvent.getY() - dragDelta.y;
                if (newY > 0 && newY < getScene().getHeight()) {
                    setLayoutY(newY);
                }
            });
            setOnMouseEntered(mouseEvent -> {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseExited(mouseEvent -> {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            });
        }

        // records relative x and y co-ordinates.
        private class Delta {
            double x, y;
        }
    }
}
