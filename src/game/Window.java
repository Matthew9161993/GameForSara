package game;

import javax.swing.*;
import javax.swing.border.Border;

public class Window {
	
	public JFrame frame;
	
	public Icon tileIcon;
	
	public Icon wallIcon;

	public JButton tileButton;
	
	public JButton wallButton;
	
	private final int BUTTONSIZE = 35;
	
	public Window() {
		frame = new JFrame("Wall and Tile");
		tileIcon = new ImageIcon("tile.png");
		wallIcon = new ImageIcon("wall.png");
		tileButton = new JButton(tileIcon);
		wallButton = new JButton(wallIcon);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Icon getIcon() {
		return tileIcon;
	}
	
	public JButton getTile() {
		return tileButton;
	}
	
	public JButton getWall() {
		return wallButton;
	}
	
	public void setupScene(Map map) {
		frame.setLayout(null);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawButtonMap(map);
		frame.setVisible(true);
	}
	
	public void drawTile(int x, int y, Icon icon) {
		JButton tileFactory = new JButton(icon);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		tileFactory.setBorder(emptyBorder);
		tileFactory.setBounds(20 + BUTTONSIZE * x, 20 + BUTTONSIZE * y,
				BUTTONSIZE, BUTTONSIZE);
		getFrame().add(tileFactory);
	}
	
	public void drawButtonMap(Map map) {
		Cell[][] buttonMapCells = map.getMapData();
		for (int i = 0; i < map.getMapSize(); i++) {
			for (int j = 0; j < map.getMapSize(); j++) {
				if (buttonMapCells[i][j].isPath()) {
					drawTile(i, j, tileIcon);
				} else {
					drawTile(i, j, wallIcon);
				}
			}
		}
	}
}