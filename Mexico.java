
/*
Created by: GREG WOO
  Program: Simulate a game of Mexico

   Sample Input:
   run Mexico 10.0 5.0
   Output:
    Round 1
    Page 5
    Giulia rolled: 5 2
    Giulia’s score is: 52
    David rolled: 3 5
    David’s score is: 53
    David wins this round
    Round 2
    Giulia rolled: 3 1
    Giulia’s score is: 31
    David rolled: 4 4
    David’s score is: 44
    David wins this round
    David won the game!
  */

public class Mexico {


  public static void main(String[] args) {

    playMexico(Double.parseDouble(args[0]), Double.parseDouble(args[1]));

  }


  //1a. A method to simulate a dice roll
  public static int diceRoll() {
    int x = (int) (Math.random() * 6) + 1;
    return x;
  }

  //1b. A method to compute the score of a Player
  public static int getScore(int x, int y) {
    int score;

    if (x>=y) {
     score = x*10 + y;
    } else {
     score = y*10 + x;
    }
    return score;
  }

  //1c. A method to simulate one round of Mexico
  public static int playOneRound(String namePlayer) {
    int rollOne, rollTwo;
    rollOne = diceRoll();
    rollTwo = diceRoll();

    int score = getScore(rollOne,rollTwo);
    System.out.println( namePlayer + " rolled: " + rollOne + " " + rollTwo );
    System.out.println( namePlayer + "'s score is: " + score ) ;
    return score;
  }

  //1d. A method to determine the winner of one round
  public static String getWinner(int scorePlayerOne, int scorePlayerTwo) {

    String result;

    // if it's a tie
    if (scorePlayerOne == scorePlayerTwo) {
      result = "Tie";

    // if one player scores 21
    } else if (scorePlayerOne == 21  || scorePlayerTwo == 21){
      if (scorePlayerOne == 21) {
        result = "Giulia";
      } else {
        result = "David";
      }

    //if one player scored a double
    } else if ((scorePlayerOne % 11 == 0 )  || (scorePlayerTwo % 11 == 0 )){
      //if both players scored a double
      if ((scorePlayerOne % 11 == 0 )  && (scorePlayerTwo % 11 == 0 )) {
        if (scorePlayerOne > scorePlayerTwo) {
          result = "Giulia";
        } else {
          result = "David";
        }
      //if playerOne scores a double
      } else if (scorePlayerOne % 11 == 0 ) {
        result = "Giulia";
      //if playerTwo scores a double
      } else {
        result = "David";
      }

    // if neither players score 21 or a double
    } else {
      if (scorePlayerOne > scorePlayerTwo) {
      result = "Giulia";
      } else {
        result = "David";
      }
    }

    return result ;
  }


  //1e. A method to check if the buy in and the base are set correctly
  public static boolean canPlay(double buyIn, double baseBet) {

    boolean gameAllowed;

    if ((buyIn > baseBet) && ((buyIn > 0 ) && (buyIn % baseBet == 0 ))) {
      gameAllowed = true;
    } else {
      gameAllowed = false;
      }
    return gameAllowed;
  }


  //1f. A method to simulate a game of Mexico
  public static void playMexico(double buyIn, double baseBet) {

      // We first check if the game can be played
      if (canPlay(buyIn, baseBet) == false ) {
      System.out.println("Insufficient funds. The game cannot be played.");
      return;

      //Since the game can be played, we then simulate a game between Giulia and David
      } else {
        String playerOne, playerTwo;
        playerOne = "Giulia";
        playerTwo = "David";

        // Both players start with the same amount of money equal to the buy in
        double moneyPlayerOne, moneyPlayerTwo;
        moneyPlayerOne = buyIn;
        moneyPlayerTwo = buyIn;

        int roundNumber = 1;

        // While loop until one of the players runs out of money
        while ((moneyPlayerOne > 0 ) && (moneyPlayerTwo > 0 )){

        System.out.println();
        System.out.println("Round " + roundNumber);
        System.out.println();

        int roundForPlayerOne, roundForPlayerTwo;
        roundForPlayerOne = playOneRound(playerOne);
        roundForPlayerTwo = playOneRound(playerTwo);


        // Depending on the winner of the round, the loser of the round loses money equal to the base bet
        // The winner of the round is then announced
        if (getWinner(roundForPlayerOne, roundForPlayerTwo).equals("Giulia")) {
          moneyPlayerTwo = moneyPlayerTwo - baseBet;
          System.out.println(getWinner(roundForPlayerOne, roundForPlayerTwo) + " is the winner of Round " + roundNumber );

        } else if (getWinner(roundForPlayerOne, roundForPlayerTwo).equals("Tie")) {
          System.out.println("There is a " + getWinner(roundForPlayerOne, roundForPlayerTwo) + " for Round " + roundNumber + ". The players have to roll again." );

        } else {
          moneyPlayerOne = moneyPlayerOne - baseBet;
          System.out.println(getWinner(roundForPlayerOne, roundForPlayerTwo) + " is the winner of Round " + roundNumber );
        }

        roundNumber++;
        }

        // The winner of the game is announced when the other player is out of money
        if (moneyPlayerOne == 0) {
          System.out.println();
          System.out.println("David is the winner of the game!");
        } else {
          System.out.println();
          System.out.println("Giulia is the winner of the game!");
        }


      }
  }



}
