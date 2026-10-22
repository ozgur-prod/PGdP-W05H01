package pgdp.chess;

import javax.swing.text.DefaultEditorKit;

public class GameBoard {

    private int sizeX;
    private int sizeY;
    private char[][] gameBoard;


    public GameBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        gameBoard = new char[sizeY][sizeX];

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }

    }

    public static GameBoard newDefault() {
        GameBoard defaultGameBoard = new GameBoard(8,16);

        defaultGameBoard.set(0,0,'Z');
        defaultGameBoard.set(1,0,'F');
        defaultGameBoard.set(2,0,'F');
        defaultGameBoard.set(3,0,'K');
        defaultGameBoard.set(4,0,'C');
        defaultGameBoard.set(5,0,'F');
        defaultGameBoard.set(6,0,'F');
        defaultGameBoard.set(7,0,'Z');
        defaultGameBoard.set(2,1,'Z');
        defaultGameBoard.set(3,1,'Z');
        defaultGameBoard.set(4,1,'Z');
        defaultGameBoard.set(5,1,'Z');

        defaultGameBoard.set(0,15,'S');
        defaultGameBoard.set(1,15,'O');
        defaultGameBoard.set(2,15,'O');
        defaultGameBoard.set(3,15,'A');
        defaultGameBoard.set(4,15,'E');
        defaultGameBoard.set(5,15,'O');
        defaultGameBoard.set(6,15,'O');
        defaultGameBoard.set(7,15,'S');
        defaultGameBoard.set(2,14,'S');
        defaultGameBoard.set(3,14,'S');
        defaultGameBoard.set(4,14,'S');
        defaultGameBoard.set(5,14,'S');

        return defaultGameBoard;
    }

    public char get(int x, int y) {
        if (x > getSizeX() - 1 || y > getSizeY() - 1 || x < 0 || y < 0){
            return '\0';
        }
        else {
            return gameBoard[y][x];
        }
    }

    public boolean set(int x, int y, char piece) {

        if (x > getSizeX() - 1 || y > getSizeY() - 1 || x < 0 || y < 0 || piece == '\0'){
            return false;
        }
        else {
            gameBoard[y][x] = piece;
            return true;
        }

    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int evaluateWinner() {
        int A = 0;
        int C = 0;

        for (int i = 0; i < getSizeY(); i++) {
            for (int j = 0; j < getSizeX(); j++) {
                if (get(j,i) == 'A'){
                    A++;
                }
                if (get(j,i) == 'C'){
                    C++;
                }
            }
        }

        return (A == 0 && C == 0) ? -1 : (A == 0 && C > 0) ? 1 : (A > 0 && C == 0) ? 2 : 0 ;
    }



}
