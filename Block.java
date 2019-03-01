// mineSweeper.java (class Block (part of the MVC model))
// Copyright Dave Binkley 2018

/**
 * This is my code! It's goal is to serve as a block class
 * CS 312 - Assignment 8
 * @author Jen Moutenot
 * @version 1.0 11/28/18
 */

abstract class Block
{
  protected boolean markedAsMine;
	
  public Block() 
  {
    markedAsMine = false;
  }
  
  public void markAsMine() 
  {
    markedAsMine = true;
  }
	
  public abstract String cheat();
  public abstract void guessSafe();
  public abstract boolean correctlyGuessed();
  public abstract void incrementAdjacentMineCount();
  public abstract void tellNeighborsAboutMine(GameBoard gb, int r, int c);
  public abstract String displayAs();
    
}

class MineBlock extends Block   // [ no instance variables ]
{
  public MineBlock()
  {
	  
  }
	
  public void guessSafe()
  {
    System.out.println("BOOM!");
    System.exit(0);
  }
  
  public void incrementAdjacentMineCount() 
  { 
	  
  }
	
  public void tellNeighborsAboutMine(GameBoard gb, int r, int c)
  {
    gb.incrementCountForSurroundingBlocks(r, c);
  }
	
  public boolean correctlyGuessed()
  {
    return markedAsMine;
  }
	
  public String displayAs()
  {
    if (markedAsMine)
      return "M";
    else
      return ".";
  }
	
  public String cheat() { return "M"; }
  
}

class NumberBlock extends Block
{
  protected int adjacentMineCount;
  protected boolean exposed;
	
  public NumberBlock()
  {
    adjacentMineCount = 0;
    exposed = false;
  }
  
  public void tellNeighborsAboutMine(GameBoard gb, int r, int c)
  {
	  
  }
	
  
  public void guessSafe()
  {
    exposed = true;
    markedAsMine = false;
  }
	
  public void incrementAdjacentMineCount()
  {
    adjacentMineCount++;
  }
  
	
  public boolean correctlyGuessed()
  {
    return !markedAsMine;
  }
	
  public String displayAs()
  {
    if (markedAsMine)
      return "M";
    else if (exposed)
      return ""+"0123456789".charAt(adjacentMineCount);
    else
      return ".";
  }
    
  public String cheat() { return ""+"0123456789".charAt(adjacentMineCount); }
  
}
