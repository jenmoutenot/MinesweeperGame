// mineSweeper.java (class GameBoard (part of the MVC model))
// Copyright Dave Binkley 2018

/**
 * This is my code! It's goal is to serve as a game board class
 * CS 312 - Assignment 8
 * @author Jen Moutenot
 * @version 1.0 11/28/18
 */

public class GameBoard
{
  public final static int BOARD_SIZE = 3;
  public final static int NUMBER_OF_MINES = 2;
  protected Block grid[][] = new Block[BOARD_SIZE][BOARD_SIZE];
  protected View view;
	
  protected void cheat()
  {
    for(int r=0; r<BOARD_SIZE; r++)        
    {
      String S = "";
      for(int c=0; c<BOARD_SIZE; c++)
        S += grid[r][c].cheat();
      System.out.println(S);
    }
  }

  public GameBoard(View v) 
  { 
    view = v; 
    
    int mineCount = NUMBER_OF_MINES;

    for(int r=0; r<BOARD_SIZE; r++)             
    {
      for(int c=0; c<BOARD_SIZE; c++)
      {
        if (mineCount-- > 0)
        {
          grid[r][c] = new MineBlock();
        }
        else
        {
          grid[r][c] = new NumberBlock();
        }
      }
    }
    
    for(int r=0; r<BOARD_SIZE; r++)             
    {
      for(int c=0; c<BOARD_SIZE; c++)
      {
        int rRand = (int) (Math.random() * BOARD_SIZE);
        int cRand = (int) (Math.random() * BOARD_SIZE);
        Block temp = grid[r][c];               
        grid[r][c] = grid[rRand][cRand];
        grid[rRand][cRand] = temp;
      }
    }
    
    for(int r=0; r<BOARD_SIZE; r++)        // fill in mine counters
      for(int c=0; c<BOARD_SIZE; c++)
        grid[r][c].tellNeighborsAboutMine(this, r, c);

    cheat();  // for testing ... and amazing your friends!
  }
  
  public void guessBlockIsSafe(int r, int c)
  {
    grid[r][c].guessSafe();
  }
  
  public void incrementCountForSurroundingBlocks(int r, int c)
  {
    for(int rNew = r-1; rNew <= r+1; rNew++)
    {
      for(int cNew = c-1; cNew <= c+1; cNew++)
      {
        if (onBoard(rNew, cNew) && !((r==rNew) && (c==cNew)))
        {
          grid[rNew][cNew].incrementAdjacentMineCount();
        }
      }
    }
  }  
  
  public boolean markBlockAsMine(int r, int c)
  {
    grid[r][c].markAsMine();
    return true;
  }
  
  public boolean minesAllFound()
  {
    for (int r = 0; r < BOARD_SIZE; r++)
      for(int c = 0; c < BOARD_SIZE; c++)
        if(!grid[r][c].correctlyGuessed())
          return false;
    return true;
  }
  
  protected boolean onBoard(int r, int c)
  {
    return (r>= 0 && r < BOARD_SIZE && c>= 0 && c< BOARD_SIZE);
  }
  
  public String displayAs(int r, int c)
  {
    return grid[r][c].displayAs();
  }
}
