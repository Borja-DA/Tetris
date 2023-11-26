package clases;

//Representa una pieza del juego Tetris
class Piece {
	private char[][] shape; // Representación de la forma de la pieza
	private int height; // Altura de la pieza
	private int width; // Anchura de la pieza

	// Constructor privado: crea una pieza con la forma proporcionada
	private Piece(char[][] shape) {
		this.shape = shape;
		this.height = shape.length;
		this.width = shape[0].length;
	}

	// Genera una pieza aleatoria a partir de un conjunto predefinido de formas
	public static Piece getRandomPiece() {
		char[][][] pieces = { { { 'X', 'X', 'X' }, { ' ', 'X', ' ' } }, { { 'X', 'X', 'X' }, { ' ', ' ', 'X' } },
				{ { 'X', 'X', 'X' } }, { { 'X', 'X', 'X', 'X' } }, { { 'X', 'X' }, { 'X', 'X' } } };

		int randomIndex = (int) (Math.random() * pieces.length);
		return new Piece(pieces[randomIndex]);
	}

	// Devuelve la forma de la pieza
	public char[][] getShape() {
		return shape;
	}

	// Devuelve la altura de la pieza
	public int getHeight() {
		return height;
	}

	// Devuelve la anchura de la pieza
	public int getWidth() {
		return width;
	}

	// Imprime la forma de la pieza en la consola
	public void printPiece() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(shape[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
