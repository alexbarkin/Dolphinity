import java.awt.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.print.*;
import java.awt.image.*;
import javax.swing.*;

/**This class is used to store and print the high scores. It also has any functions related to the high scores like clearing them. 
  * 
  * @author A Barkin edited by J Levy
  * @version 5.0.0 09.06.15
  * 
  * Alex - Updated Time Spent as of May 29, 2015: 1.5 Hours
  * - setting up class 
  * - printing on printer 
  * 
  * Jordan - Updated Time Spent as of June 9, 2015: 2.5 Hours
  * - making it work with sorting properly
  * */
public class HighScores implements Printable
{
  /**The public static final string which holds the header used at the top of any jlab file*/
  public static final String HEADER = "This is a dolphinity incorporated file. Editing this file directly will void your warranty.";
  /**array list of score used to store the scores read from the file and used to add a new high score to*/
  public static ArrayList < Score > scores = new ArrayList < Score > ();
  
  /** Method used to read the highscores from the file and store them in the ArrayList and calls the sort method
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>in -</b> BufferedReader used to read all of the stored entries from the file.
    * <p>
    * <b>line1 -</b> String used to hold the name of each entry temporarily to make sure it is not empty.
    * <p>
    * <b>line2 -</b> String used to hold the score of each entry temporarily to make sure it is not empty.
    * <p>
    * <b>line3 -</b> String used to hold the level of each entry temporarily to make sure it is not empty.
    * <p>
    * <b>e -</b> IOException used incase it cannot find the file.
    * <p>
    * <b>f -</b> NumberFormatException used incase the file is not formatted correctly.
    * 
    * <p>
    * <b>Conditional statements:</b>
    * <p>
    * <b> 1st if structure</b> used to only read the file if the first line is the header
    * <p>
    * <b> 1st if structure in the 1st if structure</b> used to only continue if all entries for the entry are there
    * 
    * <p>
    * <b>Loops:</b>
    * <p>
    * <b> 1st while loop</b> used to read until one entry is empty
    * 
    * <p>
    * <b>Try Catch structures:</b>
    * <p>
    * <b> 1st Try Catch</b> used to catch if the file is not found or is corrupt
    */
  public static void read ()
  {
    scores = new ArrayList < Score > ();
    try
    {
      BufferedReader in = new BufferedReader (new FileReader (new File(System.getProperty("user.dir")).getParentFile().toString() +
                                                              System.getProperty("file.separator") + "Ocean's 15 code" + 
                                                              System.getProperty("file.separator") + "files" + 
                                                              System.getProperty("file.separator") + "highScores.jlab"));
      if (in.readLine ().equals (HEADER))
      {
        String line1 = "";
        String line2 = "";
        String line3 = "";
        while (line1 != null && line2 != null && line3 != null)
        {
          line1 = in.readLine ();
          line2 = in.readLine ();
          line3 = in.readLine ();
          if (line1 != null && line2 != null && line3 != null)
          {
            scores.add (new Score (line1, Integer.parseInt (line2), line3));
          }
        }
        sort ();
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Incorrect Header", "The high scores file has an incorrect header, so a new one is being created.", JOptionPane.INFORMATION_MESSAGE);
        clear();
      }
      in.close();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, "File Not Found", "The high scores file was not found, so a new one is being created.", JOptionPane.INFORMATION_MESSAGE);
      clear();
    }
    catch (NumberFormatException f)
    {
      clear();
    }
  }
  
  /** Method used to sort the high scores after they are read from the file
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>n -</b> int which holds the size of the ArrayList to be sorted.
    * <p>
    * <b>key -</b> Score used to temporarily hold the value of the Score being sorted.
    * <p>
    * <b>i -</b> int used for the inner counter.
    * <p>
    * <b>j -</b> int for the external counter.
    * 
    * <p>
    * <b>Loops:</b>
    * <p>
    * <b> 1st for loop</b> used to run through each spot in the ArrayList
    * <p>
    * <b> 1st while loop</b> used to search through each entry until it finds a lower one
    */
  public static void sort ()
  {
    int n = scores.size ();
    for (int j = 1 ; j < n ; j++)
    {
      Score key = scores.get (j);
      int i = j - 1;
      while ((i > -1) && (scores.get (i).getScore () < key.getScore ()))
      {
        scores.set (i + 1, scores.get (i));
        i--;
        scores.set (i + 1, key);
      }
    }
  }
  
  /** Method used to reset the score ArrayList and overWrite the file to clear all the entries.
    * 
    */
  public static void clear ()
  {
    scores = new ArrayList<Score>();
    write();
  }
  
  /** Method used to write the high scores to a file 
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>out -</b> PrintWriter used to write the entries to a file.
    * <p>
    * <b>i -</b> int used to run through every entry in the ArrayList.
    * <p>
    * <b>e -</b> IOException used incase of an IO error
    * 
    * <p>
    * <b>Loops:</b>
    * <p>
    * <b> 1st for loop</b> used to run through each spot in the ArrayList
    * 
    * <p>
    * <b>Try Catch Structures:</b>
    * <p>
    * <b> 1st Try Catch</b> used to catch if there is an error writing to the file
    */
  public static void write ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (new File(System.getProperty("user.dir")).getParentFile().toString() +
                                                         System.getProperty("file.separator") + "Ocean's 15 code" + 
                                                         System.getProperty("file.separator") + "files" + 
                                                         System.getProperty("file.separator") + "highScores.jlab"));
      out.println (HEADER);
      for (int i = 0 ; i < scores.size () ; i++)
      {
        out.println (scores.get (i).getName ());
        out.println (Integer.toString (scores.get (i).getScore ()));
        out.println (scores.get (i).getLevel ());
      }
      out.close ();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, "Error", "There was an error writing to the high scores file.", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
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
  
  /** Method used to print the high scores and make them look pretty
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>i -</b> int used to run through each value in the ArrayList
    * 
    * <p>
    * <b>Conditional statements:</b>
    * <p>
    * <b> 1st if statement</b> used to only print if there is a page to print
    * <p>
    * <b> 2nd if statement</b> used to draw all the highscores one way except the final one
    * 
    * <p>
    * <b>Loops:</b>
    * <p>
    * <b> 1st for loop</b> used to run through every high score
    * 
    * @param g Graphics used to hold the graphics to print
    * @param pf PageFormat used to format the page.
    * @param pageIndex Int which holds how many pages to print
    * @return returns whether the page exists so it will print if it does
    * 
    * for loop used to run throug every value in the ArrayList
    */
  public int print (Graphics g, PageFormat pf, int pageIndex) throws PrinterException
  {
    if (pageIndex > 0)
      return NO_SUCH_PAGE;
    g.drawImage(getImage("logo"),360,440,null);
    g.drawImage(getImage("dolphin"),0,280,null);
    g.setColor(new Color(51, 153, 255));
    g.setFont(new Font("Magneto", Font.PLAIN, 24));
    g.drawString("Dolphinity", 120, 550);
    g.setColor(new Color(230, 46, 0));
    g.setFont(new Font("Magneto", Font.PLAIN, 30));
    g.drawString("High Scores", 200, 50);
    g.setColor(Color.BLACK);
    g.setFont(new Font("Verdana", Font.PLAIN, 14));
    g.drawString("    Name", 75, 90);
    g.drawString("Score", 275, 90);
    g.drawString("Level", 425, 90);
    for(int i = 0; i < 10; i++)
    {
      if(i < HighScores.scores.size())
      {
        g.drawString((i + 1) + ". " + HighScores.scores.get(i).getName(),75, 130 + 32 * i);
        g.drawString("" + HighScores.scores.get(i).getScore(),275, 130 + 32 * i);
        g.drawString("" + HighScores.scores.get(i).getLevel(),425, 130 + 32 * i);
      }
      else
      {
        g.drawString((i + 1) + ". ",75, 130 + 32 * i); 
      }
    }
    return PAGE_EXISTS;
  }
}
