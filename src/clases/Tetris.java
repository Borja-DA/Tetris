package clases;

import java.util.Scanner;

public class Tetris {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        int pieceCount = 0;

        while (true) {
            // Mostrar estado actual del tablero y próxima pieza
            board.printBoard();
            System.out.println("Número de piezas colocadas: " + pieceCount);
            System.out.println("Próxima pieza:");
            Piece nextPiece = Piece.getRandomPiece();
            nextPiece.printPiece();
            System.out.println("Presione Enter para insertar la siguiente pieza...");
            scanner.nextLine();

            // Crear y colocar una nueva pieza
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

class Board {
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private char[][] grid;

    public Board() {
        grid = new char[ROWS][COLUMNS];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

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

    private boolean canPlacePiece(Piece piece, int row, int col) {
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(); j++) {
                // Asegúrate de que no estás yendo más allá de los límites del tablero
                if (row - i < 0 || col + j >= COLUMNS) {
                    return false;
                }

                if (piece.getShape()[i][j] == 'X' && grid[row - i][col + j] == 'X') {
                    return false;
                }
            }
        }
        return true;
    }


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

class Piece {
    private char[][] shape;
    private int height;
    private int width;

    private Piece(char[][] shape) {
        this.shape = shape;
        this.height = shape.length;
        this.width = shape[0].length;
    }

    public static Piece getRandomPiece() {
        char[][][] pieces = {
                {{'X', 'X', 'X'}, {' ', 'X', ' '}},
                {{'X', 'X', 'X'}, {' ', ' ', 'X'}},
                {{'X', 'X', 'X'}},
                {{'X', 'X', 'X', 'X'}},
                {{'X', 'X'}, {'X', 'X'}}
        };

        int randomIndex = (int) (Math.random() * pieces.length);
        return new Piece(pieces[randomIndex]);
    }

    public char[][] getShape() {
        return shape;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

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
