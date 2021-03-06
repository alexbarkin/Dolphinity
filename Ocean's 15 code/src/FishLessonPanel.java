import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Panel used to display to the user what fish are endangered on the level they are playing.  Also shows the
 * instructions.
 * 
 * @author Alex Barkin
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of June 9, 2015: 4.5 Hours
 * -refinement and adding different lessons to enhance lesson
 */
public class FishLessonPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
  /**imageLabel - JLabel used to show the name of the endangered fish*/
  JLabel imageLabel;
  /**enter - boolean true if any key has been pressed or the mouse has been clicked*/
  public static boolean enter = false;
  /**escape - boolean true if the escape key has been pressed*/
  public static boolean escape = false;
  /**done - boolean true when panel should stop being displayed*/
  public static boolean done = false;
  /**WIDTH - int width of the panel in pixels*/
  public static final int WIDTH = 800;
  /**Height - int height of the panel in pixels*/
  public static final int HEIGHT = 590;
  
  /**
   * Constructor calls the JPanel constructor
   */
  public FishLessonPanel()
  {
    super();
  }
  
  /**
   * Method invoked when a key is pressed used to control program flow when a key that is pertanent to the program is pressed
   * @param e- KeyEvent used to store the value of the key pressed
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to set what option was clicked based on what key was pressed
   */
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
      escape = true;
    else 
      enter = true; 
    paintComponent(this.getGraphics());
  }
  
  /**
   * Method invoked when a key is released used to undo the effects of keyPressed()
   * @param e- KeyEvent used to store the value of the key released
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to set what option was released based on what key was released
   */
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
      escape = false;
    else 
      enter = false; 
  }
  
  /**
   * invoked when a key is typed used to control program flow when a key that is pertanent to the program is typed
   * @param e- KeyEvent used to store the value of the key typed
   */
  public void keyTyped (KeyEvent e)
  {
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
    * @return returns the image which is read and hopefully does not catch the IOException if it does it returns null
    * 
    */
  private BufferedImage getImage(String name) {
    try {
      BufferedImage image = ImageIO.read(new File(new File(System.getProperty("user.dir")).getParentFile().toString() +
                                                  System.getProperty("file.separator") + "Ocean's 15 code" + 
                                                  System.getProperty("file.separator") + "res" + 
                                                  System.getProperty("file.separator") + name+".png")) ;
      return image;
    } 
    catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error", "Image could not be found", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    return null;
  }
  
  /**
   * Method overwritten to fill the requirements of MouseMotionListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseExited(MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseMotionListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseDragged(MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseMotionListener 
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseMoved(MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseEntered(MouseEvent e)
  {
  }
  
  /**
   * invoked when a mouse button is released used to control program flow when a mouse button that is pertanent to the program is released
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseReleased(MouseEvent e)
  {
    enter = false;
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener 
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseClicked(MouseEvent e)
  {
    
  }
  
  /**
   * invoked when a mouse button is pressed used to control program flow when a mouse button that is pertanent to the program is pressed
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mousePressed(MouseEvent e)
  {
    enter = true; 
  }
  
  /**
   * Overwritten paintComponent method used to paint the paint with the specified graphics
   * <p>
   * <b> Local variables: </b>
   * <p>
   * <b> condition </b> int number of endangered fish in the game based on level
   * <p>
   * <b> x </b> int x position of fish
   * <p>
   * <b> y </b> int y position of fish
   * <p>
   * <b> i </b> int for loop variable to go through all endangered fish on the selected level
   * <p>
   * <b> t </b> int y offset for fish names
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to only draw on the screen if the screen exists
   * <p>
   * <b> 1st if statement in 1st if statement</b> used to move the selector depending on what the surrent choice is
   * <p>
   * <b> 1st if statement in 1st if statement in 1st if statement</b> used to centre the fishes on the screen
   * <p>
   * <b> 2nd if statement in 1st if statement in 1st if statement</b> used to centre the fishes on the screen
   * <p>
   * <b> 2nd if statement in 1st if statement</b> used to write the name of the fish but not the word endangered
   * 
   * <p>
   * <b>Loops:</b>
   * <p>
   * <b> 1st for loop</b> used to print every word in the fishes name
   * 
   * @param g- Graphics used to hold what to paint on the screen
   */
  public void paintComponent (Graphics g)
  {
    if(g != null && !done)
    {
      g.drawImage(getImage("background"),0,0,null);
      g.setColor(Color.red);
      g.setFont(new Font ("Palitino",0,30));
      g.drawString ("Why hello there, my mammalian friend! Nice to see you out",10,30);
      g.drawString ("fishing so early. Today in our waters, there"+((LevelMenuPanel.choice != 0)?" are ":" is ")+((LevelMenuPanel.choice == 0)? 1 :(LevelMenuPanel.choice == 1)?3:6)+ " endangered" ,10,60);
      g.drawString ("fish in the water which you are NOT allowed to eat! Sadly,",10,90);
      g.drawString ("our waters have been polluted, so here is a net if you would",10,120);
      g.drawString ("like to help clean up the ocean. To eject the net, simply hold",10,150); 
      g.drawString ("'w'. Good luck fishing and remember: a non endangered fish",10,180);
      g.drawString ("is 5 points, collecting garbage is 10 points and jumping",10,210);
      g.drawString ("through a hoop is 20! Press a key or the mouse to continue.",10,240);
      int condition = ((LevelMenuPanel.choice == 0)? 1 :(LevelMenuPanel.choice == 1)?3:6);
      int x = 0;
      int t = 0;
      for (int i = 0;i<condition;i++)
      {
        t=0;
        int y =(int)(320+(Math.random()*100));
        if (condition ==1)
          x=300;
        else if (condition == 3)
        {
          if (i==0)
            x=-260;
          x+=300;
        }
        else 
        {
          if (i==0)
            x=-100;
          x+=130;
        }
        g.drawImage(getImage("glow"),x-80,y-100,null);
        g.drawImage(Dolphinity.endangeredFishImages[i],x,y,null);
        
        g.setColor(Color.yellow);
        g.setFont(new Font ("Palitino",0,20));
        String fishName ="";
        String []name=Dolphinity.endangeredFishFileNames[i].split("_");
        for (String j :name)
          if (!j.equals("Endangered"))
        {
          g.drawString(j,x,y+55+(t*20));
          t++;
        }
      }
    }
    done = true;
  }
}
