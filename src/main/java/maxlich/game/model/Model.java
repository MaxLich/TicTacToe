package maxlich.game.model;

import maxlich.game.util.PlayerNumber;

import javax.swing.table.TableModel;

public abstract class Model {
    public abstract String getPlayerName(PlayerNumber playerNumber);

    public abstract TableModel getFieldTableModel();

    public abstract PlayerNumber getPlayerNumberWhoMakesAMove();
}
