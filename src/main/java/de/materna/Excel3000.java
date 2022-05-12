package de.materna;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import javax.annotation.CheckForNull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Excel3000 {
    Table<Integer /*row*/, Integer /*col*/, String /*val*/> table = HashBasedTable.create();
    Calculator calculator;
    final int TABLEROWSIZE = 5;
    final int TABLECOLUMNSIZE = 3;

    public static void main(String[] args) {
        Excel3000 excel = new Excel3000();
        excel.fillTable();
        excel.setCell("B3", "=A1 * 3");
        System.out.println(excel.getCellAt("B3").toString());
        excel.evaluate();
        System.out.println(excel.getCellAt("B3").toString());

    }

    void fillTable() {
        for (int i = 1; i <= TABLEROWSIZE; i++) {
            for (int j = 1; j <= TABLECOLUMNSIZE; j++) {
                table.put(i, j, "2");
            }
        }
    }

    Cell convertToIndex(String cell) {
        cell = cell.toUpperCase();
        // split into String array with
        // [0] -> letters
        // [1] -> numbers
        String[] collumn = cell.split("(?<=\\D)(?=\\d)");
        //convert letters into index based on ASCI table
        // A -> 1, AA -> 27 ...
        int index = 0;
        for (int i = 0; i < collumn[0].length(); i++) {
            index = index + (((int) collumn[0].charAt(i)) - 64) + i * 25;
        }
        return new Cell(Integer.parseInt(collumn[1]), index);
    }

    //TODO Abfrage wenn index groesse der tablle ueberschreitet
    void setCell(String cell, String content) {
        table.put(convertToIndex(cell).row, convertToIndex(cell).collumn, content);
    }

    Cell getCellAt(int row, int col) {
        return new Cell(row, col, table.column(col).get(row));
    }

    Cell getCellAt(String cell) {
        return getCellAt(convertToIndex(cell).row, convertToIndex(cell).collumn);
    }

    Excel3000 evaluate() {
        for (int i = 1; i <= TABLEROWSIZE; i++) {
            for (int j = 1; j <= TABLECOLUMNSIZE; j++) {
                if (getCellAt(i, j).content.startsWith("=")) {
                    evaluteCell(getCellAt(i, j));
                }
            }
        }
        return this;
    }

    void evaluteCell(Cell cell) {
        //= A1 * 3
        String input = cell.content.substring(cell.content.indexOf("=") + 1);
        //A1 * 3

        String [] content = input.split(" ");

        input = "";
        for (int i = 0; i < content.length; i++) {
            //matches if Combination between numbers and letters
            if (content[i].matches("^[A-Z]+[1-9][0-9]*$")) {
                content[i] = getCellAt(content[i]).content;
            }
            input = input + content [i];

        }
        table.put(cell.row, cell.collumn, Calculator.startCalculator(input));

    }

}
