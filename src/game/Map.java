package game;

import java.util.*;

public class Map {
	
	public int mapSize;
	private Cell[][] mapData;
	private List<Cell> frontierCells;
	private List<Cell> availableCells;
	private Random rand;
	
	public Map(int startingDirection, int size) {
		rand = new Random();
		mapData = new Cell[size][size];
		mapSize = size;
		initMap();
	}
	
	private void initMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j< mapSize; j++) {
				mapData[i][j] = new Cell(i, j, mapSize);
			}
		}
		initRandomMap();
	}
	
	private void initRandomMap() {
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
		createObstacles();
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
	
	private void createObstacles() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (mapData[i][j].isPath()) {
					mapData[i][j].createObstacle(rand);
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
	
	public void printMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j< mapSize; j++) {
				mapData[j][i].printCell();
			}
			System.out.println();
		}
	}
}
