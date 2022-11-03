
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze m = new Maze(31, 31);
		m.draw();
		m.generateMaze();
		m.solveMaze();
	}

}