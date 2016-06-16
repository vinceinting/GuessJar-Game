import java.util.Random;

public class Jar {
  
  private String mItem;
  private int mNumOfItem;
  private int mAnswer;
  private int mGuess;
  private int mGuessCount;
  private int mCredits = 2;
  public static final int JAR_CAPACITY = 1000;
  private String[] highName;
  private int[] highScore;
  private String[] scoreboard;
  
  public Jar(String item, int numOfItem) {
    
  }

  public int getCredits() {
    return mCredits;
  }
  
  public int minusCredit() {
    return --mCredits;
  }
  
  public void setupItem(String item) {
    mItem = item;
  }
  
  public void setupNumOfItem(int numOfItem) {
    mNumOfItem = numOfItem;
  }
  
  public boolean validateNum(int numOfItem) {
    if (numOfItem <= 0 || numOfItem > JAR_CAPACITY) {
      return false;
    } else {
      return true;
    }
  }
  
  public int getAnswer() {
    Random random = new Random();
    return mAnswer = (random.nextInt(mNumOfItem) + 1);
  }
  
  public boolean validateGuess(int guess) {
    if (guess <= 0 || guess > mNumOfItem) {
      return false;
    } else {
      return true;
    }
  }
  
  public int processGuess(int guess) {
    mGuess = guess;
    return mGuess;
  }
  
  public int guessCounter() {
    mGuessCount++;
    return mGuessCount;
  }
  
  public boolean compareGuessToAnswer() {
    if (mGuess == mAnswer) {
      return true;
    } else {
      if (!validateGuess(mGuess)) {
        mGuessCount--;
      } else {
        if (validateGuess(mGuess) && mGuess > mAnswer) {
          System.out.printf("\nYou guessed too high, please try again\n");
        }
        if (validateGuess(mGuess) && mGuess < mAnswer) {
          System.out.printf("\nYou guessed too low, please try again\n");
        }
      }
      return false;
    }
  }
  
  public void createHighScores() {
    highName = new String[mCredits];
    highScore = new int[mCredits];
    scoreboard = new String[mCredits];
  }
  
  public void setHighScores(String name, int guessCount, int gameNumber) {
    System.out.printf("\nHigh Scores\n-----------\n");
    highName[gameNumber] = name;
    highScore[gameNumber] = guessCount;
    scoreboard[gameNumber] = name + " - " + Integer.toString(guessCount);
    for (int i = 0; i < (gameNumber + 1); i++) {
      System.out.println(scoreboard[i] + "\n");
    }
  }
  
  public void resetGuessCount(int guessCount) {
    mGuessCount = guessCount;
  }
  
}