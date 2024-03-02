public class Cell {
    int row, col;
    String value;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = "N";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }

}
