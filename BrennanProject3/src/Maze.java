import java.util.ArrayList;
import java.util.Collections;

import edu.du.dudraw.DUDraw;

public class Maze {

	private enum CellValue {
		Wall, Open, Explored
	}; // enum value for cell value

	private int width, height; // width and height

	Cell[][] board; // 2D array of cells

	public Maze(int w, int h) {

		width = w; // width of board inputted by user
		height = h; // height of board inputted by user

		DUDraw.setCanvasSize(500, 500);
		DUDraw.setXscale(0, width);
		DUDraw.setYscale(0, height);

		board = new Cell[width][height]; // initializes board to be an array of cells
		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {

				board[i][j] = new Cell(i, j);
			}
		}

	}

	public void draw() {

		DUDraw.clear();
		DUDraw.enableDoubleBuffering();

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) { // nested for loop to set pen color of square based upon cell
														// value

				if (board[i][j].value == CellValue.Wall) { // checks if value is wall and sets to black

					DUDraw.setPenColor(DUDraw.BLACK);

				}

				if (board[i][j].value == CellValue.Open) { // checks if value is open and sets to white

					DUDraw.setPenColor(DUDraw.WHITE);

				}

				if (board[i][j].value == CellValue.Explored) { // checks if value is explored and sets to green

					DUDraw.setPenColor(DUDraw.GREEN);

				}

				DUDraw.filledSquare(0.5 + i, 0.5 + j, 0.5); // draws square

			}
		}

		DUDraw.show(); // shows board
		DUDraw.pause(10);

	}

	public void generateMaze() {

		DoublyLinkedStack<Cell> stack = new DoublyLinkedStack<>(); // creates and initializes stack

		ArrayList<Cell> neighbors; // creates neighbor variables

		Cell currentCell = board[1][1]; // sets current cell to cell (1,1)

		currentCell.value = CellValue.Open; // sets cell(1,1) to open

		stack.push(currentCell); // puts cell(1,1) on stack

		while (!stack.isEmpty()) {

			currentCell = stack.pop(); // sets current cell to popped value

			neighbors = new ArrayList<>(); // initializes ArrayList neighbors every time for new neighbors up to 4

			int r = currentCell.row; // r is current cell row value
			int c = currentCell.col; // c is current cell column value

			draw(); // calls draw every time to do animation

			if (r + 2 < height && (board[r + 2][c].value == CellValue.Wall)) { // checks 2 cells above current cell for
																				// neighbor is within board boundaries

				board[r + 2][c].value = CellValue.Open; // sets neighbor cell 2 above to open

				neighbors.add(board[r + 2][c]);// adds neighbor cell to array list

				board[r + 1][c].value = CellValue.Open; // sets cell in between to open
			}

			if (r - 2 >= 0 && (board[r - 2][c].value == CellValue.Wall)) { // checks 2 cells below current cell for
																			// neighbor is within board boundaries

				board[r - 2][c].value = CellValue.Open; // sets neighbor cell 2 below to open

				neighbors.add(board[r - 2][c]); // adds neighbor cell to array list

				board[r - 1][c].value = CellValue.Open; // sets cell in between to open
			}

			if (c + 2 < width && (board[r][c + 2].value == CellValue.Wall)) { // checks 2 cells right of current cell
																				// for neighbor is within board
																				// boundaries

				board[r][c + 2].value = CellValue.Open; // sets neighbor cell 2 to the right to open

				neighbors.add(board[r][c + 2]); // adds neighbor cell to array list

				board[r][c + 1].value = CellValue.Open; // sets cell in between to open
			}

			if (c - 2 >= 0 && (board[r][c - 2].value == CellValue.Wall)) { // checks 2 cells left of current cell for
																			// neighbor is within board boundaries

				board[r][c - 2].value = CellValue.Open; // sets neighbor cell 2 to the left to open

				neighbors.add(board[r][c - 2]); // adds neighbor cell to array list

				board[r][c - 1].value = CellValue.Open; // sets cell in between to open
			}

			Collections.shuffle(neighbors);

			for (Cell cell : neighbors) {

				stack.push(cell); // puts all cells from neighbors into stack

			}
		}
	}

	public void solveMaze() {

		DoublyLinkedQueue<Cell> q = new DoublyLinkedQueue<>(); // cretes queue list

		Cell currentCell = board[1][1]; // initializes current cell to cell(1,1)

		Cell goal = board[height - 2][width - 2]; // initializes goal cell to top right corner

		currentCell.value = CellValue.Explored; // sets cell(1,1) to explired

		q.enqueue(currentCell); // queues cell(1,1)

		while (!q.isEmpty()) {

			currentCell = q.dequeue(); // dequeues current cell

			draw(); // draws for animation loop

			if (currentCell.row == goal.row && currentCell.col == goal.col) { // if found top right corner returns and
																				// finishes

				return;

			}

			int r = currentCell.row; // to condense code r is current cell row value
			int c = currentCell.col; // to condense code c is current cell column value

			if (r + 1 < height && (board[r + 1][c].value == CellValue.Open)) { // checks 1 cell above current cell is
																				// within board boundaries and
																				// unexplored

				board[r + 1][c].value = CellValue.Explored; // sets above cell to explored

				q.enqueue(board[r + 1][c]); // puts above cell in queue list
			}

			if (r - 1 >= 0 && (board[r - 1][c].value == CellValue.Open)) { // checks 1 cell below current cell is within
																			// board boundaries and unexplored

				board[r - 1][c].value = CellValue.Explored; // sets below cell to explored

				q.enqueue(board[r - 1][c]); // puts below cell in queue list
			}

			if (c + 1 < width && (board[r][c + 1].value == CellValue.Open)) { // checks 1 cell right of current cell is
																				// within board boundaries and
																				// unexplored

				board[r][c + 1].value = CellValue.Explored; // sets right cell to explored

				q.enqueue(board[r][c + 1]); // puts right cell in queue list
			}

			if (c - 1 >= 0 && (board[r][c - 1].value == CellValue.Open)) { // checks 1 cell left of current cell is
																			// within board boundaries and unexplored

				board[r][c - 1].value = CellValue.Explored; // sets left cell to explored

				q.enqueue(board[r][c - 1]); // puts left cell in queue list
			}

			System.out.println(goal.toString());

		}

	}

	private class Cell {

		private CellValue value;; // cell value enum

		private int row, col; // row and column values

		public Cell(int r, int c) { // parameters of row and column

			value = CellValue.Wall; // initializes all cells to Black or wall

			row = r; // initializes row

			col = c; // initializes column
		}

		public String toString() {

			return value.toString(); // returns cell value

		}

	}
}
