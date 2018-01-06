import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *Game panel class.  It is a JPanel for the game to be drawn on
 * 
 * @author Alex Barkin edited by Jordan Levy
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of June 9, 2015: 6.5 Hours
 * - first menu took longer also adding the cursor and info
 * 
 * Jordan - Updated Time Spent as of May 29, 2015: 0.5 Hours
 * -helped proper scrolling and flow
 */
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
  /**dolphinity - Dolphinity object to access the class*/
  Dolphinity dolphinity;
  /**key_space - boolean used to recognize when the space bar is pressed
    * key_escape - boolean used to recognize when the escape key is pressed
    * key_enter - boolean used to recognize when the enter key is pressed
    * key_w - boolean used to recognize when the w key is pressed
    * gameOver - boolean used to recognize when the game is over*/
  public static boolean key_space, key_escape, key_enter, key_w, gameOver;
  /**count - int used to keep objects on screen for a certain amount of time after contact*/
  int count = 0;
  /**on - boolean used to keep track whether the mouse is on the pause button*/
  boolean on;
  /**start - long used to reset the seconds to make sure it counts accurately*/
  static long start = 00;
  /**hours - long used to hold the number of hours*/
  static long hours = 00;
  /**minutes - long used to hold the number of minutes*/
  static long minutes = 00;
  /**seconds - long used to hold the number of seconds*/
  static long seconds = 00;
  /**oldMinutes - long used to make sure it only adds one minute when 60 seconds is reached*/
  static long oldMinutes = 00;
  /**oldHours - long used to make sure it only adds one hour when 60 minutes is reached*/
  static long oldHours = 00;
  
  /**
   * Constructor used to call the JPanel and set the dolphinity to the current dolphinity passed in
   * @param dolphinity Dolphinity object sent in to gain access to current Dolphinity
   */
  public GamePanel(Dolphinity dolphinity)
  {
    super();
    this.dolphinity = dolphinity; 
  }
  
  /** Method used to read an image and return said image
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>image -</b> BufferedImage used to store the image before it is returned
    * <p>
    * <b>e -</b> IOException used incase of an IO error
    * 
    * <p>
    * <b>Try Catch statements:</b>
    * <p>
    * <b> 1st Try Catch</b> used to ensure program safety incase image cannot be found
    * 
    * @param name A String used to identify the image to be read.
    * @return returns the BufferedImage which is read and hopefully does not catch the IOException if it does it returns null
    * 
    */
  private BufferedImage getImage (String name)
  {
    try
    {
      BufferedImage image = ImageIO.read (new File (new File (System.getProperty ("user.dir")).getParentFile().toString () +
                                                    System.getProperty ("file.separator") + "Ocean's 15 code" +
                                                    System.getProperty ("file.separator") + "res" +
                                                    System.getProperty ("file.separator") + name + ".png"));
      return image;
    }
    catch (IOException e)
    {
      e.printStackTrace ();
    }
    return null;
  }
  
  /**
   * Method overwritten to fill the requirements of MouseMotionListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseExited (MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseMotionListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseDragged (MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseMotionListener and to 
   *      track where the mouse is when its over the pause nbutton the hint appears
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to change the image if the cursor is on the pause button
   * 
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseMoved (MouseEvent e)
  {
    if (e.getX()>700 && e.getX()<827&&e.getY()>20&&e.getY()<137)
    {
      on =true;
    }
    else 
      on=false;
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseEntered (MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener and reverses the effects of mouseClicked()
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseReleased (MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to change the state of the entered variable if the mouse is clicked
   */
  public void mouseClicked (MouseEvent e)
  {
    if (e.getX()>700 && e.getX()<827&&e.getY()>20&&e.getY()<137)
    {
      key_enter = true;
    }
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener used to control menu flow depending on which key is pressed
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mousePressed (MouseEvent e)
  {
  }
  
  /**
   * Method invoked when a key is pressed used to control program flow when a key that is pertanent to the program is pressed
   * @param e- KeyEvent used to store the value of the key pressed
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to change which key is pressed depending on which key is pressed
   */
  public void keyPressed(KeyEvent e)
  {
    if(e.getKeyChar() == ' ')
    {
      key_space = true; 
    }
    if(e.getKeyCode() == KeyEvent.VK_W)
    {
      key_w = true; 
    }
    else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
    {
      key_escape = true; 
    }
    else 
    {
      if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_P)
      {
        key_enter = true; 
      }
    }
  }
  
  /**
   * Method invoked when a key is released used to undo the effects of keyPressed()
   * @param e- KeyEvent used to store the value of the key released
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to change which key is released depending on which key is released
   * 
   */
  public void keyReleased(KeyEvent e)
  {
    if(e.getKeyChar() == ' ')
    {
      key_space = false; 
    }
    else if(e.getKeyCode() == KeyEvent.VK_W)
    {
      key_w = false; 
    }
    else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
    {
      key_escape = false; 
    }
    else
    {
      if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_P)
      {
        key_enter = false; 
      }
    }
  }
  
  /**
   * Method overwritten to fill the requirements of KeyListener
   * @param e- KeyEvent used to store the value of the key released
   */
  public void keyTyped(KeyEvent e)
  {
    
  }
  
  
  /**
   * Overwritten paintComponent method used to paint the paint with the specified graphics
   * Draws all graphics and objects in the game...including dolphin and anything which it encounters
   * 
   * <p>
   * <b>Local variables:</b>
   * <p>
   * <b>cME -</b> ConcurrentModificationException used to cath if one of the iterators crashes (Happens more than you think)
   * <p>
   * <b>nul -</b> NullPointerException used incase an object is null
   * 
   * <p>
   * <b>Loops:</b>
   * <p>
   * <b> 1st for loop</b> used to go through every fish object in the ArrayList to ensure they are all functional
   * <p>
   * <b> 2nd for loop</b> used to go through every garbage object in the ArrayList to ensure they are all functional
   * <p>
   * <b> 3rd for loop</b> used to go through every hoop object in the ArrayList to ensure they are all functional
   * <p>
   * <b> 4th for loop</b> used to go through every hoop object in the ArrayList and draw it
   * 
   * <p>
   * <b>Try Catch statements:</b>
   * <p>
   * <b> 1st try catch</b> used to ensure the iterator in the array list nor the graphics being null don't crash the program
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to only draw anything if the game is running and the panel exists
   * <p>
   * <b> 1st if statement in 1st if statement</b> used to only set the fish to the next in the ArrayList if a next exists
   * <p>
   * <b> 2nd if statement in 1st if statement</b> used to only draw the fish if it hasnt collided with anything
   * <p>
   * <b> 3rd if statement in 1st if statement</b> used to draw the points recieved for a collision only if it hasnt already or stop the game if the fish is endangered
   * <p>
   * <b> 1st if statement in the 3rd if statement in 1st if statement</b> used to draw the bonus moving upwards until a certain point
   * <p>
   * <b> 4th if statement in 1st if statement</b> used to only set the garbage to the next in the ArrayList if a next exists
   * <p>
   * <b> 5th if statement in 1st if statement</b> used to only draw the garbage if it hasnt collided with anything
   * <p>
   * <b> 6th if statement in 1st if statement</b> used to draw the points recieved for a collision only if it hasnt already 
   * <p>
   * <b> 1st if statement in the 6th if statement in 1st if statement</b> used to draw the bonus moving upwards until a certain point
   * <p>
   * <b> 7th if statement in 1st if statement</b> used to only set the hoop to the next in the ArrayList if a next exists
   * <p>
   * <b> 8th if statement in 1st if statement</b> used to draw the points recieved for a collision only if it hasnt already 
   * <p>
   * <b> 1st if statement in the 8th if statement in 1st if statement</b> used to draw the bonus moving upwards until a certain point
   * <p>
   * <b> 9th if statement in the 1st if statement </b> used to only add to the distance evry time the counter reaches 150
   * <p>
   * <b> 10th if statement in the 1st if statement </b> used to add to the minutes mark every 60 seconds
   * <p>
   * <b> 1st if statement in the 10th if statement in the 1st if statement </b> used to add to the minutes mark every 60 seconds only if it hasn't done so for that minute
   * <p>
   * <b> 11th if statement in the 1st if statement </b> used to add to the hours mark every 60 minutes
   * <p>
   * <b> 1st if statement in the 11th if statement in the 1st if statement </b> used to add to the hours mark every 60 minutes only if it hasn't done so for that hour
   * <p>
   * <b> 12th if statement in the 1st if statement </b> used to eject the net when the user clicks 'w'
   * <p>
   * <b> 13th if statement in the 1st if statement </b> used to differentiate between pause images (labelled or not) if cursor is on top
   * 
   * @param g- Graphics used to hold what to paint on the screen
   */
  public void paint(Graphics g)
  {
    Fish f = null;
    Garbage ga = null;
    Hoop ha = null;
    count++;
    System.out.print("");
    if(g != null && Dolphinity.isRunning)
    {
      try
      {
        Graphics2D g2d = (Graphics2D)g;
        g.drawImage(dolphinity.background.image, (int)(dolphinity.background.x), (int)(dolphinity.background.y), null);
        ListIterator<Fish> it = Dolphinity.fishes.listIterator();    
        g.setFont(new Font("Magneto",Font.BOLD,25));
        g.setColor(Color.yellow);
        for(int i=0;i<Dolphinity.fishes.size();i++)
        {
          if(it.hasNext())
          {
            f = null;
            f = it.next();
          }
          if (!f.collided)
            g.drawImage(f.image, (int)f.x, (int)f.y, null);
          if (f.getCollisionStatus() == 1 && !f.isEndangered)
          { 
            Dolphinity.score.scoreAddFish();
            f.collided = true;
            g.drawString("+5",300,100);
            f.temp=0;
          }
          else if (f.getCollisionStatus() == 2 ||(f.getCollisionStatus() == 1 && f.isEndangered))
            gameOver = true;
          else 
          {
            if (f.collided)
            {
              f.temp++;
              if (f.temp<100 && count>100)
                g.drawString("+5",300,100-(f.temp/2));
            }
          }
        }
        ListIterator<Garbage> itt = Dolphinity.garbages.listIterator();    
        for(int p=0;p<Dolphinity.garbages.size();p++)
        {
          if(itt.hasNext())
          {
            ga = null;
            ga = itt.next();
          }
          if (!ga.collided)
            g.drawImage(ga.image, (int)ga.x, (int)ga.y, null);
          if (ga.getCollisionStatus() == 2)
          {
            Dolphinity.score.scoreAddGarbage();
            ga.collided = true;
            g.drawString("+15",400,100);
            ga.temp2=0;
          }
          else if (ga.getCollisionStatus() == 1)
            gameOver = true;
          else 
          {
            if (ga.collided)
            {
              ga.temp2++;
              if (ga.temp2<100 && count>100)
                g.drawString("+15",300,100-(ga.temp2/2));
            }
          }
        }
        ListIterator<Hoop> ittt = Dolphinity.hoops.listIterator();    
        for(int h=0;h<Dolphinity.hoops.size();h++)
        {
          if(ittt.hasNext())
          {
            ha = null;
            ha = ittt.next();
          }
          g.drawImage(ha.imageLeft, (int)ha.x, (int)ha.y, null);
          if (ha.getCollisionStatus() == 1)
          {
            Dolphinity.score.scoreAddHoop();
            ha.collided = true;
            g.drawString("+20",400,100);
            ha.temp2=0;
          }
          else 
          {
            if (ha.collided)
            {
              ha.temp2++;
              if (ha.temp2<100 && count>100)
                g.drawString("+20",300,100-(ha.temp2/2));
            }
          }
        }
        g.setFont(new Font("Magneto",0,30));
        g.setColor(Color.yellow);
        if (count%150==0)
          g.drawString("Score: "+Dolphinity.score.scoreAddDistance(),10,40);
        else
          g.drawString("Score: "+Dolphinity.score.getScore(),10,40);
        long control =  1000L;
        long  current = (System.currentTimeMillis()-start);
        seconds = current/control;
        if (seconds == 30)
          oldMinutes = minutes;
        if (seconds == 60)
        {
          if (oldMinutes == minutes)
          {
            oldMinutes = minutes;
            minutes ++;
            seconds = 00L;
            start = System.currentTimeMillis();
          }
        }
        if (minutes == 60)
        {
          if (oldHours == hours)
          {
            oldHours = hours;
            minutes=00;
            hours++;
          }
        }
        g.drawString("Time: "+ hours +" : " + minutes + " : " + seconds,250,40);
        AffineTransformOp op = new AffineTransformOp(Dolphinity.dolphin.rotationTransformation, AffineTransformOp.TYPE_BILINEAR);
        g2d.drawImage(op.filter(Dolphinity.dolphin.image, null), (int)(Dolphinity.dolphin.x + Dolphinity.dolphin.offsetX), (int)(Dolphinity.dolphin.y + Dolphinity.dolphin.offsetY), null);
        if(key_w)
        {
          op = new AffineTransformOp(Dolphinity.dolphin.net.rotationTransformation, AffineTransformOp.TYPE_BILINEAR);
          g2d.drawImage(op.filter(Dolphinity.dolphin.net.image, null), (int)(Dolphinity.dolphin.x + Dolphinity.dolphin.offsetX), (int)(Dolphinity.dolphin.y + Dolphinity.dolphin.offsetY), null);   
        }
        for(int h=0;h<Dolphinity.hoops.size();h++)
        {
          ha = Dolphinity.hoops.get(h);
          g.drawImage(ha.imageRight, (int)(ha.x + ha.imageLeft.getWidth(null)), (int)(ha.y), null);
        }
        if (on)
          g.drawImage(getImage("pause2"),700,20,null);
        else
          g.drawImage(getImage("pause"),700,20,null);
      }
      catch(ConcurrentModificationException cME)
      {}
      catch (NullPointerException nul)
      {}
    }
  }
}