import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

/**
 *Dolphin class. It represents the dolphin in the game
 * 
 * @author Jordan Levy
 * @version 5.0.0 06.09.2015
 * 
 * Updated Time Spent as of June 9, 2015: 5.5 Hours
 * -playing with physics of dolphin and edge detection
 */
public class Dolphin
{
  /**x - double x position*/
  double x;
  /** y - double y position*/
  double y;
  /**angle - double angle of the dolphin*/
  double angle;
  /**velocityX - double velocity of the dolphin horizontally*/
  double velocityX;
  /**velocityY - double velocity of the dolphin vertically*/
  double velocityY;
  /**accelerationY - double vertical acceleration of the dolphin*/
  double accelerationY;
  /**distanceX - double distance the dolphin has travelled horizontally*/
  double distanceX;
  /**deltaT - double change in time between each frame*/
  double deltaT;
  /**offsetX - double offset from the origin of where to draw the dolphin horizontally*/
  double offsetX;
  /**offsetY - double offset from the origin of where to draw the dolphin vertically*/
  double offsetY;
  /** image - BufferedImage to draw the dolphin picture*/
  BufferedImage image;
  /**rotationTransformation - AffineTransform to rotate the image*/
  AffineTransform rotationTransformation;
  /**hitBoxX - double x position of the hitbox*/
  double hitBoxX;
  /**hitBoxY - double y position of the hitbox*/
  double hitBoxY;
  /**hitBoxRadius - double radius of the hitbox*/
  double hitBoxRadius;
  /**hitBoxDistanceCenter - double distance from the dolphin's point of rotation to the hitbox*/
  double hitBoxDistanceCenter;
  /**hitBoxAngleOffset - double offset from the dolphin's angle to the hitbox's angle*/
  double hitBoxAngleOffset;
  /**net - Net that the dolphin is holding to catch garbage*/
  Net net;
  
  /**
   * Dolphin constructor creates a Dolphin object and initializes global variables.
   * <p>
   * <b>Local variables:</b>
   * <p>
   * <b> e </b> IOException caught for reading images from files
   * 
   * <p>
   * <b>Try Catch blocks:</b>
   * <p>
   * <b> 1st Try Catch</b> used to catch if the image isn't found so the program does not crash.
   * 
   * @param x double x position of the dolphin
   * @param y double y position of the dolphin
   */
  public Dolphin (double x, double y)
  {
    this.x = x;
    this.y = y;
    angle = 0;
    velocityX = 1;
    velocityY = 0;
    accelerationY = 0;
    distanceX = 0;
    deltaT = 0.00001;
    offsetX = - 50;
    offsetY = -230;
    hitBoxRadius = 30;
    hitBoxDistanceCenter = 46;
    hitBoxAngleOffset = 0.25;
    try
    {
      image = ImageIO.read(new File(new File(System.getProperty("user.dir")).getParentFile().toString() +
                                    System.getProperty("file.separator") + "Ocean's 15 code" + 
                                    System.getProperty("file.separator") + "res" + 
                                    System.getProperty("file.separator") + "dolphin.png"));
    }catch(IOException e)
    {
      JOptionPane.showMessageDialog(null, "Error", "Image could not be found", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    hitBoxX = x + offsetX + (image.getWidth() / 2) + Math.cos(angle + hitBoxAngleOffset) * hitBoxDistanceCenter;
    hitBoxY = y + offsetY + (image.getHeight() / 2) + Math.sin(angle + hitBoxAngleOffset) * hitBoxDistanceCenter;
    net = new Net(this);
    net.hitBoxX = x + offsetX + (net.image.getWidth() / 2) + Math.cos(angle + net.hitBoxAngleOffset) * net.hitBoxDistanceCenter;
    net.hitBoxY = y + offsetY + (net.image.getHeight() / 2) + Math.sin(angle + net.hitBoxAngleOffset) * net.hitBoxDistanceCenter; 
  }
  
  /**
   * Moves the dolphin using physics.  The dolphin's distanceX is increased, and it is given an accelerationY based on
   * its position, as well as the space key.  Its velocityY is set based on accelerationY and deltaT, and friction is
   * applied.  y is set.  angle is set.  fractionWayDown is set.  If the dolphin is below the soft floor, angle is
   * set using fractionWayDown so that collision with the ground is smooth.  If the dolphin is below the floor, y is
   * set to be at floor height, angle is 0, accelerationY is 0, and so is velocityY.  Hitbox positions of the dolphin
   * and the net are set based on the angle.  The images of the dolphin and the net are rotated by angle.
   * <p>
   * <b>Local variables:</b>
   * <p>
   * <b> fractionWayDown </b> double fraction representation of how far below the soft floor the dolphin is.
   * 
   * <p>
   * <b>Conditional Statements:</b>
   * <p>
   * <b> 1st conditional statement</b> used to alter the acceleration to increase so it jumps quicker than it dives.
   * <p>
   * <b> 2nd conditional statement</b> used to alter the acceleration to increase when the user clicks the space bar (manual dive).
   * <p>
   * <b> 3rd conditional statement</b> used to rotate the dolphin when it is near the bottom so it does not hit
   * <p>
   * <b> 1st conditional statement in 3rd conditional statement</b> used to reset the dolphin to the bottom if it is one pixel past 
   *         so it resets to the value of the bottom
   */
  public void move ()
  {
    distanceX += velocityX;
    accelerationY = 0;
    if (y <= Dolphinity.WATER_HEIGHT - 10)
      accelerationY = 110;
    else if (y >= Dolphinity.WATER_HEIGHT + 10)
      accelerationY = -110;
    if (GamePanel.key_space)
      accelerationY = 150; 
    velocityY += accelerationY * deltaT;
    velocityY /= Dolphinity.WATER_FRICTION;
    y += velocityY * deltaT; 
    angle = (-Math.atan2(100 , velocityY) + 1.5);
    double fractionWayDown = ((y - Dolphinity.SOFT_FLOOR) / (Dolphinity.HEIGHT - Dolphinity.SOFT_FLOOR));
    if(y > Dolphinity.SOFT_FLOOR)
    {
      angle = (angle * (1 - fractionWayDown));
      if(y > Dolphinity.HEIGHT)
      {
        y = Dolphinity.HEIGHT;
        angle = 0;
        accelerationY = 0;
        velocityY = 0;
      }
    }
    hitBoxX = x + offsetX + (image.getWidth() / 2) + Math.cos(angle + hitBoxAngleOffset) * hitBoxDistanceCenter;
    hitBoxY = y + offsetY + (image.getHeight() / 2) + Math.sin(angle + hitBoxAngleOffset) * hitBoxDistanceCenter;
    net.hitBoxX = x + offsetX + (net.image.getWidth() / 2) + Math.cos(angle + net.hitBoxAngleOffset) * net.hitBoxDistanceCenter;
    net.hitBoxY = y + offsetY + (net.image.getHeight() / 2) + Math.sin(angle + net.hitBoxAngleOffset) * net.hitBoxDistanceCenter;
    rotationTransformation = AffineTransform.getRotateInstance(angle, image.getWidth() / 2, image.getHeight() / 2);
    net.rotationTransformation = AffineTransform.getRotateInstance(angle, image.getWidth() / 2, image.getHeight() / 2);
  }
}
