package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    Field field;
    List<Coordinate> usersMarks = new ArrayList<>();
    String action;

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        int numberOfMines = scanner.nextInt();
        field = new Field(9, 9, numberOfMines);
        field.setField();
        field.show();
        game(scanner);
        scanner.close();

    }

    private void game(Scanner scanner) {


// loop of inputs
        for(;;) {
            Coordinate coordinate = getInput(scanner);
            Cell cell = field.getCell(coordinate.getY(), coordinate.getX());

            makeAction(coordinate, cell);
            field.show();
            if (isVictory()) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }


    }

    private void makeAction(Coordinate coordinate, Cell cell) {
        switch (action){
            case "mine":
                putMark(coordinate, cell);
                break;
            case "free":
                if (cell.isMine()) {
                    gameOver();
                } else {
                    freeCell(coordinate);
                }
                break;
            default:
                System.out.println("Something wrong!");
        }


    }
    //check the terms of victory
    //number of the marked cells equal to the number of mines & their coordinates equals to the coordinates of mines
    private boolean isVictory() {
        return  field.getNUMBER_OF_MINES() == usersMarks.size() && usersMarks.containsAll(field.getXyOfMines());


    }

    private Coordinate getInput(Scanner scanner){
        int x, y;
        for(;;) {
            System.out.print("Set/unset mine marks or claim a cell as free: ");
            x = scanner.nextInt() - 1;  // -1 because of index starts from 0, but input coordinates form 1
            y = scanner.nextInt() - 1;
            action = scanner.next();
            Cell cell = field.getCell(y, x);
            if (cell.getMaskedState().matches("\\d")) { //check if the chosen cell contains a number
                System.out.println("There is a number here!");
                continue;
            }
            break;
        }
        return new Coordinate(y,x);

    }

    //open the cell and if it is empty then open neighbours too. It stops on cells with numbers
    //recursive method
    private void freeCell(Coordinate coordinate) {
        Cell cell = field.getCell(coordinate.getY(), coordinate.getX());
        cell.unMask();
        if(cell.getSTATE().matches("\\d")){
            return;
        }

        int maxX = field.getWidth();
        int maxY = field.getHeight();

        Neighborhood neighborhood = new Neighborhood(coordinate, maxY, maxX); //neighbours of the cell

        while (neighborhood.hasNext()) {
            Coordinate nhsCoordinate = neighborhood.next();
            Cell nhCell = field.getCell(nhsCoordinate.getY(), nhsCoordinate.getX());
            if(nhCell.getMask()){         //check if the neighbor is not opened then call the method recursively
                freeCell(nhsCoordinate);
            }
        }


    }

    private void putMark(Coordinate coordinate, Cell cell) {
        if (!usersMarks.contains(coordinate)) {  //check if list of user inputs don't contain the coordinate
            cell.mark();                         //mark the cell in the field output
            usersMarks.add(coordinate);          //add the coordinate to list of user's input
        } else {                               //reverse situation
            cell.mark();
            usersMarks.remove(coordinate);
        }
    }

    private void gameOver(){
        field.show();
        System.out.println("You stepped on a mine and failed!");
        System.exit(0);
    }


}
