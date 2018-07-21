package maxlich.game.model;

import maxlich.game.util.PlayerNumber;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainModel extends Model {
    private String[][] field = {
            {" "," "," "},
            {" "," "," "},
            {" "," "," "}
    };

    private DefaultTableModel fieldTableModel = new DefaultTableModel(field, null/*new String[]{"1","2","3"}*/) {
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
        }
    };

    private Map<PlayerNumber, Player> playerListMap = new HashMap<>();
    private PlayerNumber whoMakesAMove = PlayerNumber.PLAYER_1; //номер игрока, который сейчас совершает ход
    private int partyNumber = 1; //номер партии игры
    private int partyCountInGame = 3; //общее количество партий в игре (может быть равно 1,3,5,7,9)

    public MainModel() {
        playerListMap.put(PlayerNumber.PLAYER_1, new Player(PlayerNumber.PLAYER_1));
        playerListMap.put(PlayerNumber.PLAYER_2, new Player(PlayerNumber.PLAYER_2));
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

    public int getPartyCountInGame() {
        return partyCountInGame;
    }

    public void setPartyCountInGame(int partyCountInGame) {
        this.partyCountInGame = partyCountInGame;
    }
}
