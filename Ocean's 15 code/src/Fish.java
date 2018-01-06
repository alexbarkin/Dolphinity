import java.awt.*;

/**
 *Fish class. Represents a fish object in the game
 * 
 * @author A Barkin edited by J Levy
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of May 29, 2015: 1.5 Hours
 * - wrote original shell and made randomly appear
 * 
 * Jordan - Updated Time Spent as of June 9, 2015: 2.5 Hours 
 * - Jordan took over collision detection
 */
public class Fish extends Collideable
{
  /*image - Image used to store the image of the fish*/
  Image image;
  /*isEndangered - boolean to differentiate whether it is endangered or not*/
  boolean isEndangered= false;
  /*x - double used to store the x value of the fish's location*/
  double x;
  /*y - double used to store the y value of the fish's location*/
  double y;
  /*speed - double used to store the speed of the background so the fish move accordingly*/
  double speed;
  /*collided - boolean used to store whether or not the dolphin has collided with the fish hitbox*/
  boolean collided = false;
  /*temp - int used to keep the hoop for a certain amount of time after it is collided*/
  int temp = 0;
  
  /**
   * Constructor used to create a Fish object when wanted uses a waited average to decide the type of fish
   * 
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> sets the number of fish depending on the level selected
   *
   */
  public Fish ()
  {
    x = Dolphinity.WIDTH  - 30;
    y = 300 + Math.random() * 270;
    isEndangered = ((int)(Math.random() * 100) <= 10)?true:false;
    int levelNum = 0;
    if(Dolphinity.score.getLevel().equals("easy"))
      levelNum = 1;
    else if(Dolphinity.score.getLevel().equals("medium"))
      levelNum = 3;
    else
      levelNum = 6;
    image = (isEndangered)?Dolphinity.endangeredFishImages[(int)(Math.random() * levelNum)]:
      Dolphinity.fishImages[(int)(Math.random() * 5)];
    speed = 1;
    hitBoxX = x;
    hitBoxY = y;
    hitBoxRadiusX = image.getWidth(null) / 2;
    hitBoxRadiusY = image.getHeight(null) / 2;
  }
  
  
  /**
   * Method used to move the hoops with the background 
   */
  public void move()
  {
    x-= Dolphinity.dolphin.velocityX;
    hitBoxX = x + hitBoxRadiusX;
    hitBoxY = y + hitBoxRadiusY;
  }
  
  /**
   * method used to return whether or not something has collided  with the fish and what
   * <p>
   * <b>Local variables:</b>
   * <p>
   * <b>t -</b> double df angle between fish and dolphin
   * <p>
   * <b>px -</b> double point x on fish circumference that intersects with line from fish to dolphin
   * <p>
   * <b>py -</b> double point y on fish circumference that intersects with line from fish to dolphin
   * <p>
   * <b>rp -</b> double radius from fish center to point on circumference
   * <p>
   * <b>d -</b> double relative point, not absolute for pfx and pfy
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to only check collision if it hasnt already collided
   * <p>
   * <b> 2nd if structure</b> used to return 1 if the dolphin collides with a fish
   * <p>
   * <b> 3rd if structure</b> used to check for net collision with fish only if its out
   * <p>
   * <b> 4th if structure</b> used to return 2 if the net collides with a fish
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