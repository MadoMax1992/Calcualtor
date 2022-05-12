package de.materna;

public class Cell {
    int row;
    int collumn;
    String content;

    public Cell(int row, int collumn, String content) {
        this.row = row;
        this.collumn = collumn;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", collumn=" + collumn +
                ", content='" + content + '\'' +
                '}';
    }

    public Cell(int row, int collumn) {
        this.row = row;
        this.collumn = collumn;
    }
}
