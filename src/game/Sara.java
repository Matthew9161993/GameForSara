package game;

public class Sara {
	
	private static int happiness;
	private static int xPos;
	private static int yPos;
	
	public Sara () {
		System.out.println("Hi Matt!");
		happiness = 0;
		xPos = 0;
		yPos = 0;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public void addToCell(Cell cell) {
		cell.setOccupant(cell.SARA);
	}
	
	public void prepareSara(Cell cell) {
		
		if (reachable(cell)) {
			//North, East, South, West
			if (yPos > cell.getY()) {
				goNorth(cell);
			} else if (xPos < cell.getX()) {
				goEast(cell);
			} else if (yPos < cell.getY()) {
				goSouth(cell);
			} else if (xPos > cell.getX()) {
				goWest(cell);
			}
		}
	}
	
	public void goNorth(Cell cell) {
		if (look(cell)) {
			yPos--;
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
	}
	
	public void goEast(Cell cell) {
		if (look(cell)) {
			xPos++;
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
	}
	
	public void goSouth(Cell cell) {
		if (look(cell)) {
			yPos++;
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
	}
	
	public void goWest(Cell cell) {
		if (look(cell)) {
			xPos--;
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
	}
	
	// Checks if Sara will walk into a wall!
	public boolean look(Cell cell) {
		if (cell.isPath()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Will need to check if hallway is adjacent as well....
	public boolean reachable(Cell cell) {
		boolean isReachable = false;
		if (cell.getX() == getX() && cell.getY() == getY()) {
			System.out.println("Sara doesn't like wasting time!");
		} else if (Math.abs(cell.getX() - getX()) <= 1 && Math.abs(cell.getY() - getY()) <= 1) {
			isReachable = true;
		} else {
			System.out.println("Sara can't just teleport! (not yet...)");
		}
		return isReachable;
	}
}
