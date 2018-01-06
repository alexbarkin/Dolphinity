/**
 *Collideable abstract class.  This is an abstract class that represents an object that can collide with the dolphin or
 * the net
 * 
 * @author Jordan Levy
 * @version 5.0.0 06.09.2015
 * 
 *  Updated Time Spent as of May 29, 2015: 1.5 Hours
 * - made collision detection
 */
public abstract class Collideable
{
  /**hitBoxX - double x position of the hitbox*/
  double hitBoxX;
  /**hitBoxY - double y position of the hitbox*/
  double hitBoxY;
  /**hitBoxRadiusX - double x radius of the hitbox*/
  double hitBoxRadiusX;
  /**hitBoxRadiusY - double y radius of the hitbox*/
  double hitBoxRadiusY;
  
  /**
   * This method returns 0 if there is no collision, 1 if there is collision with the dolphin, and 2 if there is
   * collision with the net.
   * @return int to represent what has collided and if it has
   */
  public abstract int getCollisionStatus();
}
