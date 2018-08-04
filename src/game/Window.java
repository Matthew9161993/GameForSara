package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Window {
	
	public JFrame frame;
	
	public JLabel scoreBox;
	
	public Sara sara;
	
	public Map map;
	
//	public buttonPresser controller;
	
	public Icon tileIcon;
	
	public Icon wallIcon;
	
	public Icon puppyIcon;
	
	public Icon babyIcon;
	
	public Icon snakeIcon;
	
	public Icon SaraIcon;
	
	private final int SARA = 8;
	private final int HALLWAY = 9;
	private final int PUPPY = 10;
	private final int BABY = 11;
	private final int SNAKE = 12;
	
	public Window(Map map) {
		frame = new JFrame("Sara Takes a Walk!");
		tileIcon = new ImageIcon("tile.png");
		wallIcon = new ImageIcon("wall.png");
		puppyIcon = new ImageIcon("puppy.png");
		babyIcon = new ImageIcon("babby.png");
		snakeIcon = new ImageIcon("snake.png");
		SaraIcon = new ImageIcon("Sara.png");
		sara = map.getSara();
		scoreBox = new JLabel("Happiness: " + sara.getHappiness());
//		controller = new buttonPresser(null, this);
		this.map = map;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Icon getIcon() {
		return tileIcon;
	}
	
	public void setupScene() {
		frame.setLayout(null);
		frame.setSize(1200, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawButtonMap();
		scoreBox.setBounds(1000, 200 , 100, 50);
		frame.add(scoreBox);
		frame.addKeyListener(new buttonPresser(null, this));
		frame.setVisible(true);
		frame.setFocusable(true);
	}
	
	public void drawTile(Cell cell, Icon icon) {
		// Create the button first
		cell.makeButton(icon);
		// Add the listener next
		cell.getButton().addActionListener(new buttonPresser(cell, this));
		getFrame().add(cell.getButton());
	}
	
	public void drawButtonMap() {
		Cell[][] buttonMapCells = map.getMapData();
		for (int i = 0; i < map.getMapSize(); i++) {
			for (int j = 0; j < map.getMapSize(); j++) {
				if (buttonMapCells[i][j].isPath()) {
					switch(buttonMapCells[i][j].getOccupant()) {
					
						case HALLWAY : drawTile(buttonMapCells[i][j], tileIcon);
						break;
						case PUPPY : drawTile(buttonMapCells[i][j], puppyIcon);
						break;
						case BABY : drawTile(buttonMapCells[i][j], babyIcon);
						break;
						case SNAKE : drawTile(buttonMapCells[i][j], snakeIcon);
						break;
						case SARA : drawTile(buttonMapCells[i][j], SaraIcon);
					}
				} else {
					drawTile(buttonMapCells[i][j], wallIcon);
				}
			}
		}
	}
}

class buttonPresser implements ActionListener, KeyListener {

    private Cell cell;
    private Sara sara;
    private Window window;

    public buttonPresser(Cell cell, Window window){
        super();
        this.cell = cell;
        this.sara = window.sara;
        this.window = window;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		Cell previousCell = sara.prepareSara(cell);
		if (previousCell != null) {
			update(previousCell);
		}
	}
	
	public void update(Cell cell) {
		cell.getButton().setIcon(window.tileIcon);
		sara.getLocation().getButton().setIcon(window.SaraIcon);
		window.scoreBox.setText("Happiness: " + sara.getHappiness());
		window.frame.requestFocusInWindow();
	}
	@Override
	public void keyPressed(KeyEvent key) {
		// do nothing here
		
	}
	@Override
	public void keyReleased(KeyEvent key) {
		// adding arrow key controls and wasd for the true gamers
		int keyCode = key.getKeyCode();
		
		Cell saraCell = sara.getLocation();
		Cell previousCell = null;
		
		switch (keyCode) {
			case KeyEvent.VK_UP:
				
				if (saraCell.getY() == 0) {
					// Sara is trying to move off the top of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell north of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() - 1]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_W:
				
				if (saraCell.getY() == 0) {
					// Sara is trying to move off the top of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell north of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() - 1]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_DOWN:
				
				if (saraCell.getY() == window.map.mapSize) {
					// Sara is trying to move off the bottom of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell south of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() + 1]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_S:
				
				if (saraCell.getY() == window.map.mapSize) {
					// Sara is trying to move off the bottom of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell south of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() + 1]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_LEFT:
				
				if (saraCell.getX() == 0) {
					// Sara is trying to move off the left of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell west of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() - 1][saraCell.getY()]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_A:
				
				if (saraCell.getX() == 0) {
					// Sara is trying to move off the left of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell west of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() - 1][saraCell.getY()]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_RIGHT:
				
				if (saraCell.getX() == window.map.mapSize) {
					// Sara is trying to move off the right of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell east of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() + 1][saraCell.getY()]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_D:
				
				if (saraCell.getX() == window.map.mapSize) {
					// Sara is trying to move off the right of the map
					System.out.println("Living on the edge!");
				} else {
					// Get the cell east of sara to move into
					previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() + 1][saraCell.getY()]);
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent key) {
		// also do nothing here
		
	}
	
}


