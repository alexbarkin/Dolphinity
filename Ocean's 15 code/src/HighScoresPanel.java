import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Panel used to display the high scores to the user and select between the three options (main menu, print, or clear)
 * 
 * @author Alex Barkin
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of June 9, 2015: 3.5 Hours
 * - Making panel and displaying scores
 * - Making buttons work
 * - Making buttons and selector work
 */
public class HighScoresPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
  /**hint - JLabel used to show the key shortcuts when that option is being "hovered" over*/
  JLabel hint;
  /**choice - int used to store which choice is currently selected*/
  public static int choice = 0;
  /** menu - boolean used when the menu is selected
    * escape - boolean used when escape is selected
    * print - boolean used when print is selected
    * changed -  boolean used to only repaint when something is changed
    * clear - boolean used when clear is selected*/
  public static boolean menu = false, escape = false, print = false, changed =true, clear = false;
  /**WIDTH - final int used to store the preferred width of the screen*/
  public static final int WIDTH = 740;
  /**HEIGHT - final int used to store the preferred height of the screen*/
  public static final int HEIGHT = 460;
  /**CHOICE_MAX - final int used to store the max amount of options on the screen*/
  public static final int CHOICE_MAX = 2;
  
  /**
   * Constructor used to call the JPanel and set it's layout to null it also creates the hint JLabel and adds it to said panel
   */
  public HighScoresPanel()
  {
    super();
    setLayout(null);
    hint = new JLabel();
    hint.setForeground(Color.WHITE);
    hint.setSize(100, 40);
    hint.setLocation((0-600), 70);
    hint.setText("press p");
    add(hint);
  }
  
  /**
   * Method invoked when a key is pressed used to control program flow when a key that is pertanent to the program is pressed
   * @param e- KeyEvent used to store the value of the key pressed
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to direct which option is selected depending on which key is pressed
   * <p>
   * <b> 1st if structure in the 1st if structure</b> used to differentiate which option is selected when the general enter key is pressed (checks where selector is)
   * <p>
   * <b> 2nd if structure in the 1st if structure</b> used to reset the selector to let it go in circles
   * <p>
   * <b> 3rd if structure in the 1st if structure</b> used to reset the selector to let it go in circles
   */
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
    {
      escape = true;
    }
    else if (e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      if(choice == 0)
        print = true;
      else if(choice == 1)
        clear = true;
      else
        menu = true;
    }
    else  if (e.getKeyCode () == KeyEvent.VK_UP)
    {
      changed = true;
      if(choice >= 1)
      {
        choice--;
      }
      else
      {
        choice = CHOICE_MAX; 
      }
    }
    else if (e.getKeyCode () == KeyEvent.VK_DOWN)
    {
      changed = true;
      if(choice < CHOICE_MAX)
      {
        choice++;
      }
      else
      {
        choice = 0; 
      }
    }
    else if (e.getKeyCode() == KeyEvent.VK_C)
    {
      choice = 2;
      clear = true;
    }
    else if (e.getKeyCode() == KeyEvent.VK_P)
    {
      choice = 1;
      print = true;
    }
    else 
    {
      if (e.getKeyCode() == KeyEvent.VK_M)
      {
        choice = 0;
        menu = true;
      }
    }
    paintComponent(this.getGraphics());
  }
  
  /**
   * Method invoked when a key is released used to undo the effects of keyPressed()
   * @param e- KeyEvent used to store the value of the key released
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to reverse the key pressed if the key is released
   */
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
      escape = false;
    else  if(e.getKeyCode() == KeyEvent.VK_P)
      print = false;
    else  if(e.getKeyCode() == KeyEvent.VK_C)
      clear = false;
    else
      if (e.getKeyCode() == KeyEvent.VK_M)
      menu = false; 
    paintComponent (this.getGraphics ());
  }
  
  /**
   * Method overwritten to fill the requirements of KeyListener
   * @param e- KeyEvent used to store the value of the key released
   */
  public void keyTyped(KeyEvent e)
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
   * Method overwritten to fill the requirements of MouseMotionListener and to 
   *      track where the mouse is when its moved and change the options accordingly
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to change the selector location based on where the cursor is
   * <p>
   * <b> 1st if statement in the 1st if statement</b> used to only change the cursor if it isnt already that spot
   * <p>
   * <b> 2nd if statement in the 1st if statement</b> used to only change the cursor if it isnt already that spot
   * <p>
   * <b> 3rd if statement in the 1st if statement</b> used to only change the cursor if it isnt already that spot
   * <p>
   * <b> 2nd if statement</b> used to only refresh the screen if something has changed
   * 
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseMoved(MouseEvent e)
  {
    if(e.getX() >= 625 && e.getY() <= 142 && e.getY() >= 0)
    {
      if (choice!=0)
        changed = true;
      else 
        changed = false;
      choice = 0;
    }
    else if(e.getX() >= 625 && e.getY() <= 283 && e.getY() >= 162)
    {
      if (choice!=1)
        changed = true;
      else 
        changed = false;
      choice = 1;
    }
    else if(e.getX() >= 625 && e.getY() <= 427&& e.getY() >= 307)
    {
      if (choice!=2)
        changed = true;
      else 
        changed = false;
      choice = 2;
    }
    if (changed)
      paintComponent (this.getGraphics ());
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseEntered(MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener and reverses the effects of mouseClicked()
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to reverse the effects of mouse clicked when its released
   * 
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseReleased(MouseEvent e)
  {
    if(choice == 0)
    {
      print = false; 
    }
    else if(choice == 1)
    {
      clear = false; 
    }
    else
    {
      if(choice == 2)
      {
        menu = false; 
      }
    }
    paintComponent (this.getGraphics ());
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseClicked(MouseEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener used to control menu flow depending on which key is pressed
   * @param e- MouseEvent used to store the value of the tracked mouse
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to select an option when the mouse is pressed
   *
   */
  public void mousePressed(MouseEvent e)
  {
    if(choice == 0)
    {
      print = true; 
    }
    else if (choice == 1)
    {
      clear = true;
    }
    else
    {
      if (choice == 2)
        menu = true; 
    }
    paintComponent (this.getGraphics ());
  }
  
  /**
   * Overwritten paintComponent method used to paint the paint with the specified graphics
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to only draw on the panel if it exists
   * <p>
   * <b> 2nd if statement</b> used to draw all the highscores one way except the last one
   * <p>
   * <b> 3rd if statement</b> used to change the hint according to which option is selected
   * 
   * <p>
   * <b>Loops:</b>
   * <p>
   * <b> 1st for loop</b> used to run though every high score entry
   * 
   * @param g- Graphics used to hold what to paint on the screen
   */
  public void paintComponent (Graphics g)
  {
    if(g != null)
    {
      g.drawImage(getImage("UnderWaterHighScores"),0,0,null);
      g.setFont(new Font("Verdana", Font.PLAIN, 14));
      g.drawString("    Name", 175, 90);
      g.drawString("Score", 325, 90);
      g.drawString("Level", 475, 90);
      for(int i = 0; i < 10; i++)
      {
        if(i < HighScores.scores.size())
        {
          g.drawString((i + 1) + ". " + HighScores.scores.get(i).getName(),175, 112 + 32 * i);
          g.drawString("" + HighScores.scores.get(i).getScore(),325, 112 + 32 * i);
          g.drawString("" + HighScores.scores.get(i).getLevel(),475, 112 + 32 * i);
        }
        else
        {
          g.drawString((i + 1) + ". ",175, 112 + 32 * i); 
        }
      }
      g.drawImage (getImage ("blue2"), 635, 33+(choice*142), null); 
      if(choice == 0)
      {
        hint.setText("press p");
      }
      else if(choice == 1)
      {
        hint.setText("press c");
      }
      else 
      {
        if(choice == 2)
        {
          hint.setText("press  m");
        }
      }
      hint.setLocation(700,130+(choice*142));
      hint.repaint();
    }
    menu = false;
  }
}