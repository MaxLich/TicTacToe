package maxlich.game.view;

import maxlich.game.util.PlayerNumber;

import javax.swing.table.TableModel;

public interface View {
    void showTextMessage(String title, String text, boolean isPositive);
    void showTextMessage(String title, String text);
    void showTextMessage(String text);
    void showTextMessage(String text, boolean isPositive);

    void fireLoadWhoseTurnInfo();
    void showWhoseTurn(PlayerNumber playerNumber);

    void fireLoadPlayerName(PlayerNumber playerNumber);
    void showPlayerName(PlayerNumber playerNumber, String playerName);

    void fireLoadFieldTableModel();
    void loadFieldTableModel(TableModel tableModel);

    void fireClickFieldCell(int selectedRow, int selectedColumn);
}
