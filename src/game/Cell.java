package game;

import java.util.Random;

public class Cell {

	private int xPos;
	private int yPos;
	private int edge;
	private boolean isPath;
	private boolean isOccupied;
	private int occupant;
	
	//Direction values
	private final int NOT_AN_EDGE = -1;
	private final int NORTH = 0;
	private final int NORTHEAST = 1;
	private final int EAST = 2;
	private final int SOUTHEAST = 3;
	private final int SOUTH = 4;
	private final int SOUTHWEST = 5;
	private final int WEST = 6;
	private final int NORTHWEST = 7;
	
	private final int SARA = 8;
	private final int HALLWAY = 9;
	private final int PUPPY = 10;
	private final int BABY = 11;
	private final int SNAKE = 12;
	
	public Cell(int i, int j, int mazeSize) {
		xPos = i;
		yPos = j;
		isPath = false;
		isOccupied = true;
		occupant = -1;
		
		if (xPos == 0) {
			if (yPos == 0) {
				edge = NORTHWEST;
			} else if (yPos == mazeSize - 1) {
				edge = SOUTHWEST;
			} else {
				edge = WEST;
			}
		} else if (xPos == mazeSize - 1) {
			if (yPos == 0) {
				edge = NORTHEAST;
			} else if (yPos == mazeSize - 1) {
				edge = SOUTHEAST;
			} else {
				edge = EAST;
			}
		} else if (yPos == 0) {
			edge = NORTH;
		} else if (yPos == mazeSize - 1) {
			edge = SOUTH;
		} else {
			edge = NOT_AN_EDGE;
		}
		
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public int getEdge() {
		return edge;
	}
	
	public boolean isPath() {
		return isPath;
	}
	
	public void createPath() {
		if (isPath) {
			System.out.println("You shouldn't be here.");
		}
		isPath = true;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public int getOccupant() {
		return occupant;
	}
	
	public void setOccupant(int i) {
		occupant = i;
	}
	
	// Current forecast:
	// 70% hallways
	// 15% puppies
	// 10% babies
	// 5% snakes
	public void createObstacle (Random r) {
		int diceRoll = r.nextInt(100);
		if (diceRoll < 70) {
			setOccupant(HALLWAY);
			isOccupied = false;
		} else if (diceRoll >= 70 && diceRoll < 85) {
			setOccupant(PUPPY);
		} else if (diceRoll >= 85 && diceRoll < 95) {
			setOccupant(BABY);
		} else {
			setOccupant(SNAKE);
		}
	}
	
	public void printCell() {
		if (isPath) {
			System.out.print("O");
		} else {
			System.out.print("X");
		}
	}
}
