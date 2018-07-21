package maxlich.game.view.panels;

import maxlich.game.util.PlayerNumber;
import maxlich.game.view.View;

import javax.swing.*;

public class PlayerPanel extends AbstractPanel {
    private PlayerNumber playerNumber;
    private JLabel playerNameLabel;
    private JLabel playerWinsLabel;

    public PlayerPanel(View view, PlayerNumber playerNumber) {
        super(view);
        this.playerNumber = playerNumber;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();
    }


    @Override
    protected void addComponents() {
        JLabel titleLabel = new JLabel(playerNumber.getTitle());
        playerNameLabel = new JLabel();
        playerWinsLabel = new JLabel("0");

        add(titleLabel);
        add(playerNameLabel);
        add(playerWinsLabel);

       // view.fireLoadPlayerName(playerNumber);
    }

    @Override
    public void init() {
        view.fireLoadPlayerName(playerNumber);
    }

    public void loadPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }
}
