package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Field {
    Cell[][] field;
    private final int NUMBER_OF_MINES;
    List<Coordinate> xyOfMines = new ArrayList<>();   //list of the mines' coordinates

    public Field(int rows, int columns, int NUMBER_OF_MINES) {
        field = new Cell[rows][columns];
        this.NUMBER_OF_MINES = NUMBER_OF_MINES;
    }
//return the field[0] length
    public int getWidth() {
        return field[0].length;
    }
    //return the field length
    public int getHeight() {
        return field.length;
    }

    public int getNUMBER_OF_MINES() {
        return NUMBER_OF_MINES;
    }

    public List<Coordinate> getXyOfMines() {
        return xyOfMines;
    }
//fill the field with mines, number cells, empty cells
    public void setField() {
        setMines();
        setNumCells();
        setEmptyCells();
    }
//get current cell from the field
    public Cell getCell(int y, int x) {
        return field[y][x];
    }

    private void setMines() {
        Random index = new Random();
        int mines = NUMBER_OF_MINES;
        while (mines > 0) {
            int row = index.nextInt(getWidth());
            int col = index.nextInt(getHeight());
            if(field[row][col] == null) {
                xyOfMines.add(new Coordinate(row, col));
                String MINE_CELL = "X";
                field[row][col] = new Cell(MINE_CELL);
                mines--;
            }
        }

    }

    private void setEmptyCells() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                String EMPTY_CELL = "/";
                if(field[i][j] == null) field[i][j] = new Cell(EMPTY_CELL);
            }
        }
    }

    private void setNumCells() {
        // bottom and right border of field
        int yMax = field.length;
        int xMax = field[0].length;
        //list of mines
        for (Coordinate yx :
                xyOfMines) {

            Neighborhood nh = new Neighborhood(yx,yMax, xMax);//create a neighborhood of mine
            while (nh.hasNext()) {
                int countOfMines = 0;
                Coordinate coordinate = nh.next();

                Neighborhood neighbor = new Neighborhood(coordinate, yMax,xMax);//create a neighborhood of neighbor of mine

                while (neighbor.hasNext()) {  //check a neighborhood of neighbor of mine for mines and count them
                    Coordinate neighboursOfNeighbors = neighbor.next();
                    int y = neighboursOfNeighbors.getY();
                    int x = neighboursOfNeighbors.getX();
                    if(field[y][x] != null && field[y][x].isMine()) countOfMines++;
                }
                //create a new cell with number state
                if(field[coordinate.getY()][coordinate.getX()] == null) {
                    field[coordinate.getY()][coordinate.getX()] = new Cell(String.valueOf(countOfMines));

                }

            }
        }

    }
//show the field to standart output

    public void show() {
        System.out.println();
        System.out.print("  ");
        for (int i = 1; i <= field[0].length ; i++) {
            System.out.printf("%d", i);
        }
        System.out.println();
        System.out.print("-|");
        for (int i = 0; i < field[0].length; i++) {
            System.out.print("-");
        }
        System.out.println("|");
        int i = 1;
        for (Cell[] cells : field) {
            System.out.printf("%d|", i);
            for (Cell cell : cells) {
                System.out.print(cell.getMaskedState());
            }
            System.out.println("|");
            i++;
        }
        System.out.print("-|");
        for (int j = 0; j < field[0].length; j++) {
            System.out.print("-");
        }
        System.out.println("|");
    }
}
