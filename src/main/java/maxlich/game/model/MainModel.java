package maxlich.game.model;

import maxlich.game.util.PlayerNumber;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

public class MainModel extends Model {
    private String[][] field = {
            {" "," "," "},
            {" "," "," "},
            {" "," "," "}
    };
    private DefaultTableModel fieldTableModel = new DefaultTableModel(3,3) {
        {
            for (String[] cell : field) {
                addRow(cell);
            }
        }
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private Map<PlayerNumber, Player> playerListMap = new HashMap<>();
    private PlayerNumber whoMakesAMove;

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
}
