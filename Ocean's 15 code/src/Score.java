/**
 * data class used to make Score objects and access their values
 * 
 * @author A Barkin
 * @version 5.0.0 06.09.2015
 * 
 * Alex - Updated Time Spent as of June 9, 2015: 1 Hours
 * - writing out class
 * - adding different methods for all necessary addition to score
 */
public class Score
{
  /**score - int used to store the score which this person gets*/
  private int score = 0;
  /**name - String used to hold the name of the user*/
  private String name= "";
  /**level - String used to hold the level which the user got the score on*/
  private String level;
  
  /**
   * Constructor used to make a new Score and assign it values
   * @param name - String used to send in the name of the player
   * @param score - int used to send in the score of the player
   * @param level - String used to send in the level which the player played
   */
  public Score (String name,int score, String level)
  {
    this.name = name;
    this.score=score;
    this.level = level;
  }
  
  /**
   * Method used to access the name of the Score
   * @return returns the name for the score
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * Method used to set the name of the Score
   * @param name - the name of the Score 
   */
  public void setName(String name)
  {
    this.name = name;
  }
  
  /**
   * Method used to access the score of the Score
   * @return returns the score for the score
   */
  public int getScore(){
    return score;
  }
  
  /**
   * Method used to set the score of the Score
   * @param score - the score of the Score
   */
  public void setScore(int score){
    this.score = score;
  }
  
  /**
   * Method used to access the level of the Score
   * @return returns the level for the score
   */
  public String getLevel()
  {
    return level;
  }
  
  /**
   * Method used to set the level of the Score
   * @param level - the level of the Score
   */
  public void setLevel(String level)
  {
    this.level = level;
  }
  
  /**
   * Method used to access the score of the Score and increase it by 10 for collecting garbage
   * @return returns the score for the score
   */
  public int scoreAddGarbage()
  {
    score +=10;
    return score;
  }
  
  /**
   * Method used to access the score of the Score and increase it by 1 for adding distance
   * @return returns the score for the score
   */
  public int scoreAddDistance()
  {
    score +=1;
    return score;
  }
  
  /**
   * Method used to access the score of the Score and increase it by 5 for eating a fish
   * @return returns the score for the score
   */
  public int scoreAddFish()
  {
    score += 5;
    return score;
  }
  
  /**
   * Method used to access the score of the Score and increase it by 20 for catching a hoop
   * @return returns the score for the score
   */
  public int scoreAddHoop()
  {
    score += 20;
    return score;
  }
}