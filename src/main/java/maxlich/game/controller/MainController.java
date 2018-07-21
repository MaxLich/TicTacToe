package maxlich.game.controller;

import maxlich.game.model.Model;
import maxlich.game.util.PlayerNumber;

import javax.swing.table.TableModel;

public class MainController extends Controller {
    public MainController(Model model) {
        super(model);
    }

    @Override
    public void init() {
    }

    @Override
    public void onLoadPlayerName(PlayerNumber playerNumber) {
        String playerName = model.getPlayerName(playerNumber);
        view.showPlayerName(playerNumber, playerName);
    }

    @Override
    public void onLoadWhoseTurnInfo() {
        PlayerNumber playerWhoMakeMove = model.getPlayerNumberWhoMakesAMove();
        view.showWhoseTurn(playerWhoMakeMove);
    }

    @Override
    public void onLoadFieldTableModel() {
        TableModel tableModel = model.getFieldTableModel();
        view.loadFieldTableModel(tableModel);
    }

    @Override
    public void onClickFieldCell(int selectedRow, int selectedColumn) {
        boolean isFigurePut = model.putFigureOnFieldCell(selectedRow,selectedColumn);
        if (!isFigurePut)
            return;
        onLoadWhoseTurnInfo();
    }
}
