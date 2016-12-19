package violetFX;

import javafx.beans.binding.Bindings;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

/**
 * Created by Khiem on 11/20/2016.
 */
public class StraightEdge extends Line {


    public StraightEdge(ClassNode start, ClassNode end, boolean dashed) {
            startXProperty().bind(Bindings.createObjectBinding(() -> getConnectionPoints(start.getBoundsInLocal(), end.getBoundsInLocal())[0].getX(), start.boundsInLocalProperty(), end.boundsInLocalProperty()));
            startYProperty().bind(Bindings.createObjectBinding(() -> getConnectionPoints(start.getBoundsInLocal(), end.getBoundsInLocal())[0].getY(), start.boundsInLocalProperty(), end.boundsInLocalProperty()));
            endXProperty().bind(Bindings.createObjectBinding(() -> getConnectionPoints(start.getBoundsInLocal(), end.getBoundsInLocal())[1].getX(), start.boundsInLocalProperty(), end.boundsInLocalProperty()));
            endYProperty().bind(Bindings.createObjectBinding(() -> getConnectionPoints(start.getBoundsInLocal(), end.getBoundsInLocal())[1].getY(), start.boundsInLocalProperty(), end.boundsInLocalProperty()));
        if (dashed) {
            getStrokeDashArray().addAll(25d, 10d);
        }
    }

    public Point2D getConnectionPoint(BoundingBox bounds, Direction d)
    {
        double slope = bounds.getHeight() / bounds.getWidth();
        double ex = d.getX();
        double ey = d.getY();
        double x = (bounds.getMinX() + bounds.getMaxX()) / 2;
        double y = (bounds.getMinY() + bounds.getMaxY()) / 2;

        if (ex != 0 && -slope <= ey / ex && ey / ex <= slope)
        {
            // intersects at left or right boundary
            if (ex > 0)
            {
                x = bounds.getMaxX();
                y += (bounds.getWidth() / 2) * ey / ex;
            } else
            {
                x = bounds.getMinX();
                y -= (bounds.getWidth() / 2) * ey / ex;
            }
        } else if (ey != 0)
        {
            // intersects at top or bottom
            if (ey > 0)
            {
                x += (bounds.getHeight() / 2) * ex / ey;
                y = bounds.getMaxY();
            } else
            {
                x -= (bounds.getHeight() / 2) * ex / ey;
                y = bounds.getMinY();
            }
        }
        return new Point2D(x, y);
    }

    public Point2D[] getConnectionPoints(Bounds startBound, Bounds endBound)
    {
        BoundingBox start = (BoundingBox) startBound;
        BoundingBox end = (BoundingBox) endBound;
        Point2D[] points = new Point2D[2];
        Point2D startCenter = new Point2D(
                (start.getMinX()+start.getMaxX())/2, (start.getMinY()+start.getMaxY())/2);
        Point2D endCenter = new Point2D(
                (end.getMinX()+ end.getMaxX())/2, (end.getMinY()+end.getMaxY())/2);
        Direction toEnd = new Direction(startCenter, endCenter);
        Point2D startPoint = getConnectionPoint(start, toEnd);
        Point2D endPoint = getConnectionPoint(end, toEnd.turn(180));
        points[0] = startPoint;
        points[1] = endPoint;
        return points;
    }


}
