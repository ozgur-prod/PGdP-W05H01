package pgdp.chess;

import pgdp.PinguLib;



public class PinguChess {

    private GameBoard gameBoard;
    private boolean pinguPlaying;

    public PinguChess() {
        setGameBoard(GameBoard.newDefault());
        setPinguPlaying(true);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean isPinguPlaying() {
        return pinguPlaying;
    }

    public void setPinguPlaying(boolean playing) {
        pinguPlaying=playing;
    }


    public int getTeam(char piece) {

        int team = switch (piece){
            case 'Z' , 'F', 'K', 'C' -> 0;
            case 'S', 'O', 'E', 'A' -> 1;
            default -> -1;
        };

        return team;
    }


    public int getDirection(int dX, int dY) {

        boolean equal = Math.abs(dX) == Math.abs(dY);

        if (dX > 0 && dY == 0){
            return 2;
        }
        else if (dX < 0 && dY == 0){
            return 6;
        }
        else if (dX == 0 && dY > 0){
            return 4;
        }
        else if (dX == 0 && dY < 0){
            return 0;
        }
        else if (dX > 0 && dY > 0 && equal){
            return 3;
        }
        else if (dX > 0 && dY < 0 && equal){
            return 1;
        }
        else if (dX < 0 && dY < 0 && equal){
            return 7;
        }
        else if (dX < 0 && dY > 0 && equal){
            return 5;
        }
        else {
            return -1;
        }

    }


    public int getDistance(int dX, int dY) {

        int distance = switch (getDirection(dX,dY)){
            case 0, 4 -> Math.abs(dY);
            case 2, 6, 1, 3, 5, 7 -> Math.abs(dX);
            default -> -1;
        };

        if (dX == 0 && dY == 0){
            distance = 0;
        }

        return distance;
    }


    public int getFriendlyNeighbours(int x, int y){

        int numberOfPinguins = 0;
        int numberOfFeinds = 0;

        if (getTeam(getGameBoard().get(x, y)) == 0) {

            for (int i = 0; i < 8; i++) {
                switch (i) {
                    case 0:
                        if (getTeam(getGameBoard().get(x, y-1)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 1:
                        if (getTeam(getGameBoard().get(x+1, y-1)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 2:
                        if (getTeam(getGameBoard().get(x+1, y)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 3:
                        if (getTeam(getGameBoard().get(x+1, y+1)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 4:
                        if (getTeam(getGameBoard().get(x, y+1)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 5:
                        if (getTeam(getGameBoard().get(x-1, y+1)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 6:
                        if (getTeam(getGameBoard().get(x-1, y)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                    case 7:
                        if (getTeam(getGameBoard().get(x-1, y-1)) == 0) {
                            numberOfPinguins++;
                        }
                        break;
                }
            }

            return numberOfPinguins;
        }

        if (getTeam(getGameBoard().get(x, y)) == 1){

            for (int i = 0; i < 8; i++){
                switch (i){
                    case 0: if (getTeam(getGameBoard().get(x, y-1)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 1: if (getTeam(getGameBoard().get(x+1, y-1)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 2: if (getTeam(getGameBoard().get(x+1, y)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 3: if (getTeam(getGameBoard().get(x+1, y+1)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 4: if (getTeam(getGameBoard().get(x, y+1)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 5: if (getTeam(getGameBoard().get(x-1, y+1)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 6: if (getTeam(getGameBoard().get(x-1, y)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                    case 7: if (getTeam(getGameBoard().get(x-1, y-1)) == 1){
                        numberOfFeinds++;
                    }
                        break;
                }
            }

            return numberOfFeinds;

        }


        else {
            return -1;
        }
    }


    public boolean checkPath(int fromX, int fromY, int toX, int toY) {
        int dX = toX - fromX;
        int dY = toY - fromY;
        boolean validPath = true;


        if (getDirection(dX, dY) != -1 && getDistance(dX, dY) > 0) {
            if (getTeam(getGameBoard().get(toX, toY)) != getTeam(getGameBoard().get(fromX, fromY))) {
                for (int i = 1; i < getDistance(dX, dY); i++) {
                    switch (getDirection(dX, dY)) {
                        case 0:
                            if (getGameBoard().get(fromX, fromY - i) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 1:
                            if (getGameBoard().get(fromX + i, fromY - i) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 2:
                            if (getGameBoard().get(fromX + i, fromY) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 3:
                            if (getGameBoard().get(fromX + i, fromY + i) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 4:
                            if (getGameBoard().get(fromX, fromY + i) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 5:
                            if (getGameBoard().get(fromX - i, fromY + i) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 6:
                            if (getGameBoard().get(fromX - i, fromY) != ' ') {
                                validPath = false;
                            }
                            break;
                        case 7:
                            if (getGameBoard().get(fromX - i, fromY - i) != ' ') {
                                validPath = false;
                            }
                    }
                }
            }
            else {
                validPath = false;
            }
        }
        else {
            validPath = false;
        }


        return validPath;
    }


    public boolean checkJump(int fromX, int fromY, int toX, int toY) {
        int dX = toX - fromX;
        int dY = toY - fromY;
        boolean validJump;
        if (getDirection(dX, dY) != -1 && getDistance(dX, dY) > 0){
            validJump = getTeam(getGameBoard().get(toX, toY)) != getTeam(getGameBoard().get(fromX, fromY));
        } else {
            validJump = false;
        }return validJump;
    }


    public boolean checkMove(int fromX, int fromY, int toX, int toY) {
        int dX = toX - fromX;
        int dY = toY - fromY;
        boolean validMove = false;

        if (getTeam(getGameBoard().get(fromX, fromY)) != -1 && getGameBoard().get(toX, toY) != '\0'){

                if (getGameBoard().get(fromX,fromY) == 'F' || getGameBoard().get(fromX,fromY) == 'O'){
                    if (checkJump(fromX,fromY,toX,toY)){
                        if (getDistance(dX,dY) <= 2){
                            validMove = true;
                        }
                    }
                }
                else{
                    if (checkPath(fromX,fromY,toX,toY)){

                        switch (getGameBoard().get(fromX,fromY)){
                            case 'Z': if ((getDirection(dX,dY) == 4 && getDistance(dX,dY) <= 3) || (getDirection(dX,dY) != 4 && getDistance(dX,dY) == 1)){
                                validMove = true;
                            }
                                break;
                            case 'S': if ((getDirection(dX,dY) == 0 && getDistance(dX,dY) <= 3) || (getDirection(dX,dY) != 0 && getDistance(dX,dY) == 1)){
                                validMove = true;
                            }
                                break;
                            case 'K', 'E': if (getFriendlyNeighbours(fromX,fromY) > 1 || (getFriendlyNeighbours(fromX,fromY) < 2 && getDistance(dX,dY) <= 5)){
                                validMove = true;
                            }
                                break;
                            case 'C', 'A': if (getDistance(dX,dY) == 1){
                                validMove = true;
                            }
                                break;
                        }
                    }
                }
        }


        return validMove;
    }


    public void runTurn() {

        PinguChess p = new PinguChess();
        PinguLib.writeGameBoard(getGameBoard());

        int team;

        if (isPinguPlaying()){
            System.out.println("Die Pinguine sind am Zug");
            team = 0;
        }
        else {
            System.out.println("Die Feinde der Pinguine sind am Zug" );
            team = 1;
        }

        int x = PinguLib.readInt("Bitte X Koordinate der Figur eingeben") - 1;
        int y = PinguLib.readInt("Bitte Y Koordinate der Figur eingeben") - 1;

        while (getTeam(getGameBoard().get(x,y)) != team){
            x = PinguLib.readInt("Bitte X Koordinate der Figur eingeben") - 1;
            y = PinguLib.readInt("Bitte Y Koordinate der Figur eingeben") - 1;
        }

        System.out.println( "Wohin soll die Figur gehen?");

        int newX = PinguLib.readInt("Bitte X Koordinate des Zielfeldes eingeben") - 1;
        int newY = PinguLib.readInt("Bitte Y Koordinate des Zielfeldes eingeben") - 1;

        while (!checkMove(x,y,newX,newY)){
            newX = PinguLib.readInt("Bitte X Koordinate des Zielfeldes eingeben") - 1;
            newY = PinguLib.readInt("Bitte Y Koordinate des Zielfeldes eingeben") - 1;
        }

        getGameBoard().set(newX,newY, getGameBoard().get(x,y));
        getGameBoard().set(x,y,' ');

        setPinguPlaying(!isPinguPlaying());


    }

    public void run() {

        while (getGameBoard().evaluateWinner() == 0){
            runTurn();
        }

        if (getGameBoard().evaluateWinner() == -1){
            System.out.println("Beide haben gewonnen?");
        }
        else if (getGameBoard().evaluateWinner() == 1){
            System.out.println("Die Pinguine haben gewonnen!");
        }
        else if (getGameBoard().evaluateWinner() == 2){
            System.out.println("Die Feinde der Pinguine haben gewonnen!");
        }



    }

    public static void main(String[] args) {
        new PinguChess().run();
        //PinguChess p = new PinguChess();
        //p.getGameBoard().set(3,1,' ');
        //p.run();


    }


}
