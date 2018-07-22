package maxlich.game.model;

import maxlich.game.util.PlayerNumber;
import maxlich.game.util.ResultType;

import javax.swing.table.TableModel;

public abstract class Model {
    public abstract String getPlayerName(PlayerNumber playerNumber);

    public abstract TableModel getFieldTableModel();

    public abstract PlayerNumber getPlayerNumberWhoMakesAMove();

    public abstract boolean putFigureOnFieldCell(int selectedRow, int selectedColumn);

   // public abstract boolean isFieldFull();

   // public abstract PlayerNumber getWinnerInCurrParty();

    public abstract ResultType getPartyResult();

    public abstract ResultType getGameResult();
}
