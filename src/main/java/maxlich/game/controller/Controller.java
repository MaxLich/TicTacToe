package maxlich.game.controller;

import maxlich.game.model.Model;
import maxlich.game.util.PlayerNumber;
import maxlich.game.view.View;

public abstract class Controller {
    protected View view;
    protected Model model;

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

    public abstract void onLoadPlayerName(PlayerNumber playerNumber);
    public abstract void onLoadWhoseTurnInfo();
    public abstract void onLoadFieldTableModel();

}
