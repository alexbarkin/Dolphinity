import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Panel used to display the splash screen which is a gif
 * 
 * @author A Barkin
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of May 22, 2015: 3 Hours
 * - getting gif to work
 * - making a key or mouse override timer
 * - timer
 */
public class SplashScreen implements KeyListener, MouseMotionListener,MouseListener
{
  /**frame - JFrame used to hold the panel which holds the gif*/
  JFrame frame;
  /**contentPane - JPanel used to hold the gif*/
  JPanel contentPane;
  /**imageLabel - JLabel used to hold the gif so that it still animates*/
  JLabel imageLabel = new JLabel();
  /**startTime - long used to hold the time so the gif only runs for a select amount of time*/
  long startTime;
  /**enter - boolean used to register an enter so that it skips the splash screen*/
  boolean enter = false;
  
  /**
   * Constructor used to make the JPanel and the JFrame and add everything to it
   * 
   * <p>
   * <b>Local variables:</b>
   * <p>
   * <b>e -</b> Exception used incase of error
   * 
   * <p>
   * <b>Try Catch statements:</b>
   * <p>
   * <b> 1st Try Catch</b> used to ensure the image icon does not have any errors
   */
  public SplashScreen() 
  {
    try {
      startTime = System.currentTimeMillis();
      frame = new JFrame();
      frame.setUndecorated(true);
      frame.setAlwaysOnTop (true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = (JPanel) frame.getContentPane();
      frame.setSize(new Dimension(600, 480));
      ImageIcon ii = new ImageIcon(this.getClass().getResource("logo.gif"));
      imageLabel.setIcon(ii);
      contentPane.add(imageLabel);
      frame.addKeyListener(this);
      frame.addMouseListener(this);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    } 
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error", "GoodBye screen gif could not be found", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    doSplashScreen();
  }
  
  /**
   * Method invoked when a key is pressed used to control program flow when a key that is pertanent to the program is pressed
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to set the variable to skip the splash screen when enter or space is pressed
   * 
   * @param e- KeyEvent used to store the value of the key pressed
   */
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyChar() ==' ' || e.getKeyCode () == KeyEvent.VK_ENTER )
      enter =true;
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
   * Method overwritten to fill the requirements of MouseListener and reverses the effects of mouseClicked()
   * @param e- MouseEvent used to store the value of the tracked mouse
   */
  public void mouseReleased(MouseEvent e)
  {
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
   */
  public void mousePressed(MouseEvent e)
  {
    enter = true; 
  }
  
  /**
   * Method invoked when a key is released used to undo the effects of keyPressed()
   * @param e- KeyEvent used to store the value of the key released
   */
  public void keyReleased(KeyEvent e)
  {
  }
  
  /**
   * Method overwritten to fill the requirements of KeyListener
   * @param e- KeyEvent used to store the value of the key released
   */
  public void keyTyped(KeyEvent e)
  {
    
  }
  
  
  /**
   * Method used to run the splash screen until the time is up or a key or mouse is clicked
   * 
   * <p>
   * <b>Conditional statements:</b>
   * <p>
   * <b> 1st if structure</b> used to skip the splash screen when enter or the time is up
   * 
   * <p>
   * <b>Loops:</b>
   * <p>
   * <b> 1st while loop</b>  used to run the splash screen until a key/mouse is pressed or the time is up
   */
  public void doSplashScreen()
  {
    while(true)
    {
      if(System.currentTimeMillis() - startTime >= 5000 || enter)
      {
        break;
      }
      contentPane.repaint();
    }
    frame.setVisible(false); 
    new Dolphinity();
  }
}
