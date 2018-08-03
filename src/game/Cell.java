package game;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Cell {

	private int xPos;
	private int yPos;
	private int edge;
	private boolean isPath;
	private boolean isOccupied;
	private int occupant;
	private JButton button;
	
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
	
	public final int SARA = 8;
	public final int HALLWAY = 9;
	public final int PUPPY = 10;
	public final int BABY = 11;
	public final int SNAKE = 12;
	
	public final int PUPPY_SCORE = 5;
	public final int BABY_SCORE = 10;
	public final int SNAKE_SCORE = -25;
	
	private final int BUTTONSIZE = 35;
	
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
	
	public JButton getButton() {
		return button;
	}
	
	public void createPath() {
		if (isPath) {
			System.out.println("You shouldn't be here.");
		}
		isPath = true;
		isOccupied = false;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public int getOccupant() {
		return occupant;
	}
	
	public void setOccupant(int i) {
		occupant = i;
		if (occupant == HALLWAY) {
			isOccupied = false;
		} else {
			isOccupied = true;
		}
	}
	
	public void makeButton(Icon icon) {
		button = new JButton(icon);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button.setBorder(emptyBorder);
		button.setBounds(20 + BUTTONSIZE * getX(), 20 + BUTTONSIZE * getY(),
				BUTTONSIZE, BUTTONSIZE);
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
	
	// Scoring time
	// puppies : 5 pts
	// babies : 10 pts
	// snakes : -25 pts
	
	public void calculateHappiness(Sara sara) {
		switch (getOccupant()) {
			case (PUPPY) : sara.addToHappiness(PUPPY_SCORE);
			break;
			case (BABY) : sara.addToHappiness(BABY_SCORE);
			break;
			case (SNAKE) :sara.addToHappiness(SNAKE_SCORE);
			break;
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
