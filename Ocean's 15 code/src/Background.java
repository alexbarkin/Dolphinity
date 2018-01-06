import java.awt.*;
import java.io.*;

/**
 *Background class.  It represents the scrolling background of the game.
 * 
 * @author Jordan Levy
 * @version 5.0.0 06.09.2015
 * 
 *  Updated Time Spent as of May 22, 2015: 1 Hours
 * - made background smooth and seemless
 */
public class Background
{
  /**x - double x position of the top left corner of the image on the panel*/
  double x = 0;
  /**y - double y position of the top left corner of the image on the panel*/
  double y = 0;
  /**image - Image used to store the image of the background*/
  Image image;
  
  /** 
   * Constructor used to make the background and load it's image
   */
  public Background()
  {
    image = Toolkit.getDefaultToolkit().getImage(new File(System.getProperty("user.dir")).getParentFile().toString() +
                                                 System.getProperty("file.separator") + "Ocean's 15 code" + 
                                                 System.getProperty("file.separator") + "res" + 
                                                 System.getProperty("file.separator") + "background.png");
  }
  
  /**
   * Method used to move the background so it looks like the dolphin is moving forward
   * 
   * <p>
   * <b>Conditional Statements:</b>
   * <p>
   * <b> 1st conditional statement</b> used to reset the background so if it goes too far offscreen it resets to 0.
   */
  public void move()
  {
    x -= Dolphinity.dolphin.velocityX;
    if(x <= -800)
      x = 0;
  }
}
