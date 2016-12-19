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

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
   This class defines line styles of various shapes.
*/
public class LineStyle
{
   private LineStyle() {}

   /**
      Draw this line
      @param gc GraphicsContext
      @param a start point
      @param b end point
   */   
   public void draw(GraphicsContext gc, Point2D a, Point2D b) {
      gc.save();
      gc.setStroke(Color.BLACK);
      gc.setLineWidth(0.5);
      if (this == DOTTED)
         gc.setLineDashes(5);
      gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
      gc.restore();
   }
   
   public static final LineStyle DOTTED = new LineStyle();
   public static final LineStyle SOLID = new LineStyle();
}
