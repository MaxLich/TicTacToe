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
        view.loadPlayerName(playerNumber, playerName);
    }

    @Override
    public void onLoadFieldTableModel() {
        TableModel tableModel = model.getFieldTableModel();
        view.loadFieldTableModel(tableModel);
    }
}
