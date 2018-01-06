import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;

/**
 * Panel used to display the main menu to the user and select between the options and control program flow
 * 
 * @author A Barkin
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of June 9, 2015: 3.5 Hours
 * - first menu made so it took longer
 * - making menu options save their value 
 * - getting selector and hover to work
 */
public class MainMenuPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
  /**choice - int used to store which choice is currently selected*/
  public static int choice = 0;
  /**CHOICE_MAX - final int used to store the max amount of options on the screen*/
  public static final int CHOICE_MAX = 2;
  /**selectorY - int used to store the current y location of where the selector is*/
  public int selectorY = 60;
  /** enter - boolean used when the enter key is pressed
    * escape - boolean used when escape is selected
    * play - boolean used when play is selected
    * highScores - boolean used when high scores is selected
    * info -boolean used when info is selected
    * question - boolean used whenquestion is selected
    */
  public static boolean enter = false, escape = false, play = false, highScores = false, info =false, question = false;
  /**hint - JLabel used to show the key shortcuts when that option is being "hovered" over*/
  public JLabel hint;
  /**WIDTH - final int used to store the preferred width of the screen*/
  public static final int WIDTH = 780;
  /**HEIGHT - final int used to store the preferred height of the screen*/
  public static final int HEIGHT = 400;
  
  /**
   * Constructor used to call the JPanel and set it's layout to null it also creates the hint JLabel and adds it to said panel
   */
  public MainMenuPanel ()
  {
    super ();
    setLayout(null);
    hint = new JLabel();
    hint.setForeground(Color.WHITE);
    hint.setSize(100, 40);
    hint.setLocation(200, 70);
    hint.setText("press p");
    add(hint);
  }
  
  /**
   * Method invoked when a key is pressed used to control program flow when a key that is pertanent to the program is pressed
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to direct which option is selected depending on which key is pressed
   * <p>
   * <b> 1st if structure in the 1st if structure</b> used to reset the selector to let it go in circles
   * <p>
   * <b> 2nd if structure in the 1st if structure</b> used to reset the selector to let it go in circles
   * <p>
   * <b> 3rd if structure in the 1st if structure</b> used to differentiate which option is selected when the general enter key is pressed (checks where selector is)
   * <p>
   * <b> 2nd if structure </b> used to move the hint depending on what is currently selected
   * 
   * @param e- KeyEvent used to store the value of the key pressed
   */
  public void keyPressed (KeyEvent e)
  {
    if (e.getKeyCode () == KeyEvent.VK_UP)
    {
      if(choice >= 1)
      {
        choice--;
      }
      else if(choice == 0)
      {
        choice =4;
      }
      else
      {
        choice = CHOICE_MAX; 
      }
    }
    else if (e.getKeyCode () == KeyEvent.VK_DOWN)
    {
      if(choice <= 3)
      {
        if (choice == 3)
          choice = 4;
        else
          choice++;
      }
      else if(choice == CHOICE_MAX)
      {
        choice = 3;
      }
      else
      {
        choice = 0; 
      }
    }
    else if (e.getKeyCode () == KeyEvent.VK_P)
    {
      choice = 0; 
      play = true;
      enter = true;
    }
    else if (e.getKeyCode () == KeyEvent.VK_H)
    {
      choice = 1; 
      highScores = true;
      enter = true;
    }
    else if (e.getKeyCode () == KeyEvent.VK_ESCAPE)
    {
      choice = 2; 
      escape = true;
      enter = true;
    }
    else if (e.getKeyCode () == KeyEvent.VK_I)
    {
      info =true;
    }
    else if (e.getKeyCode () == KeyEvent.VK_Q)
    {
      question =true;
    }
    else if (e.getKeyCode () == KeyEvent.VK_ENTER)
    {
      enter = true;
      if (choice == 0)
        play = true;
      else if (choice == 1)
        highScores = true;
      else if (choice == 2)
        escape = true;
      else if (choice == 4)
        info = true;
      else 
        if (choice == 3)
        question = true;
    }
    if(choice == 0)
    {
      hint.setLocation(200, 70);
      hint.setText("press p");
    }
    else if(choice == 1)
    {
      hint.setLocation(200, 150);
      hint.setText("press h");
    }
    else if(choice == 2)
    {
      hint.setLocation(200, 240);
      hint.setText("press  esc");
    }
    else if(choice == 3)
    {
      hint.setLocation(700, 350);
      hint.setText("press q");
    }
    else 
    {
      if(choice == 4)
      {
        hint.setLocation(700, 150);
        hint.setText("press i");
      }
    }
    paintComponent (this.getGraphics ());
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
   * <b> 2nd if statement</b> used to move the hint depending on what is currently selected
   * <p>
   * <b> 3rd if statement</b> used to only refresh the screen if something has changed 
   * 
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseMoved (MouseEvent e)
  {
    boolean changed= false;
    int y = e.getY();
    int x = e.getX();
    if(y > 95 && y <= 175)
    {
      if (x>600)
        choice = 4;
      else
      {
        if (choice !=0)
          changed = true;
        choice = 0;
      }
    }
    else if(y > 175 && y <= 265)
    {
      if (x>600)
        choice = 3;
      else
      {
        if (choice !=1)
          changed = true;
        choice = 1; 
      }
    }
    else 
    {
      if(y > 265 && y <= 350)
      { 
        if (x>600)
          choice = 3;
        else
        {
          if (choice !=2)
            changed = true;
          choice = 2; 
        }
      }
    }
    if(choice == 0)
    {
      hint.setLocation(200, 70);
      hint.setText("press p");
    }
    else if(choice == 1)
    {
      hint.setLocation(200, 150);
      hint.setText("press h");
    }
    else if(choice == 2)
    {
      hint.setLocation(200, 240);
      hint.setText("press esc");
    }
    else if(choice == 3)
    {
      if (choice !=3)
        changed = true;
      hint.setLocation(700, 350);
      hint.setText("press q");
      this.repaint();
    }
    else 
    {
      if(choice == 4)
      {
        if (choice !=4)
          changed = true;
        hint.setLocation(700, 150);
        hint.setText("press i");
        this.repaint();
      }
    }
    if (changed)
      paintComponent (this.getGraphics ());
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
   */
  public void mouseClicked (MouseEvent e)
  {
    
  }
  
  /**
   * Method overwritten to fill the requirements of MouseListener used to control menu flow depending on which key is pressed
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to select an option when the mouse is pressed
   *
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mousePressed (MouseEvent e)
  {
    if (choice ==4)
      info = true;
    else if (choice == 3)
      question = true;
    else if (choice == 0)
      play = true;
    else if (choice == 1)
      highScores = true;
    else 
      if (choice == 2)
      escape = true;
    paintComponent (this.getGraphics ());
  }
  
  /**
   * Method invoked when a key is released used to undo the effects of keyPressed()
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to reverse the key pressed if the key is released
   * 
   * @param e- KeyEvent used to store the value of the key released
   */
  public void keyReleased (KeyEvent e)
  {
    if (e.getKeyCode () == KeyEvent.VK_ENTER)
    {
      enter = false;
    }
  }
  
  /**
   * Method overwritten to fill the requirements of KeyListener
   * @param e- KeyEvent used to store the value of the key released
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
  public BufferedImage getImage (String name)
  {
    try
    {
      BufferedImage image = ImageIO.read (new File (new File (System.getProperty ("user.dir")).getParentFile ().toString () +
                                                    System.getProperty ("file.separator") + "Ocean's 15 code" +
                                                    System.getProperty ("file.separator") + "res" +
                                                    System.getProperty ("file.separator") + name + ".png"));
      return image;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, "Error", "Image could not be found", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    return null;
  }
  
  /**
   * Overwritten paintComponent method used to paint the paint with the specified graphics
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if statement</b> used to only draw on the panel if it exists
   * <p>
   * <b> 2nd if statement</b> used to differentiate selection of the options between 1,2,3 and 4
   * <p>
   * <b> 3rd if statement</b> used to change the image depending on which option needs highlighting
   * 
   * @param g- Graphics used to hold what to paint on the screen
   */
  public void paintComponent (Graphics g)
  {
    if (g != null)
    {
      g.drawImage (getImage ("underwaterMenu"), 0, 0, null);
      g.drawImage (getImage ("info"), 600, 40, null);
      g.drawImage (getImage ("question"), 600, 240, null);
      if (choice <3)
      {
        selectorY = 60 + 75 * choice;
        g.drawImage (getImage ("logo"), 50, selectorY, null);
        if (choice == 0)
          g.drawImage (getImage ("blue1"), 198, selectorY+((int)(selectorY/4.5)), null);
        else if (choice == 1)
          g.drawImage (getImage ("blue"), 198, selectorY+((int)(selectorY/4.5)), null);
        else 
          g.drawImage (getImage ("blue3"), 198, selectorY+((int)(selectorY/4.5)), null);
      }
      else 
      {
        if (choice == 4)
          g.drawImage (getImage ("blue2"), 615, 56, null);
        else 
          g.drawImage (getImage ("blue2"), 615, 256, null);
      }
    }
    enter = false;
  }
}
