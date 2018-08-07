package game;

public class Sara {
	
	private static int happiness;
	private static Cell saraCell;
	
	private final int HALLWAY = 9;
	
	public Sara (Cell cell) {
		System.out.println("Hi Matt!");
		happiness = 0;
		saraCell = cell;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public void addToHappiness(int i) {
		happiness += i;
	}
	
	public Cell getLocation() {
		return saraCell;
	}
	
	public void setLocation(Cell cell) {
		saraCell = cell;
	}
	
	public void addToCell(Cell cell) {
		cell.setOccupant(cell.SARA);
	}
	
	public Cell prepareSara(Cell cell) {
		
		Cell previousCell = null;
		
		if (reachable(cell)) {
			//North, East, South, West
			if (saraCell.getY() > cell.getY()) {
				previousCell = goNorth(cell);
			} else if (saraCell.getX() < cell.getX()) {
				previousCell = goEast(cell);
			} else if (saraCell.getY() < cell.getY()) {
				previousCell = goSouth(cell);
			} else if (saraCell.getX() > cell.getX()) {
				previousCell = goWest(cell);
			}
		}
		
		return previousCell;
	}
	
	public Cell goNorth(Cell cell) {
		
		Cell previousCell = null;
		
		if (look(cell)) {
			previousCell = saraCell;
			cell.calculateHappiness(this);
			cell.setOccupant(HALLWAY);
			saraCell = cell;
			addToCell(saraCell);
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
		
		return previousCell;
	}
	
	public Cell goEast(Cell cell) {
		
		Cell previousCell = null;
		
		if (look(cell)) {
			previousCell = saraCell;
			cell.calculateHappiness(this);
			cell.setOccupant(HALLWAY);
			saraCell = cell;
			addToCell(saraCell);
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
		
		return previousCell;
	}
	
	public Cell goSouth(Cell cell) {
		
		Cell previousCell = null;
		
		if (look(cell)) {
			previousCell = saraCell;
			cell.calculateHappiness(this);
			cell.setOccupant(HALLWAY);
			saraCell = cell;
			addToCell(saraCell);
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
		
		return previousCell;
	}
	
	public Cell goWest(Cell cell) {
		
		Cell previousCell = null;
		
		if (look(cell)) {
			previousCell = saraCell;
			cell.calculateHappiness(this);
			cell.setOccupant(HALLWAY);
			saraCell = cell;
			addToCell(saraCell);
		} else {
			System.out.println("Sara just ran into a wall! Ouch!");
		}
		
		return previousCell;
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
		if (cell.getX() == saraCell.getX() && cell.getY() == saraCell.getY()) {
			System.out.println("Sara doesn't like wasting time!");
		} else if (Math.abs(cell.getX() - saraCell.getX()) <= 1 && Math.abs(cell.getY() - saraCell.getY()) <= 0 || 
				Math.abs(cell.getX() - saraCell.getX()) <= 0 && Math.abs(cell.getY() - saraCell.getY()) <= 1 ) {
			isReachable = true;
		} else {
			System.out.println("Sara can't just teleport! (not yet...)");
		}
		return isReachable;
	}
}
