package game;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		System.out.println("Hi Sara!");
		Sara mySara = new Sara();
		Map theGrandWalk = new Map(0, 25, mySara);
		// theGrandWalk.printMap();
		
		// First time making a window!
		// TODO: Work more with Swing
		Window firstWindow = new Window(mySara);
		firstWindow.setupScene(theGrandWalk);
	}
}
