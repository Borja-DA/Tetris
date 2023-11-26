package clases;

import java.util.Scanner;

//Clase principal que contiene el bucle principal del juego Tetris
public class Tetris {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Board board = new Board(); // Crea un nuevo tablero
		int pieceCount = 0; // Contador de piezas colocadas

		while (true) {
			// Muestra el estado actual del tablero y la próxima pieza
			board.printBoard();
			System.out.println("Número de piezas colocadas: " + pieceCount);
			System.out.println("Próxima pieza:");
			Piece nextPiece = Piece.getRandomPiece();
			nextPiece.printPiece();
			System.out.println("Presione Enter para insertar la siguiente pieza...");
			scanner.nextLine();

			// Crea y coloca una nueva pieza
			Piece currentPiece = nextPiece;
			if (board.placePiece(currentPiece)) {
				pieceCount++;
			} else {
				System.out.println("¡Juego terminado! No se puede colocar la pieza en el tablero.");
				break;
			}
		}
	}
}