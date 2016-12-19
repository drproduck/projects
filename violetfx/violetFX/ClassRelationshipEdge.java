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

/**
   An helper class that combines arrow head with line to form an edge
*/
public class ClassRelationshipEdge
{
   /**
      Constructs a straight edge.
   */
   private ClassRelationshipEdge(ArrowHead a, LineStyle l)
   {      
      line = l;
      arrowHead = a;
   }

   /**
    * Draw this edge
    * @param gc GraphicsContext  
    * @param a start point
    * @param b end point
    */
   public void draw(GraphicsContext gc, Point2D a, Point2D b) {      
      line.draw(gc, a, b);
      arrowHead.draw(gc, a, b);
   }
   
   public static final ClassRelationshipEdge DEPENDENCY = 
         new ClassRelationshipEdge(ArrowHead.V, LineStyle.SOLID);
   
   public static final ClassRelationshipEdge INHERITANCE = 
         new ClassRelationshipEdge(ArrowHead.TRIANGLE, LineStyle.SOLID);
   
   public static final ClassRelationshipEdge AGGREGATION = 
         new ClassRelationshipEdge(ArrowHead.DIAMOND, LineStyle.SOLID);
   
   public static final ClassRelationshipEdge COMPOSITION = 
         new ClassRelationshipEdge(ArrowHead.BLACK_DIAMOND, LineStyle.SOLID);
   
   public static final ClassRelationshipEdge ASSOCIATION = 
         new ClassRelationshipEdge(ArrowHead.V, LineStyle.SOLID);
   
   public static final ClassRelationshipEdge IMPLEMENTATION = 
         new ClassRelationshipEdge(ArrowHead.TRIANGLE, LineStyle.DOTTED);
     
   private ArrowHead arrowHead;
   private LineStyle line;
}
