package maxlich.game.controller;

import maxlich.game.model.Model;
import maxlich.game.util.PlayerNumber;
import maxlich.game.view.View;

public abstract class Controller {
    View view;
    Model model;

    Controller(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void init();

    public abstract void onLoadPartyNumber();
    public abstract void onLoadPlayerName(PlayerNumber playerNumber);
    public abstract void onLoadPlayerWinsCount(PlayerNumber playerNumber);

    public abstract void onLoadWhoseTurnInfo();
    public abstract void onLoadFieldTableModel();

    public abstract void onClickFieldCell(int selectedRow, int selectedColumn);

}
