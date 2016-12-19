package violetFX;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

/**
 * Created by Khiem on 11/19/2016.
 */
public class Pop extends Popup {
    public Pop() {
        if (this == CLASSNODE) {
            TextField name = new TextField("name");
            TextArea attributes = new TextArea("atributes");
            TextArea methods = new TextArea("methods");
            VBox vLayout = new VBox();
            Button ok = new Button("OK");
            ok.setOnAction(event -> {
                hide();
            });
            vLayout.getChildren().addAll(name, attributes, methods, ok);
            getContent().add(vLayout);
        }
    }

    public static final Pop CLASSNODE = new Pop();

    public static void main(String[] args) {
    }
}
