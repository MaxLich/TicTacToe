package maxlich.game.model;

import maxlich.game.util.PlayerNumber;
import maxlich.game.util.ResultType;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

public class MainModel implements Model {
    private static final int FIELD_SIZE = 3; //размер поля: 3х3
    private final static Figure[][] EMPTY_FIELD = {
            {Figure.NONE, Figure.NONE, Figure.NONE},
            {Figure.NONE, Figure.NONE, Figure.NONE},
            {Figure.NONE, Figure.NONE, Figure.NONE}
    };

    private DefaultTableModel fieldTableModel = new DefaultTableModel(EMPTY_FIELD, null) {
        @Override
        public int getRowCount() {
            return 3;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
            // return true;
        }
    };

    private final Map<PlayerNumber, Player> playerListMap = new HashMap<>(); //список игроков (в виде пар "номер игрока - игрок")
    private PlayerNumber whoMakesFirstMove = PlayerNumber.PLAYER_1; //номер игрока, который ходит первым
    private PlayerNumber whoMakesAMove = PlayerNumber.PLAYER_1; //номер игрока, который сейчас совершает ход

    private PlayerNumber winnerInCurrParty = null; //победитель в текущей партии
    private PlayerNumber winnerOfThisGame = null; //победитель в этой игре (серии партий)

    private int cycleCount = 0; //количество циклов в партии (когда совершили ход оба игрока)

    private int partyNumber = 1; //номер партии игры
    private int partyCountInGame = 3; //общее количество партий в игре (может быть равно 1,3,5,7,9)

    private ResultType partyResult = null; //результат текущей партии
    private ResultType gameResult = null; //результат всей игры (серии партий)


    public MainModel() {
        playerListMap.put(PlayerNumber.PLAYER_1, new Player(PlayerNumber.PLAYER_1, Figure.X));
        playerListMap.put(PlayerNumber.PLAYER_2, new Player(PlayerNumber.PLAYER_2, Figure.O));
    }

    @Override
    public String getPlayerName(PlayerNumber playerNumber) {
        return playerListMap.get(playerNumber).getName();
    }

    @Override
    public int getPlayerWinsCount(PlayerNumber playerNumber) {
        return playerListMap.get(playerNumber).getWins();
    }

    @Override
    public TableModel getFieldTableModel() {
        return fieldTableModel;
    }

    public PlayerNumber getPlayerNumberWhoMakesAMove() {
        return whoMakesAMove;
    }

    public void setPlayerNumberWhoMakesAMove(PlayerNumber whoMakesAMove) {
        this.whoMakesAMove = whoMakesAMove;
    }

    private void changePlayerNumberWhoMakesAMove() {
        switch (whoMakesAMove) {
            case PLAYER_1:
                whoMakesAMove = PlayerNumber.PLAYER_2;
                break;
            case PLAYER_2:
                whoMakesAMove = PlayerNumber.PLAYER_1;
        }
    }

    @Override
    public int getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(int partyNumber) {
        this.partyNumber = partyNumber;
    }

    public int getPartyCountInGame() {
        return partyCountInGame;
    }

    public void setPartyCountInGame(int partyCountInGame) {
        this.partyCountInGame = partyCountInGame;
    }

    @Override
    public boolean putFigureOnFieldCell(int selectedRow, int selectedColumn) {
        Figure figureAtCell = (Figure) fieldTableModel.getValueAt(selectedRow, selectedColumn);
        if (figureAtCell != Figure.NONE || partyResult != null)
            return false;

        fieldTableModel.setValueAt(playerListMap.get(whoMakesAMove).getFigure(), selectedRow, selectedColumn);
        boolean isFieldFull = isFieldFull();
        boolean isWinnerFound = checkForWinnerInCurrParty(selectedRow, selectedColumn);

        if (!isWinnerFound && !isFieldFull) {
            changePlayerNumberWhoMakesAMove();
        } else {
            if (isFieldFull && !isWinnerFound)
                partyResult = ResultType.DRAW;
            checkForGameEnd();
        }

        return true;
    }

    private void checkForGameEnd() {
        if (partyNumber != partyCountInGame)
            return;

        int player1Wins = playerListMap.get(PlayerNumber.PLAYER_1).getWins();
        int player2Wins = playerListMap.get(PlayerNumber.PLAYER_2).getWins();

        if (player1Wins > player2Wins)
            gameResult = ResultType.PLAYER_1_WON;
        else if (player1Wins < player2Wins)
            gameResult = ResultType.PLAYER_2_WON;
        else
            gameResult = ResultType.DRAW;
    }

    // Проверяет, есть ли победитель: true - победитель есть, false - победителя нет (ничья или игра продолжается)
    private boolean checkForWinnerInCurrParty(final int selectedRow, final int selectedColumn) {
        if (cycleCount < FIELD_SIZE)
            return false;

        boolean isThreeInLine;
        // проверка строки
        isThreeInLine = checkLineForThreeInLine(LineType.ROW, selectedColumn, selectedRow);
        if (isThreeInLine)
            return true;

        // проверка столбца
        isThreeInLine = checkLineForThreeInLine(LineType.COLUMN, selectedRow, selectedColumn);
        if (isThreeInLine)
            return true;

        // проверка прямой диагонали
        if (selectedColumn == selectedRow) {
            isThreeInLine = checkLineForThreeInLine(LineType.DIRECT_DIAGONAL, selectedColumn, -1);
            if (isThreeInLine)
                return true;
        }

        // проверка обратной диагонали
        if (selectedColumn + selectedRow == FIELD_SIZE - 1) {
            isThreeInLine = checkLineForThreeInLine(LineType.INVERSE_DIAGONAL, selectedColumn, -1);
        }

        return isThreeInLine;
    }

    private boolean checkLineForThreeInLine(LineType lineType, int missingIndex, int fixedIndex) {
        final Figure figureOfCurrPlayer = playerListMap.get(whoMakesAMove).getFigure(); //фигура игрока, который ходит
        Figure currCellFigure = null; //фигура текущей клетки поля
        boolean isThreeInLine = true; //три фигуры в ряд?
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (i == missingIndex)
                continue;

            switch (lineType) {
                case ROW:
                    currCellFigure = (Figure) fieldTableModel.getValueAt(fixedIndex, i);
                    break;
                case COLUMN:
                    currCellFigure = (Figure) fieldTableModel.getValueAt(i, fixedIndex);
                    break;
                case DIRECT_DIAGONAL:
                    currCellFigure = (Figure) fieldTableModel.getValueAt(i, i);
                    break;
                case INVERSE_DIAGONAL:
                    currCellFigure = (Figure) fieldTableModel.getValueAt(FIELD_SIZE - i - 1, i);
            }

            if (currCellFigure != figureOfCurrPlayer) {
                isThreeInLine = false;
                break;
            }
        }
        if (isThreeInLine) {
            //winnerInCurrParty = whoMakesAMove;
            partyResult = ResultType.getResultTypeByWinner(whoMakesAMove);
            playerListMap.get(whoMakesAMove).incrementWins();
        }

        return isThreeInLine;
    }


    private boolean isFieldFull() { //проверка поля на заполненность фигурами
        if (whoMakesAMove == whoMakesFirstMove)
            cycleCount++;
        return cycleCount == FIELD_SIZE * FIELD_SIZE / 2 + 1;
    }

    @Override
    public void initNewParty() {
        partyNumber++;
        whoMakesFirstMove = partyResult != null && partyResult != ResultType.DRAW ?
                partyResult.getWinner() : PlayerNumber.PLAYER_1;
        whoMakesAMove = whoMakesFirstMove;
        cycleCount = 0;
        fieldTableModel.setDataVector(EMPTY_FIELD,null);
        partyResult = null;
    }

    /* @Override
    public boolean isFieldFull() {
        return isFieldFull;
    }

    @Override
    public PlayerNumber getWinnerInCurrParty() {
        return winnerInCurrParty;
    }*/

    @Override
    public ResultType getPartyResult() {
        return partyResult;
    }

    @Override
    public ResultType getGameResult() {
        return gameResult;
    }

    //тип линии, вдоль которой идём, перебирая клетки поля (элементы матрицы)
    private enum LineType {
        ROW, //строка
        COLUMN, //столбец
        DIRECT_DIAGONAL, //прямая диагональ
        INVERSE_DIAGONAL //обратная диагональ
    }
}
