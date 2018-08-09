package game;

import java.util.*;

public class Map {
	
	public int mapSize;
	public int mapNum;
	private Cell[][] mapData;
	private List<Cell> frontierCells;
	private List<Cell> availableCells;
	private Random rand;
	private Sara sara;
	
	public Map(int startingDirection, int size) {
		rand = new Random();
		mapData = new Cell[size][size];
		mapSize = size;
		mapNum = 0;
		initMap();
	}
	
	private void initMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				mapData[i][j] = new Cell(i, j, mapSize);
				if (i == 0 && j == 0) {
					sara = new Sara(mapData[i][j]);
				}
			}
		}
		initRandomMap(0, 0);
	}
	
	public void initMap(int mapNum, int direction) {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				mapData[i][j] = new Cell(i, j, mapSize);
			}
		}
		initRandomMap(mapNum, direction);
	}
	
	private void initRandomMap(int mapNum, int direction) {
		mapData[0][0].createPath();
		frontierCells = new ArrayList<Cell>();
		addFrontier(mapData[0][0]);
		
		while (!frontierCells.isEmpty()) {
			
			Cell workingCell =
					frontierCells.remove(rand.nextInt(frontierCells.size()));
			
			getPathList(workingCell);
			
			if (availableCells.size() > 0) {
				createPath(availableCells.get(rand.nextInt
						(availableCells.size())), workingCell);
			}
			
			addFrontier(workingCell);
		}
		createObstacles(mapNum, direction);
	}
	
	private void addFrontier(Cell cell) {
		if (cell.getY() - 2 > -1) {
			if (!mapData[cell.getX()][cell.getY() - 2].isPath()
					&& !frontierCells.contains(
							mapData[cell.getX()][cell.getY() -2])) {
				frontierCells.add(mapData[cell.getX()][cell.getY() - 2]);
			}
		}
		if (cell.getX() + 2 < mapSize) {
			if (!mapData[cell.getX() + 2][cell.getY()].isPath()
					&& !frontierCells.contains(
							mapData[cell.getX() + 2][cell.getY()])) {
				frontierCells.add(mapData[cell.getX() + 2][cell.getY()]);
			}
		}
		if (cell.getY() + 2 < mapSize) {
			if (!mapData[cell.getX()][cell.getY() + 2].isPath()
					&& !frontierCells.contains(
							mapData[cell.getX()][cell.getY() + 2])) {
				frontierCells.add(mapData[cell.getX()][cell.getY() + 2]);
			}
		}
		if (cell.getX() - 2 > -1) {
			if (!mapData[cell.getX() - 2][cell.getY()].isPath()
					&& !frontierCells.contains(
							mapData[cell.getX() - 2][cell.getY()])) {
				frontierCells.add(mapData[cell.getX() - 2][cell.getY()]);
			}
		}
	}
	
	private void getPathList (Cell cell) {
			availableCells = new ArrayList<Cell>();
		if (cell.getY() - 2 > -1) {
			if (mapData[cell.getX()][cell.getY() - 2].isPath()) {
				availableCells.add(mapData[cell.getX()][cell.getY() - 2]);
			}
		}
		if (cell.getX() + 2 < mapSize) {
			if (mapData[cell.getX() + 2][cell.getY()].isPath()) {
				availableCells.add(mapData[cell.getX() + 2][cell.getY()]);
			}
		}
		if (cell.getY() + 2 < mapSize) {
			if (mapData[cell.getX()][cell.getY() + 2].isPath()) {
				availableCells.add(mapData[cell.getX()][cell.getY() + 2]);
			}
		}
		if (cell.getX() - 2 > -1) {
			if (mapData[cell.getX() - 2][cell.getY()].isPath()) {
				availableCells.add(mapData[cell.getX() - 2][cell.getY()]);
			}
		}
	}
	
	private void createPath(Cell oldCell, Cell newCell) {
		newCell.createPath();
		if (oldCell.getY() < newCell.getY()) {
			mapData[oldCell.getX()][oldCell.getY() + 1].createPath();
		} else if (oldCell.getX() > newCell.getX()) {
			mapData[oldCell.getX() - 1][oldCell.getY()].createPath();
		} else if (oldCell.getY() > newCell.getY()) {
			mapData[oldCell.getX()][oldCell.getY() - 1].createPath();
		} else {
			mapData[oldCell.getX() + 1][oldCell.getY()].createPath();
		}
	}
	
	private void createObstacles(int mapNum, int direction) {
		
		// Make an end for MVP
		if (mapNum > 5) {
			mapData[(mapSize - 1) / 2][(mapSize - 1) / 2].createEnd();
		}
		
		// TODO: MOVE THIS CODE
		if (mapNum == 0) {
			// TODO: Change this later. Add Sara to the map! Currently adding to top left, this is NOW changing!
			sara.addToCell(mapData[0][0]);
		} else {
			// TODO: This is the new implementation for random maps after Sara walks off the edge.
			// TODO: Change direction numbers to final variables
			
			Cell saraLocation = sara.getLocation();
			if (direction == 1) {
				// Moving from top of previous map to bottom of new map
				if (!mapData[saraLocation.getX()][mapSize - 1].isOccupied()) {
					sara.addToCell(mapData[saraLocation.getX()][mapSize - 1]);
					sara.setLocation(mapData[saraLocation.getX()][mapSize - 1]);
				} else {
					sara.addToCell(mapData[saraLocation.getX() + 1][mapSize - 1]);
					sara.setLocation(mapData[saraLocation.getX() + 1][mapSize - 1]);
				}
			} else if (direction == 2) {
				// Moving from right of previous map to left of new map
				if (!mapData[0][saraLocation.getY()].isOccupied()) {
					sara.addToCell(mapData[0][saraLocation.getY()]);
					sara.setLocation(mapData[0][saraLocation.getY()]);
				} else {
					sara.addToCell(mapData[0][saraLocation.getY() + 1]);
					sara.setLocation(mapData[0][saraLocation.getY() + 1]);
				}
			} else if (direction == 3) {
				// Moving from bottom of previous map to top of new map
				if (!mapData[saraLocation.getX()][0].isOccupied()) {
					sara.addToCell(mapData[saraLocation.getX()][0]);
					sara.setLocation(mapData[saraLocation.getX()][0]);
				} else {
					sara.addToCell(mapData[saraLocation.getX() + 1][0]);
					sara.setLocation(mapData[saraLocation.getX() + 1][0]);
				}
			} else {
				// Moving from left of previous map to right of new map
				if (!mapData[mapSize - 1][saraLocation.getY()].isOccupied()) {
					sara.addToCell(mapData[mapSize - 1][saraLocation.getY()]);
					sara.setLocation(mapData[mapSize - 1][saraLocation.getY()]);
				} else {
					sara.addToCell(mapData[mapSize - 1][saraLocation.getY() + 1]);
					sara.setLocation(mapData[mapSize - 1][saraLocation.getY() + 1]);
				}
			}
		}
		
		// TODO: MOVE THIS CODE
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (!mapData[i][j].isOccupied()) {
					if (mapData[i][j].isPath()) {
						mapData[i][j].createObstacle(rand);
					}
				}
			}
		}
	}
	
	public int getMapSize() {
		return mapSize;
	}
	
	public Cell[][] getMapData() {
		return mapData;
	}
	
	public Sara getSara() {
		return sara;
	}
	
	public void printMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j< mapSize; j++) {
				mapData[j][i].printCell();
			}
			System.out.println();
		}
	}
}
