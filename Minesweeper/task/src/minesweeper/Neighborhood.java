package minesweeper;

import java.util.ArrayList;
import java.util.List;

//find out the "neighbors" of central cell. "." are neighbours, "x" is the central cell
//  ...
//  .x.
//  ...
public class Neighborhood {
    private final Coordinate coordinate; //central cell is around that found out cells
    private final int maxX; //max columns in the field
    private final int maxY; // max rows in the field
    private final List<Coordinate> coordinates = new ArrayList<>(8); //the coordinates of neighbors
    private int currentNext = 0; //using for hasNext() to return current coordinate from 'coordinates'

    public Neighborhood(Coordinate coordinate, int maxY , int maxX) {
        this.coordinate = coordinate;
        this.maxX = maxX;
        this.maxY = maxY;
        findOutNeighbors();
    }
    // fill the list "coordinates"
    public void findOutNeighbors() {
        // x and y of the central cell
        int yHost = coordinate.getY();
        int xHost = coordinate.getX();
// to start form top left corner subtract 1 from x and y of central cell
        int neighborY = yHost -1;
        for (int i = 0; i < 3; i++) {   //loop of y coordinates
            int neighborX = xHost -1;
            for (int j = 0; j < 3; j++) { //loop of x coordinates

                if((neighborX >= 0 && neighborY >= 0)    //check if neighbours are out of the top and the left edge of field
                        && !(neighborX == xHost && neighborY == yHost) &&  //exclude the central cell
                        (neighborX < maxX && neighborY < maxY)) {   // out of the bottom and right edge of field
                    coordinates.add(new Coordinate(neighborY, neighborX));
                }
                neighborX++;
            }
            neighborY++;

        }
    }
//true if user don't use all the neighbors' coordinates
    public boolean hasNext() {

        if(currentNext < coordinates.size()) return true;
        currentNext = 0; //restart the returning data from list from index = 0
        return false;
    }
//returns neighbor's coordinate from coordinates
    public Coordinate next() {
        return coordinates.get(currentNext++);
    }


}
