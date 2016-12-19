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

import java.io.Serializable;


import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
   A string that can extend over multiple lines.
*/
@SuppressWarnings("serial")
public class MultiLineString implements Serializable
{
   /**
      Constructs an empty, centered, normal size multiline
      string that is not underlined.
   */
   public MultiLineString() 
   { 
      text = new Text("");
      size = 12;
      underlined = false;    
   }
   /**
      Sets the value of the text property.
      @param newValue the text of the multiline string
   */
   public void setText(String newValue) { 
      text.setText(newValue); 
   }
   /**
      Gets the value of the text property.
      @return the text of the multiline string
   */
   public String getText() { return text.getText(); }
   /**
      Gets the value of the underlined property.
      @return true if the text is underlined
   */
   public boolean isUnderlined() { return underlined; }
   
   /**
      Sets the value of the underlined property.
      @param newValue true to underline the text
   */
   public void setUnderlined(boolean newValue) { underlined = newValue;}
   
   /**
      Sets the value of the size property.
      @param newValue the size, one of SMALL, NORMAL, LARGE
   */
   public void setSize(int newValue) { size = newValue;}
   
   /**
      Gets the value of the size property.
      @return the size, one of SMALL, NORMAL, LARGE
   */
   public double getSize() { return size; }
   
   /**
    * This method return a string contains this text
    * @return a string of this text
    */
   public String toString()
   {
      return text.getText().replace('\n', '|');
   }
   
   /**
      Gets the bounding rectangle for this multiline string.
      @return the bounding box at (0,0)
   */
   public Bounds getBounds()
   {    
      if (text == null || text.getText().isEmpty()) return new BoundingBox(0,0,0,0);
      return text.getBoundsInLocal(); 
   }

   /**
      Draws this multiline string inside a given rectangle
      @param gc the graphics context
      @param x x coordinate
      @param y y coordinate
   */
   public void draw(GraphicsContext gc, double x, double y)
   {
      gc.save();
      gc.setTextBaseline(VPos.CENTER);
      gc.setTextAlign(TextAlignment.CENTER);
      gc.setFill(Color.BLACK);
      gc.fillText(text.getText(), x, y);
      gc.restore();
   }

/*   public Object clone()
   {
      try
      {
         MultiLineString cloned = (MultiLineString) super.clone();
         cloned.label = new JLabel();
         cloned.setLabelText();
         return cloned;
      }
      catch (CloneNotSupportedException exception)
      {
         return null;
      }
   }
*/
   public static final int LEFT = 0;
   public static final int CENTER = 1;
   public static final int RIGHT = 2;

   private Text text;
   private double size;
   private boolean underlined;
}
