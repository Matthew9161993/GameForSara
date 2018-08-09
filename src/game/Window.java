package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class Window {
	
	public JFrame frame;
	
	public JLabel scoreBox;
	
	public Sara sara;
	
	public Map map;
	
	// Refactor to create an Icon array, clean up the mess
	
	public Icon tileIcon;
	
	public Icon wallIcon;
	
	public Icon puppyIcon;
	
	public Icon babyIcon;
	
	public Icon snakeIcon;
	
	public Icon SaraIcon;
	
	public Icon finishIcon;
	
	private final int SARA = 8;
	private final int HALLWAY = 9;
	private final int PUPPY = 10;
	private final int BABY = 11;
	private final int SNAKE = 12;
	private final int END = 13;
	
	public Window(Map map) {
		frame = new JFrame("Sara Takes a Walk!");
		tileIcon = new ImageIcon("tile.png");
		wallIcon = new ImageIcon("wall.png");
		puppyIcon = new ImageIcon("puppy.png");
		babyIcon = new ImageIcon("babby.png");
		snakeIcon = new ImageIcon("snake.png");
		SaraIcon = new ImageIcon("Sara.png");
		finishIcon = new ImageIcon("end.png");
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
	
	public void setupInitialScene() {
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
						break;
						case END : drawTile(buttonMapCells[i][j], finishIcon);
						break;
					}
				} else {
					drawTile(buttonMapCells[i][j], wallIcon);
				}
			}
		}
	}
	
	public void writeEndDialogue() {
		while (true) {
			int endDialogue = JOptionPane.showConfirmDialog(this.frame, "You made it!\n"
	                + "Sara's happiness is: " + sara.getHappiness()
	                + "\nCongratulations!",
	                "A winner is you!",
	                JOptionPane.DEFAULT_OPTION);
	
			if (endDialogue == 0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
		Cell previousCell;
		try {
			previousCell = sara.prepareSara(cell);
			if (previousCell != null) {
				update(previousCell);
			} else {
				window.frame.requestFocusInWindow();
			}
		} catch (EndException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void update(Cell cell) {
		cell.getButton().setIcon(window.tileIcon);
		cell.setOccupant(cell.HALLWAY);
		sara.getLocation().getButton().setIcon(window.SaraIcon);
		window.scoreBox.setText("Happiness: " + sara.getHappiness());
		window.frame.requestFocusInWindow();
	}
	
	public void updateAll(int direction) {
		window.map.mapNum++;
		window.frame.getContentPane().removeAll();
		window.map.initMap(window.map.mapNum, direction);
		window.drawButtonMap();
		window.frame.add(window.scoreBox);
		window.frame.repaint();
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
		
		// TODO: Clean up the "magic numbers"
		
		switch (keyCode) {
			case KeyEvent.VK_UP:
				try {
					if (saraCell.getY() == 0) {
						// Sara is trying to move off the top of the map
						updateAll(1);
					} else {
						// Get the cell north of sara to move into
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() - 1]);
					}
					if (previousCell != null) {
						update(previousCell);
					}
				} catch (EndException e) {
					window.writeEndDialogue();
				}
				
				break;
			case KeyEvent.VK_W:
				
				if (saraCell.getY() == 0) {
					// Sara is trying to move off the top of the map
					updateAll(1);
				} else {
					// Get the cell north of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() - 1]);
					} catch (EndException e) {
						window.writeEndDialogue();

					}
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_DOWN:
				
				if (saraCell.getY() == window.map.mapSize - 1) {
					// Sara is trying to move off the bottom of the map
					updateAll(3);
				} else {
					// Get the cell south of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() + 1]);
					} catch (EndException e) {
						window.writeEndDialogue();
					}
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_S:
				
				if (saraCell.getY() == window.map.mapSize - 1) {
					// Sara is trying to move off the bottom of the map
					updateAll(3);
				} else {
					// Get the cell south of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX()][saraCell.getY() + 1]);
					} catch (EndException e) {
						window.writeEndDialogue();
					}
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_LEFT:
				
				if (saraCell.getX() == 0) {
					// Sara is trying to move off the left of the map
					updateAll(4);
				} else {
					// Get the cell west of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() - 1][saraCell.getY()]);
					} catch (EndException e) {
						window.writeEndDialogue();
					}
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_A:
				
				if (saraCell.getX() == 0) {
					// Sara is trying to move off the left of the map
					updateAll(4);
				} else {
					// Get the cell west of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() - 1][saraCell.getY()]);
					} catch (EndException e) {
						window.writeEndDialogue();
					}
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_RIGHT:
				
				if (saraCell.getX() == window.map.mapSize - 1) {
					// Sara is trying to move off the right of the map
					updateAll(2);
				} else {
					// Get the cell east of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() + 1][saraCell.getY()]);
					} catch (EndException e) {
						window.writeEndDialogue();
					}
				}
				if (previousCell != null) {
					update(previousCell);
				}
				
				break;
			case KeyEvent.VK_D:
				
				if (saraCell.getX() == window.map.mapSize - 1) {
					// Sara is trying to move off the right of the map
					updateAll(2);
				} else {
					// Get the cell east of sara to move into
					try {
						previousCell = sara.prepareSara(window.map.getMapData()[saraCell.getX() + 1][saraCell.getY()]);
					} catch (EndException e) {
						window.writeEndDialogue();
					}
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


