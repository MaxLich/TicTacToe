package maxlich.game.controller;

import maxlich.game.model.Model;
import maxlich.game.util.PlayerNumber;
import maxlich.game.util.ResultType;

import javax.swing.table.TableModel;

public class MainController extends Controller {
    public MainController(Model model) {
        super(model);
    }

    @Override
    public void init() {
    }

    @Override
    public void onLoadPartyNumber() {
        int partyNumber = model.getPartyNumber();
        view.showPartyTitle(partyNumber);
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
        boolean isFigurePut = model.putFigureOnFieldCell(selectedRow, selectedColumn);
        if (!isFigurePut)
            return;

        //PlayerNumber winnerInCurrParty = model.getWinnerInCurrParty();
        ResultType partyResult = model.getPartyResult();
        if (partyResult != null) {
            view.showPartyResult(partyResult.getMessage() + "!!!");
            view.setFieldActivity(false);
            return;
        }

        onLoadWhoseTurnInfo();
    }
}
