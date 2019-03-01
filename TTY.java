// mineSweeper.java (TTY main)
// Copyright Dave Binkley 2018

/**
 * This is my code! It's goal is to serve as a tty class
 * CS 312 - Assignment 8
 * @author Jen Moutenot
 * @version 1.0 11/28/18
 */

public class TTY
{
  static public void main(String[] args)
  {
    int r = 0;
    int c = 0;
    char choice = 'X';
    View v = new TTYView(); 
    GameBoard b = new GameBoard(v);
    v.setModel(b);
  
    v.initialDisplay();
   
    // the 'controller' (this code) gets input "events" and updates the
    // model (which requests view updates as required)
    do
    {
      System.out.println("enter guess: ");
      try
      {
        choice = (char) System.in.read(); // [ rather unforgiving IO ]
        r = (char) System.in.read() - '0';
        c = (char) System.in.read() - '0';
        System.in.read(); // return
      }
      catch(Exception e)
      {
      }
  
      System.out.println("r = " + r + " c = " + c + " choice " + choice);
      if (choice == 'M')
      {
        b.markBlockAsMine(r,c);
        v.update(r, choice);
      }
      else
      {
        b.guessBlockIsSafe(r,c);
        v.update(r, choice);
      }
    } while (!b.minesAllFound());
  
    System.out.println("yipee!!  you found them all");
  }
}
