package maxlich.game.model;

import maxlich.game.util.PlayerNumber;
import maxlich.game.util.ResultType;

import javax.swing.table.TableModel;

public interface Model {
    String getPlayerName(PlayerNumber playerNumber);
    int getPlayerWinsCount(PlayerNumber playerNumber);

    TableModel getFieldTableModel();
    PlayerNumber getPlayerNumberWhoMakesAMove();
    int getPartyNumber();

    boolean putFigureOnFieldCell(int selectedRow, int selectedColumn);

    ResultType getPartyResult();
    ResultType getGameResult();

    void initNewParty();
    void initNewGame();
}
