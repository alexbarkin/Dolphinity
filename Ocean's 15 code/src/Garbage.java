import java.awt.*;

/**
 *Garbage class.  Represents a garbage object in the game.
 * 
 * @author Jordan Levy 
 * @version 5.0.0 06.09.2015
 * 
 * Jordan - Updated Time Spent as of May 29, 2015: 2.5 Hours
 * - setting up class 
 * - main issue was collision detection
 */
public class Garbage extends Collideable
{
  /**image - Image used to store the image of the garbage*/
  Image image;
  /**x - double used to store the x value of the garbage's location*/
  double x;
  /**y - double used to store the y value of the garbage's location*/
  double y;
  /**speed - double used to store the speed of the background so the garbage move accordingly*/
  double speed;
  /**collided - boolean used to store whether or not the net has collided with the Garbage hitbox*/
  boolean collided = false;
  /**temp2 - int used to keep the garbage for a certain amount of time after it is collided*/
  int temp2 = 0;
  
  /**
   * Constructor used to create a garbage object when wanted and sets up the hitBoxes
   */
  public Garbage ()
  {
    x = Dolphinity.WIDTH  - 30;
    y = 300 + Math.random() * 270;
    image = Dolphinity.garbageImages[(int)(Math.random() * 7)];
    hitBoxX = x;
    hitBoxY = y;
    hitBoxRadiusX = image.getWidth(null) / 2;
    hitBoxRadiusY = image.getHeight(null) / 2;
  }
  
  /**
   * Method used to move the garbage with the background 
   */
  public void move()
  {
    x -= Dolphinity.dolphin.velocityX;
    hitBoxX = x + hitBoxRadiusX;
    hitBoxY = y + hitBoxRadiusY;
  }
  
  /**
   * method used to return whether or not something has collided  with the garbage and what
   * <p>
   * <b>Local variables:</b>
   * <p>
   * <b>t -</b> double df angle between garbage and dolphin
   * <p>
   * <b>px -</b> double point x on garbage circumference that intersects with line from garbage to dolphin
   * <p>
   * <b>py -</b> double point y on garbage circumference that intersects with line from garbage to dolphin
   * <p>
   * <b>rp -</b> double radius from garbage center to point on circumference
   * <p>
   * <b>d -</b> double relative point, not absolute for pfx and pfy
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to only check collision if it hasnt already collided
   * <p>
   * <b> 2nd if structure</b> used to return 1 if the dolphin collides with the garbage
   * <p>
   * <b> 3rd if structure</b> used to check for net collision with garbage only if its out
   * <p>
   * <b> 4th if structure</b> used to return 2 if the net collides with garbage
   * 
   * @return int to represent what has collided and if it has
   */
  public int getCollisionStatus()
  {
    if (!collided)
    {
      double t = Math.atan2(Dolphinity.dolphin.hitBoxX - hitBoxX, Dolphinity.dolphin.hitBoxY - hitBoxY);
      double px = hitBoxX + hitBoxRadiusX * Math.cos(t);
      double py = hitBoxY + hitBoxRadiusY * Math.sin(t);
      double rp = Math.sqrt(Math.pow(px - hitBoxX,2) + Math.pow(py - hitBoxY ,2));
      double d = Math.sqrt(Math.pow(hitBoxX - Dolphinity.dolphin.hitBoxX ,2) + Math.pow(hitBoxY - Dolphinity.dolphin.hitBoxY ,2));
      
      if(d <= rp + Dolphinity.dolphin.hitBoxRadius)
      {
        //collision between fish and dolphin 
        return 1;
      }
      
      if(GamePanel.key_w)
      {
        t = Math.atan2(hitBoxX - Dolphinity.dolphin.net.hitBoxX, hitBoxY - Dolphinity.dolphin.net.hitBoxY);
        px = hitBoxX + hitBoxRadiusX * Math.cos(t);
        py = hitBoxY + hitBoxRadiusY * Math.sin(t);
        rp = Math.sqrt(Math.pow(hitBoxX - px ,2) + Math.pow(hitBoxY - py ,2));
        d = Math.sqrt(Math.pow(hitBoxX - Dolphinity.dolphin.net.hitBoxX ,2) + Math.pow(hitBoxY - Dolphinity.dolphin.net.hitBoxY ,2));
        
        if(d <= rp + Dolphinity.dolphin.net.hitBoxRadius)
        {
          //collision between fish and net
          return 2;
        }
      }
    }
    return 0;
  }
}