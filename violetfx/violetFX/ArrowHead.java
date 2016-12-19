/*
Violet - A program for editing UML diagrams.

Copyright (C) 2002 Cay S. Horstmann (http://horstmann.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package violetFX;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.geometry.Point2D;

/**
 * Arrowhead class
 * 
 * @author Shariq Shah
 *
 */
public class ArrowHead
{
   /**
    * Creates an ArrowHead 
    */
   public ArrowHead()
   {
   }

   /**
    * Gets the path of the arrowhead
    * 
    * @param gc GraphicsContext
    * 
    * @param p
    *           a point on the axis of the arrow head
    * @param q
    *           the end point of the arrow head
    */
   public void draw(GraphicsContext gc, Point2D p, Point2D q)
   {
      gc.save();
      gc.setStroke(Color.BLACK);
      gc.setFill(Color.BLACK);
      if (this == NONE)
         return;
      final double ARROW_ANGLE = Math.PI / 6;
      final double ARROW_LENGTH = 10;

      double dx = q.getX() - p.getX();
      double dy = q.getY() - p.getY();
      double angle = Math.atan2(dy, dx);
      double x1 = q.getX() - ARROW_LENGTH * Math.cos(angle + ARROW_ANGLE);
      double y1 = q.getY() - ARROW_LENGTH * Math.sin(angle + ARROW_ANGLE);
      double x2 = q.getX() - ARROW_LENGTH * Math.cos(angle - ARROW_ANGLE);
      double y2 = q.getY() - ARROW_LENGTH * Math.sin(angle - ARROW_ANGLE);
      
      gc.beginPath();
      gc.moveTo((float)q.getX(), (float)q.getY());
      gc.lineTo((float)x1, (float)y1);
      if (this == V)
      {
         gc.moveTo((float)x2, (float)y2);
         gc.lineTo((float)q.getX(), (float)q.getY());
      }
      else if (this == TRIANGLE || this == BLACK_TRIANGLE)
      {
         gc.lineTo((float)x2, (float)y2);
         gc.closePath();
         if (this == BLACK_TRIANGLE) gc.setFill(Color.BLACK);
         else gc.setFill(Color.WHITE);
         gc.fill();
      }
      else if (this == DIAMOND || this == BLACK_DIAMOND)
      {
         double x3 = x2 - ARROW_LENGTH * Math.cos(angle + ARROW_ANGLE);
         double y3 = y2 - ARROW_LENGTH * Math.sin(angle + ARROW_ANGLE);
         gc.lineTo((float)x3, (float)y3);
         gc.lineTo((float)x2, (float)y2);
         gc.closePath();
         if (this == BLACK_DIAMOND) gc.setFill(Color.BLACK);
         else gc.setFill(Color.WHITE);
         gc.fill();
      }      
      gc.stroke();
      gc.restore();
   }

   public static final ArrowHead NONE = new ArrowHead();
   public static final ArrowHead TRIANGLE = new ArrowHead();
   public static final ArrowHead BLACK_TRIANGLE = new ArrowHead();
   public static final ArrowHead V = new ArrowHead();
   public static final ArrowHead HALF_V = new ArrowHead();
   public static final ArrowHead DIAMOND = new ArrowHead();
   public static final ArrowHead BLACK_DIAMOND = new ArrowHead();

}
