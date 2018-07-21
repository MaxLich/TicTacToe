package maxlich.game.model;

import maxlich.game.util.PlayerNumber;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

public class MainModel extends Model {
    private Figure[][] field = {
            {Figure.NONE,Figure.NONE,Figure.NONE},
            {Figure.NONE,Figure.NONE,Figure.NONE},
            {Figure.NONE,Figure.NONE,Figure.NONE}
    };

    private DefaultTableModel fieldTableModel = new DefaultTableModel(field, null) {
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

    private Map<PlayerNumber, Player> playerListMap = new HashMap<>();
    private PlayerNumber whoMakesAMove = PlayerNumber.PLAYER_1; //номер игрока, который сейчас совершает ход
    private int partyNumber = 1; //номер партии игры
    private int partyCountInGame = 3; //общее количество партий в игре (может быть равно 1,3,5,7,9)

    public MainModel() {
        playerListMap.put(PlayerNumber.PLAYER_1, new Player(PlayerNumber.PLAYER_1, Figure.X));
        playerListMap.put(PlayerNumber.PLAYER_2, new Player(PlayerNumber.PLAYER_2, Figure.O));
    }

    @Override
    public String getPlayerName(PlayerNumber playerNumber) {
        return playerListMap.get(playerNumber).getName();
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

    public int getPartyCountInGame() {
        return partyCountInGame;
    }

    public void setPartyCountInGame(int partyCountInGame) {
        this.partyCountInGame = partyCountInGame;
    }

    @Override
    public boolean putFigureOnFieldCell(int selectedRow, int selectedColumn) {
        Figure figureAtCell = (Figure) fieldTableModel.getValueAt(selectedRow, selectedColumn);
        if (figureAtCell != Figure.NONE)
            return false;

        fieldTableModel.setValueAt(playerListMap.get(whoMakesAMove).getFigure(),selectedRow,selectedColumn);
        changePlayerNumberWhoMakesAMove();
        return true;
    }
}
