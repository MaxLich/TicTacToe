package maxlich.game.model;

import maxlich.game.util.ResultType;

public class FieldManager {
    static final int FIELD_SIZE = 3; //размер поля: 3х3

    private static FieldManager instance = null;

    private Figure[][] field = new Figure[FIELD_SIZE][FIELD_SIZE];
    private int elementCount = 0;

    private FieldManager() {
        initEmptyField();
    }

    private void initEmptyField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = Figure.NONE;
            }
        }
    }

    static FieldManager getInstance() {
        if (instance == null)
            instance = new FieldManager();
        return instance;
    }

    Figure[][] getField() {
        return field;
    }

    Figure getFieldCell(int row, int column) {
        return field[row][column];
    }

    void setFieldCell(int row, int column, Figure figure) {
        field[row][column] = figure;
        elementCount++;
    }

    boolean isFieldFull() { //проверка поля на заполненность фигурами
        return elementCount >= FIELD_SIZE * FIELD_SIZE;
    }

    void clearField() {
        initEmptyField();
        elementCount = 0;
    }

    // Проверяет поле на "три в ряд" от текущего элемента с индексами 'selectedRow' и 'selectedColumn'
    boolean checkThreeInLineOnField(final int selectedRow, final int selectedColumn) {
        if (elementCount < FIELD_SIZE * 2 - 1)
            return false;

        boolean isThreeInLine;
        // проверка строки
        final Figure currPutFigure = getFieldCell(selectedRow, selectedColumn);
        isThreeInLine = checkLineForThreeInLine(LineType.ROW, selectedColumn, selectedRow, currPutFigure);
        if (isThreeInLine)
            return true;

        // проверка столбца
        isThreeInLine = checkLineForThreeInLine(LineType.COLUMN, selectedRow, selectedColumn, currPutFigure);
        if (isThreeInLine)
            return true;

        // проверка прямой диагонали
        if (selectedColumn == selectedRow) {
            isThreeInLine = checkLineForThreeInLine(LineType.DIRECT_DIAGONAL, selectedColumn, -1, currPutFigure);
            if (isThreeInLine)
                return true;
        }

        // проверка обратной диагонали
        if (selectedColumn + selectedRow == FIELD_SIZE - 1) {
            isThreeInLine = checkLineForThreeInLine(LineType.INVERSE_DIAGONAL, selectedColumn, -1, currPutFigure);
        }

        return isThreeInLine;
    }

    //Проверяет "линию" в матрице (строку, столбец или диагональ) на "три в ряд"
    private boolean checkLineForThreeInLine(LineType lineType, int missingIndex, int fixedIndex, Figure putFigure) {
        Figure currCellFigure = null; //фигура текущей клетки поля
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (i == missingIndex)
                continue;

            switch (lineType) {
                case ROW:
                    currCellFigure = getFieldCell(fixedIndex, i);
                    break;
                case COLUMN:
                    currCellFigure = getFieldCell(i, fixedIndex);
                    break;
                case DIRECT_DIAGONAL:
                    currCellFigure = getFieldCell(i, i);
                    break;
                case INVERSE_DIAGONAL:
                    currCellFigure = getFieldCell(FIELD_SIZE - i - 1, i);
            }

            if (currCellFigure != putFigure) {
                return false;
            }
        }

        return true;
    }


    //тип линии, вдоль которой идём, перебирая клетки поля (элементы матрицы)
    private enum LineType {
        ROW, //строка
        COLUMN, //столбец
        DIRECT_DIAGONAL, //прямая диагональ
        INVERSE_DIAGONAL //обратная диагональ
    }
}
