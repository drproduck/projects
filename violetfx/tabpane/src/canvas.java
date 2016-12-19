import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.G;


/**
 * Created by Khiem on 11/26/2016.
 */
public class canvas extends Application
{

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
        Scene scene = new Scene(root, 800, 600);
        Canvas c = new Canvas(200, 200);
        GraphicsContext gc=  c.getGraphicsContext2D();
        gc.setLineDashes(5);
        gc.strokeLine(100,100,200,200);

        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(0.0f);
        moveTo.setY(0.0f);

        HLineTo hLineTo = new HLineTo();
        hLineTo.setX(70.0f);

        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setX(120.0f);
        quadCurveTo.setY(60.0f);
        quadCurveTo.setControlX(100.0f);
        quadCurveTo.setControlY(0.0f);

        LineTo lineTo = new LineTo();
        lineTo.setX(175.0f);
        lineTo.setY(55.0f);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(50.0f);
        arcTo.setY(50.0f);
        arcTo.setRadiusX(50.0f);
        arcTo.setRadiusY(50.0f);


        path.getElements().add(moveTo);
        path.getElements().add(hLineTo);
        path.getElements().add(quadCurveTo);
        path.getElements().add(lineTo);
        path.getElements().add(arcTo);
        path.setFill(Color.AZURE);

        Path p1 = new Path();
        p1.getElements().addAll(new MoveTo(0, 0), new LineTo(60 - 8, 0), new LineTo(60, 0 + 8), new LineTo(60, 40), new LineTo(0, 40), new LineTo(0,0));
        p1.setFillRule(FillRule.EVEN_ODD);
        p1.setFill(Color.YELLOW);
        Path p2 = new Path();
        p2.getElements().addAll(new MoveTo(60-8, 0), new LineTo(60-8, 0+8), new LineTo(60, 0+8), new LineTo(60-8, 0));
        p2.setFill(Color.WHITE);
        Shape p = Shape.union(p1, p2);
        Path p3 = new Path();
        p3.getElements().addAll(new MoveTo(0, 0), new LineTo(60 - 8, 0), new LineTo(60, 0 + 8), new LineTo(60, 40), new LineTo(0, 40), new LineTo(0,0));
        p3.relocate(100, 100);
        p3.setFillRule(FillRule.EVEN_ODD);
        p3.setStroke(Color.YELLOW);
        p3.setFill(Color.AZURE);
        root.getChildren().addAll(c, p, p3);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
