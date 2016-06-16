import java.io.Console;

public class Prompter {
  
  private Jar mJar;
  
  public Prompter(Jar jar) {
    mJar = jar;
  }
  
  public void play() {
    String item;
    int numOfItem = 0;
    int answer;
    int guess = 0;
    int guessCount = 0;
    int credits;
    boolean wantsToPlay = true;
    String playAgain;
    int gameNumber = 0;
    Console console = System.console();
    credits = mJar.getCredits();
    // Start of setup
    console.printf("Administrative Setup\n--------------------\n");
    mJar.setupItem(item = console.readLine("What item should be placed in the jar?: "));
    // Continuously loop until numOfItem is valid input
    boolean isInvalidNum = true;
    while (isInvalidNum) {
      try {
        mJar.setupNumOfItem(numOfItem = Integer.parseInt(console.readLine("How many %s should the jar contain? (1 to %d): ", item, mJar.JAR_CAPACITY)));
        if (!mJar.validateNum(numOfItem)) {
          console.printf("\nThat is not within the range specified, please try again\n");
        } else {
          isInvalidNum = false;
        }
      } catch (NumberFormatException nfe) {
        console.printf("\nThat is not an actual number, please try again\n");
      }
    }
    // End of setup, start of guessing
    mJar.createHighScores();
    while (wantsToPlay == true && credits > 0) {    
      answer = mJar.getAnswer();
      console.printf("\nPlayer\n------\nYour goal is to guess how many %s are in the jar\n", item);
      boolean isGuessed = false;
      boolean isInvalidGuess = true;
      // Continuously loop until guess is valid input and is correct
      while (isInvalidGuess || !isGuessed) {
        try {
          guess = mJar.processGuess(Integer.parseInt(console.readLine("Enter a guess between 1 and %d: ", numOfItem)));
          guessCount = mJar.guessCounter();
          
          if (!mJar.validateGuess(guess)) {
            console.printf("\nThat is not within the range specified, please try again (your guess count is unaffected)\n\n");
          } else {
            isInvalidGuess = false;
          }
        } catch (NumberFormatException nfe) {
          console.printf("\nThat is not an actual number, please try again\n");
        }
        isGuessed = mJar.compareGuessToAnswer();
      }
      // Full game completed, deduct a credit and congratulate player
      credits = mJar.minusCredit();
      console.printf("\nCongratulations! You guessed that there are %d %s in the jar. It took you %d guess(es)\n", answer, item, guessCount);
      // Present player with high scores
      mJar.setHighScores(console.readLine("Enter your name: "), guessCount, gameNumber);
      
      if (credits > 0) {
        playAgain = console.readLine("You have %d credit(s) remaining. Enter 'yes' to play again, all other input will end the game: ", credits);
        if (playAgain.equalsIgnoreCase("yes")) {
          console.printf("\nHere we go again!\n");
          gameNumber++;
          mJar.resetGuessCount(0);
        } else {
          wantsToPlay = false;
        }
      } else {
        wantsToPlay = false;
        System.exit(0);
      }
    }
  }
  
}































