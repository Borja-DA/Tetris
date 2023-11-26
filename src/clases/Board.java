package clases;

//Representa el tablero del juego Tetris
class Board {
	private static final int ROWS = 10; // Número de filas en el tablero
	private static final int COLUMNS = 10; // Número de columnas en el tablero
	private char[][] grid; // Matriz que representa el estado actual del tablero

	// Constructor: inicializa el tablero y lo llena con celdas vacías '-'
	public Board() {
		grid = new char[ROWS][COLUMNS];
		initializeBoard();
	}

	// Inicializa el tablero llenándolo con celdas vacías '-'
	private void initializeBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				grid[i][j] = '-';
			}
		}
	}

	// Imprime el estado actual del tablero en la consola
	public void printBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// Intenta colocar una pieza en el tablero y devuelve true si es exitoso, false
	// si no se puede colocar
	public boolean placePiece(Piece piece) {
		for (int i = ROWS - 1; i >= 0; i--) {
			for (int j = 0; j <= COLUMNS - piece.getWidth(); j++) {
				if (canPlacePiece(piece, i, j)) {
					placePieceOnBoard(piece, i, j);
					return true;
				}
			}
		}
		return false;
	}

	// Verifica si es posible colocar una pieza en una posición específica del
	// tablero
	private boolean canPlacePiece(Piece piece, int row, int col) {
		for (int i = 0; i < piece.getHeight(); i++) {
			for (int j = 0; j < piece.getWidth(); j++) {
				// Asegúrate de que no estás yendo más allá de los límites del tablero
				if (row - i < 0 || col + j >= COLUMNS) {
					return false;
				}

				// Verifica si la celda en el tablero y la celda de la pieza son ambas 'X'
				if (piece.getShape()[i][j] == 'X' && grid[row - i][col + j] == 'X') {
					return false; // No se puede colocar la pieza aquí
				}
			}
		}
		return true; // Es posible colocar la pieza en esta posición
	}

	// Coloca una pieza en el tablero en una posición específica
	private void placePieceOnBoard(Piece piece, int row, int col) {
		for (int i = 0; i < piece.getHeight(); i++) {
			for (int j = 0; j < piece.getWidth(); j++) {
				if (piece.getShape()[i][j] == 'X') {
					grid[row - i][col + j] = 'X';
				}
			}
		}
	}
}