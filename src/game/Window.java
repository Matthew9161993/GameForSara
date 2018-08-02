package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Window {
	
	public JFrame frame;
	
	public Sara sara;
	
	public Icon tileIcon;
	
	public Icon wallIcon;
	
	public Icon puppyIcon;
	
	public Icon babyIcon;
	
	public Icon snakeIcon;
	
	public Icon SaraIcon;
	
	private buttonPresser pressMe;
	
	private final int BUTTONSIZE = 35;
	
	private final int SARA = 8;
	private final int HALLWAY = 9;
	private final int PUPPY = 10;
	private final int BABY = 11;
	private final int SNAKE = 12;
	
	public Window(Sara Sara) {
		frame = new JFrame("Wall and Tile");
		tileIcon = new ImageIcon("tile.png");
		wallIcon = new ImageIcon("wall.png");
		puppyIcon = new ImageIcon("puppy.png");
		babyIcon = new ImageIcon("babby.png");
		snakeIcon = new ImageIcon("snake.png");
		SaraIcon = new ImageIcon("Sara.png");
		sara = Sara;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Icon getIcon() {
		return tileIcon;
	}
	
	public void setupScene(Map map) {
		frame.setLayout(null);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawButtonMap(map);
		frame.setVisible(true);
	}
	
	public void drawTile(int x, int y, Icon icon, Map map) {
		JButton tileFactory = new JButton(icon);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		tileFactory.setBorder(emptyBorder);
		tileFactory.setBounds(20 + BUTTONSIZE * x, 20 + BUTTONSIZE * y,
				BUTTONSIZE, BUTTONSIZE);
		Cell currentCell = map.getMapData()[x][y];
		tileFactory.addActionListener(new buttonPresser(currentCell, sara));
		getFrame().add(tileFactory);
	}
	
	public void drawButtonMap(Map map) {
		Cell[][] buttonMapCells = map.getMapData();
		for (int i = 0; i < map.getMapSize(); i++) {
			for (int j = 0; j < map.getMapSize(); j++) {
				if (buttonMapCells[i][j].isPath()) {
					switch(buttonMapCells[i][j].getOccupant()) {
					
						case HALLWAY : drawTile(i, j, tileIcon, map);
						break;
						case PUPPY : drawTile(i, j, puppyIcon, map);
						break;
						case BABY : drawTile(i, j, babyIcon, map);
						break;
						case SNAKE : drawTile(i, j, snakeIcon, map);
						break;
						case SARA : drawTile(i, j, SaraIcon, map);
					}
				} else {
					drawTile(i, j, wallIcon, map);
				}
			}
		}
	}
}

class buttonPresser implements ActionListener {

    private Cell cell;
    private Sara sara;

    public buttonPresser(Cell cell, Sara sara){
        super();
        this.cell = cell;
        this.sara = sara;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		sara.prepareSara(cell);
	}
	
}


