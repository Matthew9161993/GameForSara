package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Window {
	
	public JFrame frame;
	
	public Sara sara;
	
	public Map map;
	
	public Icon tileIcon;
	
	public Icon wallIcon;
	
	public Icon puppyIcon;
	
	public Icon babyIcon;
	
	public Icon snakeIcon;
	
	public Icon SaraIcon;
	
	private buttonPresser pressMe;
	
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
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawButtonMap();
		frame.setVisible(true);
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

class buttonPresser implements ActionListener {

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
			redrawButton(previousCell);
		}
	}
	
	public void redrawButton(Cell cell) {
		cell.getButton().setIcon(window.tileIcon);
		sara.getLocation().getButton().setIcon(window.SaraIcon);
	}
	
}


