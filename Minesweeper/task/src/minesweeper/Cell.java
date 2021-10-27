package minesweeper;
//the class that storage in the field
class Cell{

    boolean mask = true;  //show the 'X' of mine or not
    final String STATE; //'X' or number of the cell in the field
    final String closed = ".";
    final String suspected = "*";
    String marker = closed;   //placeholder if mask is true


    Cell(String state){
        this.STATE = state;
    }

    public void unMask() {
        mask = false;
    }

    public boolean getMask() {
        return mask;
    }

    public String getSTATE() {
        return STATE;
    }

    public boolean isMine() {
        return STATE.equals("X");
    }

    public String getMaskedState() {
        return mask ? marker : STATE;
    }

    public void mark() {
        marker = marker.equals(".") ? suspected : closed;    //change the marker if the cell is chosen by user or vice versa
    }
}

