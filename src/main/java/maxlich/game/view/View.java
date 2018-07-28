package maxlich.game.view;

import maxlich.game.util.PlayerNumber;

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

public interface View {
    void showTextMessage(String title, String text, boolean isPositive);
    void showTextMessage(String title, String text);
    void showTextMessage(String text);
    void showTextMessage(String text, boolean isPositive);
    boolean showDialog(String title, String text);

    void fireLoadWhoseTurnInfo();
    void showWhoseTurn(PlayerNumber playerNumber);

    void fireLoadPartyNumber();
    void showPartyTitle(int partyNumber);

    void fireLoadPlayerName(PlayerNumber playerNumber);
    void showPlayerName(PlayerNumber playerNumber, String playerName);

    void fireLoadPlayerWinsCount(PlayerNumber playerNumber);
    void showPlayerWinsCount(PlayerNumber playerNumber, int winsCount);

    void fireLoadFieldTableModel();
    void loadFieldTableModel(TableModel tableModel);

    void fireClickFieldCell(int selectedRow, int selectedColumn);
    void showPartyResult(String resultMessage);
    void clearPartyResult();
    void setFieldActivity(boolean isActive);

    void fireStartNewGame();
    void fireStartNewParty();

    void setNewPartyMenuItemActivity(boolean isActive);

    // void increaseCountOfWinsForPlayer(PlayerNumber winner);
}
