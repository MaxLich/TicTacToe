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
    public void onLoadPlayerWinsCount(PlayerNumber playerNumber) {
        int playerWinsCount = model.getPlayerWinsCount(playerNumber);
        view.showPlayerWinsCount(playerNumber,playerWinsCount);
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

        ResultType partyResult = model.getPartyResult();
        if (partyResult != null) {
            view.setFieldActivity(false);
            if (partyResult != ResultType.DRAW)
                onLoadPlayerWinsCount(partyResult.getWinner());
            ResultType gameResult = model.getGameResult();
            if (gameResult != null) {
                view.showPartyResult("Игра завершена. Итоги всей игры: " + gameResult.getMessage() + "!!!");
                boolean isNewGame = view.showDialog("Игра завершена", "Начать новую игру?");
                if (isNewGame) {
                    onStartNewGame();
                }
            } else {
                view.showPartyResult("Партия завершена. Итоги партии: " + partyResult.getMessage() + "!!!");
                boolean isNextParty = view.showDialog("Партия завершена", "Следующая партия?");
                if (isNextParty) {
                    onStartNewParty();
                } else {
                    view.setNewPartyMenuItemActivity(true);
                }
            }
            return;
        }

        onLoadWhoseTurnInfo();
    }

    @Override
    public void onStartNewParty() {
        model.initNewParty();
        view.setNewPartyMenuItemActivity(false);
        onLoadPartyNumber();
        view.clearPartyResult();
        view.setFieldActivity(true);
    }

    @Override
    public void onStartNewGame() {
        model.initNewGame();
        onLoadPartyNumber();
        onLoadPlayerWinsCount(PlayerNumber.PLAYER_1);
        onLoadPlayerWinsCount(PlayerNumber.PLAYER_2);
        view.clearPartyResult();
        view.setFieldActivity(true);
    }
}
