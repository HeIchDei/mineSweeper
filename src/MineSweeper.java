import java.util.Scanner;
import java.util.Random;

public class MineSweeper {

	String[][] board;
	String[][] revealedBoard;
	int numOfMines;
	int numOfMaxMines;
	Random rand = new Random();

	boolean cheat = true;

	MineSweeper(int row, int col) {
		this.board = new String[row][col];
		this.revealedBoard = new String[row][col];
		this.numOfMaxMines = (row * col) / 4;
		this.numOfMines = 0;
	}

	void run() {
		createBoard();
		cheat();
		System.out.println("===========================");
		System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz!");
		printBoard();
		game();
	}

	void game() {
		Scanner inp = new Scanner(System.in);
		while (true) {
			System.out.println("===========================");
			System.out.print("Satır Giriniz: ");
			int row = inp.nextInt() - 1;
			System.out.print("Sütun Giriniz: ");
			int col = inp.nextInt() - 1;
			this.board[row][col] = this.revealedBoard[row][col];

			if (this.board[row][col].equals("*")) {
				System.out.println("Game Over!!!");
				cheat();
				break;
			}

			//System.out.println(winCheck());
			if(winCheck()){
				System.out.println("Oyunu Kazandınız!");
				break;
			}

			printBoard();


		}
	}

	boolean winCheck(){
		for (int i = 0; i < revealedBoard.length; i++) {
			for (int j = 0; j < revealedBoard[i].length; j++) {
				if(!this.board[i][j].equals(this.revealedBoard[i][j]) && !this.revealedBoard[i][j].equals("*")){
					return false;
				}
			}
		}
		return true;
	}

	void cheat() {
		if (this.cheat) {
			System.out.println("Mayinlarin Konumu");
			showAnswer();
		}
	}

	void createBoard() {
		fillBoards();
		createMines();
		createNumbers();
	}

	//fills board with "-"
	void fillBoards() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = "-";
				this.revealedBoard[i][j] = "0";
			}
		}
	}

	// creates mines and places them to the revealedBoard
	void createMines() {
		while (numOfMaxMines != numOfMines) {
			int randomRow = rand.nextInt(this.revealedBoard.length);
			int randomCol = rand.nextInt(this.revealedBoard[0].length);
			if (!this.revealedBoard[randomRow][randomCol].equals("*")) {
				this.revealedBoard[randomRow][randomCol] = "*";
				numOfMines++;
			}
		}
	}

	void createNumbers() {
		for (int i = 0; i < revealedBoard.length; i++) {
			for (int j = 0; j < revealedBoard[i].length; j++) {
				if (!revealedBoard[i][j].equals("*")) {
					revealedBoard[i][j] = "" + getNumbers(i, j);
				}
			}
		}
	}

	// getting numbers of the mines around a certain place
	int getNumbers(int row, int col) {
		int count = 0;

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i >= 0 && i < revealedBoard.length && j >= 0 && j < revealedBoard[i].length) {
					if (revealedBoard[i][j].equals("*")) {
						count++;
					}
				}
			}
		}
		return count;
	}


	//prints the board for game
	void printBoard() {
		for (String[] i : this.board) {
			for (String j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	// prints the board with mines and numbers
	void showAnswer() {
		for (String[] i : this.revealedBoard) {
			for (String j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}