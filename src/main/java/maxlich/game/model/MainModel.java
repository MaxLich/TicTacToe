package maxlich.game.model;

import maxlich.game.util.PlayerNumber;
import maxlich.game.util.ResultType;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

public class MainModel implements Model {
    private FieldManager fieldManager = FieldManager.getInstance();

    private DefaultTableModel fieldTableModel = new DefaultTableModel(fieldManager.getField(), null) {
        @Override
        public int getRowCount() {
            return FieldManager.FIELD_SIZE;
        }

        @Override
        public int getColumnCount() {
            return FieldManager.FIELD_SIZE;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final Map<PlayerNumber, Player> playerListMap = new HashMap<>(); //список игроков (в виде пар "номер игрока - игрок")
    private PlayerNumber whoMakesFirstMove = PlayerNumber.PLAYER_1; //номер игрока, который ходит первым
    private PlayerNumber whoMakesAMove = PlayerNumber.PLAYER_1; //номер игрока, который сейчас совершает ход

    private int partyNumber = 1; //номер партии игры
    private int partyCountInGame = 3; //общее количество партий в игре (может быть равно 1,3,5,7,9)

    private ResultType partyResult = null; //результат текущей партии
    private ResultType gameResult = null; //результат всей игры (серии партий)


    public MainModel() {
        playerListMap.put(PlayerNumber.PLAYER_1, new Player(PlayerNumber.PLAYER_1.getTitle(), Figure.X));
        playerListMap.put(PlayerNumber.PLAYER_2, new Player(PlayerNumber.PLAYER_2.getTitle(), Figure.O));
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
        Figure figureAtCell = fieldManager.getFieldCell(selectedRow, selectedColumn);
        if (figureAtCell != Figure.NONE || partyResult != null)
            return false;

        fieldManager.setFieldCell(selectedRow, selectedColumn, playerListMap.get(whoMakesAMove).getFigure());
        reloadTableModel();
        boolean isFieldFull = fieldManager.isFieldFull();
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

    private boolean checkForWinnerInCurrParty(int selectedRow, int selectedColumn) {
        boolean isThreeInLine = fieldManager.checkThreeInLineOnField(selectedRow, selectedColumn);
        if (isThreeInLine) {
            partyResult = ResultType.getResultTypeByWinner(whoMakesAMove);
            playerListMap.get(whoMakesAMove).incrementWins();
        }
        return isThreeInLine;
    }

    private void checkForGameEnd() {
        if (partyNumber != partyCountInGame || partyResult == null)
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

    private void reloadTableModel() {
        fieldTableModel.setDataVector(fieldManager.getField(), null);
    }

    @Override
    public void initNewParty() {
        partyNumber++;
        whoMakesFirstMove = partyResult != null && partyResult != ResultType.DRAW ?
                partyResult.getWinner() : PlayerNumber.PLAYER_1;
        whoMakesAMove = whoMakesFirstMove;
        fieldManager.clearField();
        reloadTableModel();
        partyResult = null;
    }

    @Override
    public void initNewGame() {
        partyNumber = 1;
        if (whoMakesFirstMove == null)
            whoMakesFirstMove = PlayerNumber.PLAYER_1;
        whoMakesAMove = whoMakesFirstMove;
        fieldManager.clearField();
        reloadTableModel();
        partyResult = null;
        gameResult = null;
        playerListMap.values().forEach(Player::resetWins);
    }

    @Override
    public ResultType getPartyResult() {
        return partyResult;
    }

    @Override
    public ResultType getGameResult() {
        return gameResult;
    }

}
