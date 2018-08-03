package game;

public class Main {
	public static void main(String args[]) {
		System.out.println("Hi Sara!");
		Map theGrandWalk = new Map(0, 25);
		// theGrandWalk.printMap();
		
		// First time making a window!
		// TODO: Work more with Swing
		Window firstWindow = new Window(theGrandWalk);
		firstWindow.setupScene();
	}
}
