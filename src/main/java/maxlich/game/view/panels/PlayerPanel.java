package maxlich.game.view.panels;

import maxlich.game.util.PlayerNumber;
import maxlich.game.view.Fonts;
import maxlich.game.view.View;

import javax.swing.*;
import java.awt.*;

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
        titleLabel.setFont(Fonts.H3);
        playerNameLabel = new JLabel();
        playerNameLabel.setFont(Fonts.H4_ITALIC);
        playerWinsLabel = new JLabel("0");
        playerWinsLabel.setFont(Fonts.H4);


        add(titleLabel);
        add(Box.createVerticalStrut(5));
        add(playerNameLabel);
        add(Box.createVerticalStrut(5));
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
